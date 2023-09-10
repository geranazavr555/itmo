import numpy as np
from collections import defaultdict


class MarkovChain:
    def __init__(self, X, Y, i_to_ch, ch_to_i):
        self._X = X
        self._Y = Y
        self._i_to_ch = i_to_ch
        self._ch_to_i = ch_to_i
        self._al = len(i_to_ch)

        self._graph = defaultdict(lambda: (defaultdict(lambda: 0)))
        self._cnts = defaultdict(lambda: 0)

        self._calced = False

    def deonehot_char(self, x):
        for i in range(len(x)):
            if x[i] == 1:
                return self._i_to_ch[i]
        raise Exception

    def deonehot(self, x):
        return "".join(map(self.deonehot_char, x))

    def __call__(self, x):
        if not self._calced:
            self._calc()
            self._calced = True
        return self._call(x)

    def _call(self, x):
        result = [0.0] * self._al
        for y in self._graph[x]:
            result[self._ch_to_i[y[-1]]] = self._graph[x][y]
        return np.array(result)

    def _calc(self):
        for i in range(len(self._X)):
            x = self.deonehot(self._X[i])
            ychr = self.deonehot_char(self._Y[i])
            y = x[1:] + ychr
            self._graph[x][y] += 1

        for x in self._graph:
            for y in self._graph[x]:
                self._cnts[x] += self._graph[x][y]

        for x in self._graph:
            for y in self._graph[x]:
                self._graph[x][y] /= self._cnts[x]
