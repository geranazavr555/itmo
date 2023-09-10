import numpy as np
from functools import cache


# http://cs229.stanford.edu/materials/smo.pdf
class SimplifiedSMO:
    def __init__(self, X, Y, c, kernel, max_iters=100, tol=1e-5, eps=1e-5, seed=None):
        self._X = X
        self._Y = Y
        self._c = c
        self._kernel = kernel

        self._mem_kernel = cache(lambda i, j: kernel(self._X[i], self._X[j]))

        self._max_iters = max_iters
        self._tol = tol
        self._eps = eps
        self._rng = np.random.default_rng(seed)

        self._n = self._X.shape[0]
        self._alpha = np.zeros(self._n)
        self._b = 0

        self._calced = False
        self._non_zero_idxs = set()

    def _get_j(self, i):
        while True:
            j = self._rng.integers(0, self._n)
            if i != j:
                return j

    def _calc(self):
        passes = 0
        while passes < self._max_iters:
            # print(passes)
            changed = 0
            for i in range(self._n):
                e_i = self._call_idx(i) - self._Y[i]
                if self._Y[i] * e_i < -self._tol and self._alpha[i] < self._c \
                        or self._Y[i] * e_i > self._tol and self._alpha[i] > 0:
                    j = self._get_j(i)
                    e_j = self._call_idx(j) - self._Y[j]
                    alpha_i_old = self._alpha[i]
                    alpha_j_old = self._alpha[j]

                    if self._Y[i] == self._Y[j]:
                        l = max(0, alpha_i_old + alpha_j_old - self._c)
                        h = min(self._c, alpha_i_old + alpha_j_old)
                    else:
                        l = max(0, alpha_j_old - alpha_i_old)
                        h = min(self._c, self._c + alpha_j_old - alpha_i_old)
                    if l == h:
                        continue

                    eta = 2 * self._mem_kernel(i, j) - self._mem_kernel(i, i) - self._mem_kernel(j, j)
                    if eta >= 0:
                        continue

                    ## a(x) = sign (\sum lambda i y i <x, xi> + b)

                    self._alpha[j] -= self._Y[j] * (e_i - e_j) / eta
                    self._alpha[j] = min(h, max(self._alpha[j], l))

                    if abs(self._alpha[j] - alpha_j_old) < self._eps:
                        self._alpha[j] = alpha_j_old
                        continue

                    self._alpha[i] += self._Y[i] * self._Y[j] * (alpha_j_old - self._alpha[j])

                    if abs(self._alpha[i]) > 0.0:
                        self._non_zero_idxs.add(i)
                    elif i in self._non_zero_idxs:
                        self._non_zero_idxs.remove(i)
                    if abs(self._alpha[j]) > 0.0:
                        self._non_zero_idxs.add(j)
                    elif j in self._non_zero_idxs:
                        self._non_zero_idxs.remove(j)

                    # noinspection DuplicatedCode
                    b1 = self._b - e_i - \
                         self._Y[i] * (self._alpha[i] - alpha_i_old) * self._mem_kernel(i, i) - \
                         self._Y[j] * (self._alpha[j] - alpha_j_old) * self._mem_kernel(i, j)
                    # noinspection DuplicatedCode
                    b2 = self._b - e_j - \
                         self._Y[i] * (self._alpha[i] - alpha_i_old) * self._mem_kernel(i, j) - \
                         self._Y[j] * (self._alpha[j] - alpha_j_old) * self._mem_kernel(j, j)
                    self._b = b1 if 0 < self._alpha[i] < self._c else \
                        b2 if 0 < self._alpha[j] < self._c else (b1 + b2) / 2
                    changed += 1

            if changed == 0:
                passes += 1
            else:
                passes = 0
        self._calced = True

    def _call_internal_idx(self, i, idxs):
        result = 0.0
        for j in idxs:
            result += self._alpha[j] * self._Y[j] * self._mem_kernel(i, j)
        return np.sign(result + self._b)

    def _call_internal(self, x, idxs):

        # \sum lambda_i y_i K(x, x_i)
        result = 0.0
        for i in idxs:
            result += self._alpha[i] * self._Y[i] * self._kernel(x, self._X[i])
        return np.sign(result + self._b)

        # return np.sign(
        #     np.array([
        #         self._alpha[i] * self._Y[i] * self._kernel(x, self._X[i])
        #         for i in idxs
        #     ]).sum() + self._b
        # )

    def _call(self, x):
        return self._call_internal(x, self._non_zero_idxs)

    def _call_idx(self, i):
        return self._call_internal_idx(i, self._non_zero_idxs)

    def __call__(self, x):
        if not self._calced:
            self._calc()
        return self._call_internal(x, self._non_zero_idxs)

    @property
    def w0(self):
        return -self._b

    @property
    def lambdas(self):
        return self._alpha


_kernels = {
    "linear": lambda: (lambda x, y: np.dot(x, y)),
    "polynomial": lambda d: (lambda x, y: (np.dot(x, y) + 1) ** d),
    "gaussian": lambda beta: (lambda x, y: np.exp(-beta * np.linalg.norm(x - y) ** 2))
}


def kernel(name, *args, **kwargs):
    return _kernels[name](*args, *kwargs)
