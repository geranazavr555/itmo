import numpy as np
import math


class Model:
    def __init__(self, X_train, Y_train):
        self._X_train = X_train
        self._Y_train = Y_train
        assert len(X_train) == len(Y_train)
        self._dist_func = None
        self._kernel_func = None
        self._h_func = None
        self._looi = None
        self._loo_cache_x = {}
        self._loo_cache_y = {}

    @property
    def dist_func(self):
        return self._dist_func

    @dist_func.setter
    def dist_func(self, value):
        assert callable(value)
        self._dist_func = value

    @property
    def kernel_func(self):
        return self._kernel_func

    @kernel_func.setter
    def kernel_func(self, value):
        assert callable(value)
        self._kernel_func = np.vectorize(value)

    @property
    def h_func(self):
        return self._h_func

    @h_func.setter
    def h_func(self, value):
        assert callable(value)
        self._h_func = value

    def _loo(self, nparr):
        return np.delete(nparr, self._looi, axis=0)

    @property
    def X_train(self):
        if self._looi is None:
            return self._X_train
        if self._looi not in self._loo_cache_x:
            self._loo_cache_x[self._looi] = self._loo(self._X_train)
        return self._loo_cache_x[self._looi]

    @property
    def Y_train(self):
        if self._looi is None:
            return self._Y_train
        if self._looi not in self._loo_cache_y:
            self._loo_cache_y[self._looi] = self._loo(self._Y_train)
        return self._loo_cache_y[self._looi]

    def __call__(self, x):
        h = self._h_func(x, self.X_train, self.dist)
        if h == 0.0:
            w = np.zeros((len(self.X_train),))
            for i, _x in enumerate(self.X_train):
                if self.dist(_x, x) == 0.0:
                    w[i] = 1
        else:
            args = np.apply_along_axis(lambda _x: self.dist(_x, x), 1, self.X_train) / h
            w = self.kernel(args)
        sm = w.sum()
        if sm == 0.0:
            return np.mean(self.Y_train)
        return (w * self.Y_train).sum() / sm

    @property
    def leave_one_out_index(self):
        return self._looi

    @leave_one_out_index.setter
    def leave_one_out_index(self, value):
        assert isinstance(value, int)
        self._looi = value

    @leave_one_out_index.deleter
    def leave_one_out_index(self):
        self._looi = None

    def kernel(self, x):
        return self._kernel_func(x)

    def dist(self, a, b):
        return self._dist_func(a, b)


class VectorModel:
    def __init__(self, X_train, Y_trains):
        self._models = list([Model(X_train, Y_train) for Y_train in Y_trains])

    @property
    def dist_func(self):
        return self._models[0].dist_func

    @dist_func.setter
    def dist_func(self, value):
        for model in self._models:
            model.dist_func = value

    @property
    def kernel_func(self):
        return self._models[0].kernel_func

    @kernel_func.setter
    def kernel_func(self, value):
        for model in self._models:
            model.kernel_func = value

    @property
    def h_func(self):
        return self._models[0].h_func

    @h_func.setter
    def h_func(self, value):
        for model in self._models:
            model.h_func = value

    def __call__(self, x):
        return np.array([model(x) for model in self._models])

    @property
    def leave_one_out_index(self):
        return self._models[0].leave_one_out_index

    @leave_one_out_index.setter
    def leave_one_out_index(self, value):
        for model in self._models:
            model.leave_one_out_index = value

    @leave_one_out_index.deleter
    def leave_one_out_index(self):
        for model in self._models:
            del model.leave_one_out_index


def nadaraya_watson(*args, **kwargs):
    return VectorModel(*args, **kwargs)


_dists = {
    "manhattan": lambda a, b: abs(a - b).sum(),
    "euclidean": lambda a, b: (abs(a - b) ** 2).sum() ** (1 / 2),
    "chebyshev": lambda a, b: abs(a - b).max()
}

_kernels = {
    "uniform": lambda u: 0.5 if abs(u) <= 1 else 0.0,
    "triangular": lambda u: 1 - abs(u) if abs(u) <= 1 else 0.0,
    "epanechnikov": lambda u: 0.75 * (1 - u ** 2) if abs(u) <= 1 else 0.0,
    "quartic": lambda u: 0.9375 * (1 - u ** 2) ** 2 if abs(u) <= 1 else 0.0,
    "triweight": lambda u: 1.09375 * (1 - u ** 2) ** 3 if abs(u) <= 1 else 0.0,
    "tricube": lambda u: 70 * (1 - abs(u) ** 3) ** 3 / 81 if abs(u) <= 1 else 0.0,
    "gaussian": lambda u: math.exp(-0.5 * u ** 2) / math.sqrt(math.pi * 2),
    "cosine": lambda u: math.pi * (math.cos(math.pi * u / 2)) / 4 if abs(u) <= 1 else 0.0,
    "logistic": lambda u: (math.exp(u) + 2 + math.exp(-u)) ** -1,
    "sigmoid": lambda u: 2 / (math.pi * (math.exp(u) + math.exp(-u)))
}

_hs = {
    "fixed": lambda k: (lambda x, X_train, dist: k),
    "variable": lambda k: (lambda x, X_train, dist: np.sort(np.apply_along_axis(lambda _x: dist(_x, x), 1, X_train))[k])
}


def dist(name):
    return _dists[name]


def kernel(name):
    return _kernels[name]


def h(name, k):
    return _hs[name](k)
