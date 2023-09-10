#include <iostream>
#include <vector>
#include <random>
#include <cmath>

#define DEBUG

typedef std::vector<char> vector_type;
typedef std::vector<vector_type> matrix_type;

typedef std::pair<std::vector<std::pair<int, char>>, std::vector<std::pair<int, char>>> vertex_type;

inline std::vector<std::pair<int, char>> &prev(vertex_type &vertex) {
    return vertex.first;
}

inline std::vector<std::pair<int, char>> &next(vertex_type &vertex) {
    return vertex.second;
}

inline std::vector<std::pair<int, char>> const &prev(vertex_type const &vertex) {
    return vertex.first;
}

inline std::vector<std::pair<int, char>> const &next(vertex_type const &vertex) {
    return vertex.second;
}

typedef std::vector<vertex_type> layer_type;
typedef std::vector<layer_type> lattice_type;
typedef std::vector<int> active_symbols_type;
typedef std::vector<active_symbols_type> vector_active_symbols_type;

#ifdef DEBUG

void print_debug(matrix_type const &G) {
    using std::endl;
    using std::cerr;
    std::cerr << "===  ===" << endl;
    for (const auto &gi: G) {
        for (char gij: gi)
            cerr << static_cast<char>(gij + '0') << ' ';
        cerr << endl;
    }
}

void print_debug(lattice_type const& lattice) {
    using std::endl;
    using std::cerr;
    std::cerr << "===  ===" << endl;
    for (int i = 0; i < lattice.size(); ++i) {
        std::cerr << "=== Layer " << i << " ===" << endl;

        auto const& layer = lattice[i];
        for (int j = 0; j < layer.size(); ++j) {
            cerr << "Vertex " << j << ": prev: ";
            auto const& vertex = layer[j];

            for (const auto &prev_v: prev(vertex)) {
                cerr << prev_v.first << ' ';
            }

            cerr << "next: ";

            for (const auto &next_pair : next(vertex)) {
                const auto &next_v = next_pair.first;
                const auto &edge_val = next_pair.second;

                cerr << "(" << next_v << ", " << static_cast<char>(edge_val + '0') << ") ";
            }
            cerr << endl;
        }
    }
}

void print_debug(std::vector<double> const& v) {
    using std::endl;
    using std::cerr;
    std::cerr << "===  ===" << endl;
    for (double x: v)
        cerr << x << ' ';
    cerr << endl;
}

void print_debug(std::string const &s) {
    std::cerr << s << std::endl;
}

#else
#define print_debug(...)
#endif

inline char xor_(char a, char b) {
    return (a ? static_cast<char>(b ? 0 : 1) : b);
}

inline void xor_assign(vector_type &a, vector_type &b) {
    for (int i = 0; i < a.size(); ++i)
        a[i] = xor_(a[i], b[i]);
}

void min_span_form(matrix_type &G) {
    int k = static_cast<int>(G.size());
    int n = static_cast<int>(G[0].size());
    int column = 0;
    for (int i = 0; i < k; ++i) {
        print_debug(G);

        int first_one_row = -1;
        for (int j = i; j < k; ++j) {
            if (G[j][column]) {
                first_one_row = j;
                break;
            }
        }

        if (first_one_row == -1) {
            column++;
            i--;
            continue;
        }

        if (i != first_one_row)
            xor_assign(G[i], G[first_one_row]);
//        std::swap(G[i], G[first_one_row]);

        int j = first_one_row;
        if (i == first_one_row)
            j++;
        for (; j < k; ++j)
            if (G[j][column])
                xor_assign(G[j], G[i]);
        column++;
    }

    print_debug("================");

    std::vector<char> fixed(k);
    column = n - 1;
    for (int i = k - 1; i >= 0; --i) {
        print_debug(G);

        int last_one_row = -1;
        for (int j = k - 1; j >= 0; --j) {
            if (G[j][column] && !fixed[j]) {
                last_one_row = j;
                break;
            }
        }

        if (last_one_row == -1) {
            column--;
            i++;
            continue;
        }

        fixed[last_one_row] = true;

        for (int j = last_one_row - 1; j >= 0; --j)
            if (G[j][column] && !fixed[j])
                xor_assign(G[j], G[last_one_row]);
        column--;
    }
}

void build_active_symbols(matrix_type const &G, vector_active_symbols_type &active_symbols) {
    active_symbols = vector_active_symbols_type(G[0].size() + 1);
    int k = static_cast<int>(G.size());
    for (int i = 0; i < k; ++i) {
        const auto &row = G[i];
        int first = -1, last = -1;
        int n = static_cast<int>(row.size());
        for (int j = 0; j < n; ++j) {
            if (row[j]) {
                if (first == -1)
                    first = j;
                last = j;
            }
        }
        for (int j = first; j < last; ++j)
            active_symbols[j + 1].push_back(i);
    }
}

