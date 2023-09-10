#include <iostream>
#include <vector>
#include <random>
#include <set>
#include <optional>
#include <unordered_map>

#undef DEBUG

const int BITS = 8;

using std::vector;

typedef uint32_t mask_type;
typedef uint32_t bit_index_type;
typedef vector<mask_type> polynomial_type;

#ifdef DEBUG

template <typename T>
void print_debug(vector<T> const& v) {
    using std::endl;
    using std::cerr;
    std::cerr << "=== ===" << endl;
    for (double x: v)
        cerr << x << ' ';
    cerr << endl;
}

template <typename T>
void print_debug(T const &s) {
    std::cerr << s << std::endl;
}

#else
#define print_debug(...)
#endif

#define assert(x) {if (!(x)) {std::cerr << "Assertion failed in line " << __LINE__ << std::endl; exit(1);}}

mask_type primitive_polynomial;

inline bool bit(mask_type x, bit_index_type i) {
    return (x >> i) & 1;
}

vector<bit_index_type> bit_count_cache(1 << 20, std::numeric_limits<bit_index_type>::max());

inline bit_index_type bit_count(mask_type x) {
    const int limit = bit_count_cache.size();
    if (x < limit && bit_count_cache[x] != std::numeric_limits<bit_index_type>::max())
        return bit_count_cache[x];

    auto xx = x;

    bit_index_type result = 0;
    while (x > 0) {
        x >>= 1;
        result++;

        if (x < limit) {
            result += bit_count(x);
            break;
        }
    }

    if (xx < limit)
        bit_count_cache[xx] = result;

    return result;
}

inline void inverse_bit(mask_type& x, bit_index_type i) {
    x ^= (1u << i);
}

#define madd(x, y) ((x) ^ (y))

//inline mask_type madd(mask_type a, mask_type b) {
//    return a ^ b;
//}

mask_type **mmul_cache;

inline mask_type mmul_unsafe(mask_type a, mask_type b) {
    auto bit_count_a = bit_count(a);
    auto bit_count_b = bit_count(b);

    assert(bit_count_a + bit_count_b <= sizeof(mask_type) * BITS)

    mask_type result = 0;
    for (bit_index_type i = 0; i < bit_count_a; ++i)
        for (bit_index_type j = 0; j < bit_count_b; ++j)
            if (bit(a, i) && bit(b, j))
                inverse_bit(result, i + j);

    return result;
}

inline mask_type mmod(mask_type x, mask_type modulo) {
    auto modulo_bit_count = bit_count(modulo);
    auto x_bit_count = bit_count(x);
    while (x_bit_count >= modulo_bit_count) {
        auto shift = x_bit_count - modulo_bit_count;
        x ^= (modulo << shift);
        while (x_bit_count > 0 && !bit(x, x_bit_count - 1))
            x_bit_count--;
//        x_bit_count = bit_count(x);
    }
    return x;
}

inline mask_type mmul(mask_type a, mask_type b) {
    auto result = mmod(mmul_unsafe(a, b), primitive_polynomial);
    mmul_cache[a][b] = result;
    return result;
}

inline int mlog(mask_type x);

inline void pnorm(polynomial_type& x) {
    while (x.back() == 0)
        x.pop_back();
}

inline polynomial_type pmul(polynomial_type const& a, polynomial_type const& b) {
    polynomial_type result(a.size() + b.size());
    for (int i = 0; i < a.size(); ++i) {
        auto const& alpha_a = a[i];
        for (int j = 0; j < b.size(); ++j) {
            auto const& alpha_b = b[j];

            result[i + j] = madd(result[i + j], mmul_cache[alpha_a][ alpha_b]);
        }
    }

    pnorm(result);

    return result;
}

inline void pmul_inplace(polynomial_type& polynomial, mask_type c) {
    for (auto &alpha: polynomial) {
        alpha = mmul_cache[c][alpha];
    }

    pnorm(polynomial);
}

inline polynomial_type pmul(polynomial_type const& polynomial, mask_type c) {
    polynomial_type result(polynomial);
    pmul_inplace(result, c);
    return result;
}

inline polynomial_type padd(polynomial_type const& a, polynomial_type const& b) {
    polynomial_type result(std::max(a.size(), b.size()));
    for (int i = 0; i < result.size(); ++i) {
        if (i < a.size() && i < b.size()) {
            result[i] = madd(a[i], b[i]);
        } else if (i < a.size()) {
            result[i] = a[i];
        } else {
            result[i] = b[i];
        }
    }
    pnorm(result);
    return result;
}

inline void padd_inplace_unsafe(polynomial_type& a, polynomial_type const& b) {
    assert(a.size() >= b.size());

    for (int i = 0; i < b.size(); ++i) {
        a[i] = madd(a[i], b[i]);
    }

    pnorm(a);
}

