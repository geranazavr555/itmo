import math
import warnings

import numpy as np


def _unique(a):
    result = set()
    for sub in a:
        for x in sub:
            result.add(x)
    return result


class NaiveBayesClassifier:
    def __init__(self, X, Y, lambdas=None, alpha=None, classes=None, seed=None):
        self._X = X
        self._Y = Y
        self._n = len(X)
        self._lambdas = lambdas
        self._alpha = alpha
        self._objects = list(enumerate(_unique(X)))

        self._obj_to_i = dict()
        for obj_i, obj in self._objects:
            self._obj_to_i[obj] = obj_i

        self._classes = classes or list(sorted(set(Y)))
        self._classes_count = len(self._classes)
        self._seed = seed

        self._calced = False

        self._log_lambda_apriori_p = None
        self._p_obj_y = None
        self._class_to_idxs = dict()
        for klass in self._classes:
            self._class_to_idxs[klass] = []
        self._obj_to_class_to_cnt = dict()
        self._obj_to_cnt = dict()
        for _, obj in self._objects:
            self._obj_to_class_to_cnt[obj] = {klass: 0 for klass in self._classes}
            self._obj_to_cnt[obj] = 0

        self._result_precalc = []

    def _calc_log_lambda_apriori_p(self):
        self._log_lambda_apriori_p = np.zeros(self._classes_count)
        for y in self.Y:
            self._log_lambda_apriori_p[y] += 1
        self._log_lambda_apriori_p /= self.n

        # не логарифмировать лямбду
        self._log_lambda_apriori_p = np.log(self.lambdas) + np.log(self._log_lambda_apriori_p)

    def _calc_obj_y(self, obj_i, obj, y):
        positive_count = self._obj_to_class_to_cnt[obj][y]
        negative_count = len(self._class_to_idxs[y]) - positive_count
        self._p_obj_y[obj_i][y] = (positive_count + self.alpha) / (positive_count + negative_count + self.alpha * 2)

    def _calc(self):
        self._calc_log_lambda_apriori_p()
        self._result_precalc = [0.0 for _ in range(len(self._classes))]

        for i in range(self.n):
            self._class_to_idxs[self.Y[i]].append(i)

        for i in range(self.n):
            objs = set()
            for obj in self.X[i]:
                if obj not in objs:
                    self._obj_to_class_to_cnt[obj][int(self.Y[i])] += 1
                    self._obj_to_cnt[obj] += 1
                    objs.add(obj)

        self._p_obj_y = np.zeros((len(self._objects), self._classes_count))
        for obj_i, obj in self._objects:
            for y in self._classes:
                self._calc_obj_y(obj_i, obj, y)

        with warnings.catch_warnings():
            warnings.simplefilter("ignore")
            for y in self._classes:
                for obj_i, obj in self._objects:
                    self._result_precalc[y] += np.log(1.0 - self._p_obj_y[obj_i][y])

    def _call(self, x):
        result = np.copy(self._log_lambda_apriori_p) + self._result_precalc
        x = set(list(x))
        for y in self._classes:
            for obj in x:
                if obj in self._obj_to_i:
                    obj_i = self._obj_to_i[obj]
                    if self._p_obj_y[obj_i][y] == 1.0:
                        result = np.zeros(result.shape)
                        result[y] = 1.0
                        return result
                    result[y] -= np.log(1.0 - self._p_obj_y[obj_i][y])
                    result[y] += np.log(self._p_obj_y[obj_i][y])

        result = np.exp(result - np.max(result))
        return result / result.sum()

# лямбды --

    def __call__(self, x, hard=False):
        if not self._calced:
            self._calc()
            self._calced = True
        result = self._call(x)
        return result if not hard else self._classes[result.argmax()]

    @property
    def X(self):
        return self._X

    @property
    def Y(self):
        return self._Y

    @property
    def n(self):
        return self._n

    @property
    def lambdas(self):
        return self._lambdas

    @lambdas.setter
    def lambdas(self, value):
        self._lambdas = value
        self._calc_log_lambda_apriori_p()

    @property
    def alpha(self):
        return self._alpha

    @alpha.setter
    def alpha(self, value):
        self._alpha = value
        self._calced = False


class HashableToIntTransformer:
    def __init__(self):
        self._known_objects = dict()

    def __call__(self, obj):
        if obj not in self._known_objects:
            self._known_objects[obj] = len(self._known_objects)
        return self._known_objects[obj]
