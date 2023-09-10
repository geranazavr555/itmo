import numpy as np


class NGramsTransformer:
    def __init__(self, n):
        self._n = n

    @property
    def n(self):
        return self._n

    def __call__(self, objects):
        result = []
        for i in range(len(objects) - self.n + 1):
            subresult = []
            for j in range(self._n):
                subresult.append(objects[i + j])
            result.append(tuple(subresult))
        return result


if __name__ == '__main__':
    print(NGramsTransformer(1)("a"))
    print(NGramsTransformer(2)("a"))
    print(NGramsTransformer(3)("a"))