inline polynomial_type pmulpowx(polynomial_type const& a, int pw) {
    polynomial_type result(a.size() + pw);
    for (int i = 0; i < a.size(); ++i)
        result[i + pw] = a[i];
    return result;
}

inline void pmulx_inplace(polynomial_type& a) {
    a.insert(a.begin(), 0);
}

inline int pdeg_unsafe(polynomial_type const& x) {
    for (int i = static_cast<int>(x.size()) - 1; i >= 0; --i)
        if (x[i] != 0)
            return i;
    return -1;
}

inline int pdeg(polynomial_type const& x) {
    auto result = pdeg_unsafe(x);
    assert(result >= 0);
    return result;
}

vector<mask_type> alphas;

const mask_type AONE = 1;

inline mask_type mpow(mask_type a, int b) { // NOLINT(misc-no-recursion)
    while (b < 0)
        b += static_cast<int>(alphas.size());
    if (b == 0)
        return AONE;
    if (b == 1)
        return a;
    if (b % 2)
        return mmul_cache[mpow(a, b - 1)][ a];

    auto tmp = mpow(a, b / 2);
    return mmul_cache[tmp][tmp];
}

inline mask_type apow(int pw) {
    return alphas[(pw + alphas.size()) % alphas.size()];
}

inline mask_type inverse(mask_type x) {
    for (mask_type alpha: alphas)
        if (mmul_cache[alpha][x] == AONE)
            return alpha;
    assert(false);
}

inline polynomial_type pmod(polynomial_type x, polynomial_type const& modulo) {
    int modulo_deg = pdeg(modulo);
    int modulo_alpha_log = mlog(modulo[modulo_deg]);
    int x_deg = pdeg_unsafe(x);
    while (x_deg >= modulo_deg) {
        int shift = x_deg - modulo_deg;
//        auto shifted_modulo = pmulpowx(modulo, shift);
        int x_alpha_log = mlog(x[x_deg]);
        auto mul_coef = apow(x_alpha_log - modulo_alpha_log);
//        pmul_inplace(shifted_modulo, mul_coef);

        for (int i = shift; i <= x_deg; ++i) {
            x[i] = madd(x[i], mmul_cache[mul_coef][modulo[i - shift]]);
        }

//        padd_inplace_unsafe(x, shifted_modulo);
        while (x_deg >= 0 && x[x_deg] == 0)
            x_deg--;
//        x_deg = pdeg_unsafe(x);
    }
    return x;
}

inline int mlog(mask_type x) {
    for (int i = 0; i < alphas.size(); ++i)
        if (x == alphas[i])
            return i;
    assert(false);
}

#define PAPPLY_GORNER

inline mask_type papply(polynomial_type const& polynomial, mask_type x) {
#ifdef PAPPLY_GORNER
    mask_type result = polynomial.back();
    for (int i = polynomial.size() - 2; i >= 0; --i)
        result = madd(polynomial[i], mmul_cache[x][result]);
    return result;
#else
    mask_type x_pow = 1;
    mask_type sum = 0;
    for (mask_type c : polynomial) {
        sum = madd(sum, mmul_cache[c][ x_pow]);
        x_pow = mmul_cache[x_pow][ x];
    }
    return sum;
#endif
}

const mask_type AX = 2;

void prepare_alphas(int n) {
    mmul_cache = new mask_type*[primitive_polynomial];
    for (int i = 0; i <= primitive_polynomial; ++i)
        mmul_cache[i] = new mask_type[primitive_polynomial];

    for (int i = 0; i < primitive_polynomial; ++i)
        for (int j = 0; j < primitive_polynomial; ++j)
            mmul_cache[i][j] = mmul(i, j);

    alphas.resize(n);
    alphas[0] = AONE;
    for (int i = 1; i < n; ++i)
        alphas[i] = mmul_cache[alphas[i - 1]][AX];
}

polynomial_type gx;

void prepare_gx(int n, int d) {
    vector<vector<int>> C;
    vector<char> used(n);
    for (int i = 0; i < n; ++i) {
        if (!used[i]) {
            C.emplace_back();
            int j = i;
            do {
                C.back().push_back(j);
                used[j] = true;
                j = (j * 2) % n;
            } while (!used[j]);
        }
    }

    for (const auto &cyclomatic_class: C) {
        if (cyclomatic_class[0] >= 1 && cyclomatic_class[0] < d) {
            polynomial_type min_polynomial({alphas[cyclomatic_class[0]], 1});
            for (int i = 1; i < cyclomatic_class.size(); ++i)
                min_polynomial = pmul(min_polynomial, polynomial_type({alphas[cyclomatic_class[i]], 1}));

            if (gx.empty())
                gx = min_polynomial;
            else
                gx = pmul(gx, min_polynomial);
        }
    }
}

