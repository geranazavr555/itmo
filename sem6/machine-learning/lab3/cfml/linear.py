from abc import ABC, abstractmethod

import numpy as np


class LeastSquares:
    def __init__(self, X, Y, tau):
        self._X = X
        self._Y = Y
        self._tau = tau

        XT = X.transpose()
        self._Fplus = np.linalg.inv(XT @ X + tau * np.eye(XT.shape[0])) @ XT
        self._theta = self._Fplus @ Y

    def __call__(self, x):
        return np.dot(self.theta, x)

    @property
    def theta(self):
        return self._theta


class StochasticGradient(ABC):
    def __init__(self, X, Y, h, lambd, tau, metric_eps=1e-7, theta_i_eps=1e-6, max_iters=2000, seed=None):
        self._X = X
        self._Y = Y
        self._rng = np.random.default_rng(seed)
        self._iter_max = max_iters
        self._h = h
        self._tau = tau
        self._lambd = lambd
        n = self._X.shape[1]
        bound = (2 * n) ** -1
        self._theta = self._rng.uniform(-bound, bound, n)
        self._metric_eps = metric_eps
        self._theta_i_eps = theta_i_eps
        self._metric = None
        self._metric_data = []

        self._calced = False

    def __call__(self, x):
        if not self._calced:
            self.calc()
            self._calced = True

        return self._call(x)

    def _call(self, x):
        return np.dot(self._theta, x)

    def _check_theta(self, new_theta):
        stop = True
        for i in range(len(new_theta)):
            if abs(new_theta[i] - self._theta[i]) > self._theta_i_eps:
                stop = False
                break
        return stop

    def calc(self):
        for _ in range(self._iter_max):
            i = self._rng.integers(0, len(self._X))
            x = self._X[i]
            y = self._Y[i]
            loss = self.loss(x, y)
            gradient = self.gradient(x, y)

            new_theta = self._theta * (1 - self._h * self._tau) - self._h * gradient
            stop = self._check_theta(new_theta)

            self._metric_data.append(self._metric)
            new_metric = self._lambd * loss + (1 - self._lambd) * self._metric
            if abs(new_metric - self._metric) < self._metric_eps:
                stop = True

            self._theta = new_theta
            self._metric = new_metric

            if stop:
                self._metric_data.append(self._metric)
                return
        self._metric_data.append(self._metric)

    @property
    def metric(self):
        return np.array(self._metric_data)

    @property
    def theta(self):
        return self._theta

    @abstractmethod
    def loss(self, x, y):
        pass

    @abstractmethod
    def gradient(self, x, y):
        pass


class StochasticGradientMSE(StochasticGradient):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)

        self._metric = np.array([
            (self._Y[i] - np.dot(self._theta, self._X[i])) ** 2 for i in range(self._X.shape[0])
        ]).mean()

    def loss(self, x, y):
        return (self._call(x) - y) ** 2

    def gradient(self, x, y):
        return 2 * (self._call(x) - y) * self._theta


class StochasticGradientSMAPE(StochasticGradient):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)

        self._metric = np.array([
            (self._Y[i] - np.dot(self._theta, self._X[i])) ** 2 for i in range(self._X.shape[0])
        ]).mean()

    def loss(self, x, y):
        _y = self._call(x)
        return abs(y - _y) / (abs(y) + abs(_y))

    def gradient(self, x, y):
        res = []
        for j in range(len(x)):
            sm = np.dot(self._theta, x)
            f1 = -np.sign(y - sm) * x[j]
            f2 = np.sign(sm) * x[j]
            num = f1 * (abs(y) + abs(sm)) - f2 * abs(y - sm)
            den = (abs(y) + abs(sm)) ** 2
            res.append(num / den)
        return np.array(res)
