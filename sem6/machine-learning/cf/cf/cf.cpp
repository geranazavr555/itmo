#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <map>

using namespace std;

const int N = 100100, K = 100100;

int n, m, k;
int c[N];

vector<int> parts[K];

int main()
{
	ios::sync_with_stdio(0);

	map<int, int> cnt;
	set<int> classes;
	map<int, vector<int>> ind;

	cin >> n >> m >> k;
	for (int i = 0; i < n; ++i) {
		cin >> c[i];
		cnt[c[i]]++;
		classes.insert(c[i]);
		ind[c[i]].push_back(i);
	}

	int i = 0;
	for (auto const& cl : classes) {
		while (cnt[cl] > 0) {
			parts[i].push_back(ind[cl][cnt[cl] - 1]);
			cnt[cl]--;
			i = (i + 1) % k;
		}
	}

	for (i = 0; i < k; ++i) {
		cout << parts[i].size() << ' ';
		for (auto const& x : parts[i]) {
			cout << x + 1 << ' ';
		}
		cout << endl;
	}

	return 0;
}