polynomial_type encode(int n, int k, polynomial_type const& to_encode) {
    int shift = n - k;
    auto shifted = pmulpowx(to_encode, shift);
    auto result = padd(shifted, pmod(shifted, gx));
    while (result.size() < n)
        result.push_back(0);
    return result;
}

inline mask_type get(polynomial_type const& polynomial, int i) {
    if (i >= polynomial.size())
        return 0;
    return polynomial[i];
}

std::optional<polynomial_type> decode(int d, polynomial_type const& to_decode) {
    polynomial_type syndrome(d);
    bool flag = true;
    for (int i = 1; i < d; ++i) {
        syndrome[i] = papply(to_decode, apow(i));
        flag &= (syndrome[i] == 0);
    }
    if (flag)
        return to_decode;

    int L = 0;
    polynomial_type locator_polynomial({1});
    polynomial_type B({1});
    for (int r = 1; r < d; ++r) {
        pmulx_inplace(B);

        if (r % 2 == 0)
            continue;

        mask_type delta = 0;
        for (int j = 0; j <= L; ++j)
            delta = madd(delta, mmul_cache[get(locator_polynomial, j)][get(syndrome, r - j)]);


        if (delta != 0) {
            auto tmp = padd(locator_polynomial, pmul(B, delta));
            if (2 * L <= r - 1) {
                B = pmul(locator_polynomial, inverse(delta));
                L = r - L;
            }
            locator_polynomial = tmp;
        }
    }

    if (pdeg(locator_polynomial) != L) {
        return std::nullopt;
    }

    polynomial_type result(to_decode);
    for (int i = 0; i < to_decode.size(); ++i)
        if (papply(locator_polynomial, apow(-i)) == 0)
            result[i] ^= 1;

    return result;
}

std::mt19937_64 generator; // NOLINT(cert-msc51-cpp)
double perform_simulate(int n, int k, int d, double noise_level, int iterations, int max_errors) {
    std::uniform_int_distribution<int> zero_one_gen(0, 1);
    std::uniform_real_distribution<double> inverse_gen(0, 1);

    int error_count = 0;
    int all_count = 0;
    for (int i = 0; i < iterations; ++i) {

        if (i % 10000 == 0)
            std::cerr << i << std::endl;

        polynomial_type v(k);
        for (int j = 0; j < k; ++j)
            v[j] = zero_one_gen(generator);

        polynomial_type encoded = encode(n, k, v);

        polynomial_type noisy_encoded(encoded);
        for (auto &x: noisy_encoded) {
            if (inverse_gen(generator) < noise_level)
                x ^= 1;
        }

        auto decoded = decode(d, noisy_encoded);

        ++all_count;

        if (decoded.has_value()) {
            for (int j = 0; j < n; ++j) {
                if (decoded.value()[j] != encoded[j]) {
                    error_count++;
                    break;
                }
            }
        } else
            error_count++;

        if (error_count == max_errors)
            break;
    }

    return static_cast<double>(error_count) / static_cast<double>(all_count);
}

int main() {
    using std::cin;
    using std::cout;
    using std::endl;

    std::ios::sync_with_stdio(false);
#ifndef DEBUG
    freopen("input.txt", "r", stdin);
//    freopen("test.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif

    int n, d;
    cin >> n >> primitive_polynomial >> d;

    prepare_alphas(n);
    prepare_gx(n, d);

    int k = n - static_cast<int>(gx.size()) + 1;
    cout << k << endl;
    for (int i = 0; i < n - k + 1; ++i)
        cout << gx[i] << ' ';
    cout << endl;

    std::string command;
    while (cin >> command) {
        if (command == "Encode") {
            polynomial_type to_encode(k);
            for (auto& x : to_encode)
                cin >> x;

            auto encoded = encode(n, k, to_encode);
            for (auto& x : encoded)
                cout << x << ' ';
            cout << endl;

        } else if (command == "Decode") {
            polynomial_type to_decode(n);
            for (auto& x : to_decode)
                cin >> x;

            auto decoded = decode(d, to_decode);

            if (decoded.has_value()) {
                for (auto& x : decoded.value())
                    cout << x << ' ';
                cout << endl;
            } else
                cout << "ERROR" << endl;

        } else if (command == "Simulate") {
            double noise_level;
            int iterations, max_errors;
            cin >> noise_level >> iterations >> max_errors;
            cout << perform_simulate(n, k, d, noise_level, iterations, max_errors) << endl;

        } else {
            std::cerr << "stop" << endl;
            return 0;
        }
    }

    return 0;
}