inline int one_in_a_not_in_b(active_symbols_type const &a, active_symbols_type const &b) {
    for (int x: a) {
        int result = x;
        for (int y: b)
            if (y == x) {
                result = -1;
                break;
            }
        if (result != -1)
            return result;
    }
    return -1;
}

inline bool bit(int x, int i) {
    return (1 << i) & x;
}

inline char bitc(int x, int i) {
    return bit(x, i) ? 1 : 0;
}

inline char scalar_mul(std::vector<char> const& a, std::vector<char> const& b) {
    char result = 0;
    for (int i = 0; i < a.size(); ++i)
        result ^= a[i] & b[i]; // NOLINT(cppcoreguidelines-narrowing-conversions)
    return result;
}

void build_lattice(matrix_type const &G, lattice_type &lattice) {
    vector_active_symbols_type active_symbols;
    build_active_symbols(G, active_symbols);

    lattice = lattice_type();
    lattice.emplace_back(1, vertex_type());

    int n = static_cast<int>(G[0].size());
    int k = static_cast<int>(G.size());
    for (int i = 1; i < n + 1; ++i) {
        int layer_size = 1 << active_symbols[i].size();
        lattice.emplace_back(layer_size);
        auto &layer = lattice[i];
        auto &prev_layer = lattice[i - 1];
        auto &cur_active_symbols = active_symbols[i];
        auto &prev_active_symbols = active_symbols[i - 1];

        std::vector<char> prev_column(k);
        for (int j = 0; j < k; ++j)
            prev_column[j] = G[j][i - 1];

        for (int prev_vertex_i = 0; prev_vertex_i < prev_layer.size(); ++prev_vertex_i) {
            for (int vertex_i = 0; vertex_i < layer.size(); ++vertex_i) {
                bool exist_edge = true;
                for (int prev_active_symbol_i = 0; prev_active_symbol_i < prev_active_symbols.size(); ++prev_active_symbol_i) {
                    auto const& prev_active_symbol = prev_active_symbols[prev_active_symbol_i];
                    for (int cur_active_symbol_i = 0; cur_active_symbol_i < cur_active_symbols.size(); ++cur_active_symbol_i) {
                        auto const& cur_active_symbol = cur_active_symbols[cur_active_symbol_i];
                        if (cur_active_symbol == prev_active_symbol &&
                            bit(prev_vertex_i, prev_active_symbol_i) != bit(vertex_i, cur_active_symbol_i)) {
                            exist_edge = false;
                            break;
                        }
                    }

                    if (!exist_edge)
                        break;
                }

                if (exist_edge) {
                    std::vector<int> symbols(prev_active_symbols);
                    int add = one_in_a_not_in_b(cur_active_symbols, prev_active_symbols);
                    if (add != -1)
                        symbols.push_back(add);

                    std::vector<char> symbol_values_from_matrix;
                    symbol_values_from_matrix.reserve(symbols.size());
                    for (int symbol: symbols) {
                        symbol_values_from_matrix.push_back(prev_column[symbol]);
                    }

                    std::vector<char> symbol_values_from_index;
                    for (int si = 0; si < symbols.size(); ++si)
                        if (symbols[si] != add)
                            symbol_values_from_index.push_back(bitc(prev_vertex_i, si));

                    if (add != -1) {
                        symbol_values_from_index.push_back(bitc(vertex_i, static_cast<int>(cur_active_symbols.size()) - 1));
                    }

                    char edge_val = scalar_mul(symbol_values_from_index, symbol_values_from_matrix);
                    next(prev_layer[prev_vertex_i]).emplace_back(vertex_i, edge_val);
                    prev(layer[vertex_i]).emplace_back(prev_vertex_i, edge_val);
                }
            }
        }
    }
}

inline void multiply(std::vector<char> const& v, matrix_type const& G, std::vector<char>& result_vector) {
    int k = static_cast<int>(v.size());
    int n = static_cast<int>(G[0].size());
    result_vector.resize(n);
    for (int i = 0; i < n; ++i) {
        char result = 0;
        for (int j = 0; j < k; ++j) {
            result ^= v[j] & G[j][i]; // NOLINT(cppcoreguidelines-narrowing-conversions)
        }
        result_vector[i] = result;
    }
}

inline void encode(matrix_type const& G, std::vector<char> const& to_encode, std::vector<char>& result) {
    multiply(to_encode, G, result);
}

inline double count_edge_w(double decode_item, char edge_value) {
    return decode_item * static_cast<double>(edge_value);
}

