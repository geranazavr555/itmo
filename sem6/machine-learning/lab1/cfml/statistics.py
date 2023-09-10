import numpy as np
from decimal import Decimal
import math


def pearson_correlation(X, Y):
    X_mean = np.mean(X)
    Y_mean = np.mean(Y)
    d = ((X - X_mean) ** 2).sum() * ((Y - Y_mean) ** 2).sum()
    return ((X - X_mean) * (Y - Y_mean)).sum() / np.sqrt(d) if abs(d) > 1e-9 else 0.0


def spearman_rank_correlation(X, Y):
    n = X.shape[0]
    return 1.0 - ((X.argsort().argsort() - Y.argsort().argsort()) ** 2).sum() * 6.0 / (n * (n - 1) * (n + 1))


def conditional_variance(k, X, Y):
    n = len(X)
    X_to_Ys = dict()
    X_to_Ys_to_cnt = dict()
    X_to_sum_Y = dict()
    X_to_sum_Y_2 = dict()

    for i in range(n):
        if X[i] not in X_to_Ys:
            X_to_Ys[X[i]] = []
            X_to_sum_Y[X[i]] = 0.0
            X_to_sum_Y_2[X[i]] = 0.0
            X_to_Ys_to_cnt[X[i]] = dict()
        X_to_Ys[X[i]].append(Y[i])
        X_to_sum_Y[X[i]] += Y[i]
        X_to_sum_Y_2[X[i]] += Y[i] ** 2

    result = 0.0
    for x, ys in X_to_Ys.items():
        E_Y_2_X = X_to_sum_Y_2[x] / len(ys)
        E_Y_X = X_to_sum_Y[x] / len(ys)
        result += (E_Y_2_X - E_Y_X ** 2) * len(ys) / n

    return result


def khi_sqr(k1, k2, X, Y):
    n = len(X)
    count_pairs = dict()
    count_x = dict()
    count_y = dict()
    for i in range(n):
        if (X[i], Y[i]) not in count_pairs:
            count_pairs[(X[i], Y[i])] = 0
        count_pairs[(X[i], Y[i])] += 1

        if X[i] not in count_x:
            count_x[X[i]] = 0
        count_x[X[i]] += 1

        if Y[i] not in count_y:
            count_y[Y[i]] = 0
        count_y[Y[i]] += 1

    result = n
    for x, y in count_pairs.keys():
        real = Decimal(count_pairs[(x, y)])
        expected = Decimal(count_x[x]) * Decimal(count_y[y]) / Decimal(n)
        result -= expected - (real - expected) ** 2 / expected
    return result


def conditional_entropy(k1, k2, X, Y):
    n = len(X)
    X_to_Ys = dict()
    X_to_Ys_cnt = dict()
    X_to_Ys_to_cnt = dict()

    for i in range(n):
        if X[i] not in X_to_Ys:
            X_to_Ys[X[i]] = set()
            X_to_Ys_cnt[X[i]] = 0
            X_to_Ys_to_cnt[X[i]] = dict()
        X_to_Ys[X[i]].add(Y[i])
        X_to_Ys_cnt[X[i]] += 1

        if Y[i] not in X_to_Ys_to_cnt[X[i]]:
            X_to_Ys_to_cnt[X[i]][Y[i]] = 0
        X_to_Ys_to_cnt[X[i]][Y[i]] += 1

    result = 0.0
    for x, ys in X_to_Ys.items():
        p_x = X_to_Ys_cnt[x] / n
        sum_ = 0.0
        for y in ys:
            p_y_x = X_to_Ys_to_cnt[x][y] / X_to_Ys_cnt[x]
            sum_ -= p_y_x * math.log(p_y_x)
        result += p_x * sum_

    return result


def _sum_distances(X):
    X = sorted(X)
    result = 0
    add = 0
    for i in range(1, len(X)):
        local_add = i * (X[i] - X[i - 1])
        result += add
        result += local_add
        add += local_add
    return result * 2


def distances(k, X, Y):
    n = len(X)
    Y_to_Xs = dict()
    for i in range(n):
        if Y[i] not in Y_to_Xs:
            Y_to_Xs[Y[i]] = []
        Y_to_Xs[Y[i]].append(X[i])

    inner = sum(map(_sum_distances, Y_to_Xs.values()))
    return inner, _sum_distances(X) - inner