void decode(lattice_type const& lattice, std::vector<double> const& to_decode, std::vector<char> &result) {
    std::vector<std::vector<double>> dist(lattice.size());
    for (int i = 0; i < lattice.size(); ++i)
        dist[i].resize(lattice[i].size());

    std::vector<std::vector<int>> prev_i(lattice.size());
    for (int i = 0; i < lattice.size(); ++i)
        prev_i[i].resize(lattice[i].size());

    std::vector<std::vector<char>> prev_c(lattice.size());
    for (int i = 0; i < lattice.size(); ++i)
        prev_c[i].resize(lattice[i].size());

    for (int i = 1; i < lattice.size(); ++i) {
        for (int j = 0; j < lattice[i].size(); ++j) {
            auto const& prev_vec = prev(lattice[i][j]);
            dist[i][j] = dist[i - 1][prev_vec[0].first] + count_edge_w(to_decode[i - 1], prev_vec[0].second);
            prev_i[i][j] = prev_vec[0].first;
            prev_c[i][j] = prev_vec[0].second;
            for (int pi = 1; pi < prev_vec.size(); ++pi) {
                double new_dist = dist[i - 1][prev_vec[pi].first] + count_edge_w(to_decode[i - 1], prev_vec[pi].second);
                if (new_dist < dist[i][j]) {
                    dist[i][j] = new_dist;
                    prev_i[i][j] = prev_vec[pi].first;
                    prev_c[i][j] = prev_vec[pi].second;
                }
            }
        }
    }

    result.resize(lattice.size() - 1);
    int j = 0;
    for (int i = static_cast<int>(lattice.size()) - 1; i > 0; --i) {
        result[i - 1] = prev_c[i][j];
        j = prev_i[i][j];
    }
}

std::mt19937_64 generator; // NOLINT(cert-msc51-cpp)
double perform_simulate(matrix_type const& G, lattice_type const& lattice, double noise_level, int iterations, int max_errors) {
    auto n = static_cast<int>(G[0].size());
    auto k = static_cast<int>(G.size());

    std::uniform_int_distribution<int> zero_one_gen(0, 1);
    std::normal_distribution<double> noise_gen(0, sqrt(pow(10, -noise_level / 10) * static_cast<double>(n) / (2 * static_cast<double>(k))));

    int error_count = 0;
    int all_count = 0;
    for (int i = 0; i < iterations; ++i) {
        vector_type v(k);
        for (int j = 0; j < k; ++j)
            v[j] = static_cast<char>(zero_one_gen(generator));

        vector_type encoded;
        encode(G, v, encoded);

        std::vector<double> noisy_encoded;
        noisy_encoded.reserve(encoded.size());
        for (char x: encoded)
            noisy_encoded.push_back(1 - 2 * static_cast<double>(x) + noise_gen(generator));

       // print_debug(noisy_encoded);

        std::vector<char> decoded;
        decode(lattice, noisy_encoded, decoded);

        ++all_count;
        for (int j = 0; j < n; ++j) {
            if (decoded[j] != encoded[j]) {
                error_count++;
                break;
            }
        }

        if (error_count == max_errors)
            break;
    }

    return static_cast<double>(error_count) / static_cast<double>(all_count);
}

int main() {
    std::ios::sync_with_stdio(false);
#ifndef DEBUG
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif

    int n, k;
    std::cin >> n >> k;
    matrix_type G(k);
    for (int i = 0; i < k; ++i) {
        for (int j = 0; j < n; ++j) {
            char c;
            std::cin >> c;
            G[i].push_back(static_cast<char>(c - '0'));
        }
    }

    matrix_type span(G);
    min_span_form(span);
//    print_debug(span);

    lattice_type lattice;
    build_lattice(span, lattice);
    print_debug(lattice);

    for (auto const &layer: lattice)
        std::cout << layer.size() << ' ';
    std::cout << std::endl;

    std::string command;
    while (std::cin >> command) {
        if (command == "Encode") {
            std::vector<char> to_encode(k);
            for (auto &x: to_encode)
                std::cin >> x;

            std::vector<char> result;
            encode(G, to_encode, result);
            for (char x: result)
                std::cout << static_cast<char>(x + '0') << ' ';
            std::cout << std::endl;
        } else if (command == "Decode") {
            std::vector<double> to_decode(n);
            for (auto &x : to_decode)
                std::cin >> x;

            std::vector<char> result;
            decode(lattice, to_decode, result);
            for (char x: result)
                std::cout << static_cast<char>(x + '0') << ' ';
            std::cout << std::endl;
        } else if (command == "Simulate") {
            double noise_level;
            int iterations, max_errors;
            std::cin >> noise_level >> iterations >> max_errors;
            std::cout << perform_simulate(G, lattice, noise_level, iterations, max_errors) << std::endl;
        } else {
            std::cerr << "stop" << std::endl;
            return 0;
        }
    }

    return 0;
}
