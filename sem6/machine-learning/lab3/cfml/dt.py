from collections import defaultdict
from abc import ABC, abstractmethod
import numpy as np


class GainFunction(ABC):
    def __init__(self, X, Y, W, indexes):
        super().__init__()
        self._X = X
        self._Y = Y
        self._W = W
        self._indexes = indexes

    @abstractmethod
    def _func(self, indexes):
        pass

    def __call__(self, splits):
        max_gain, max_args, max_kwargs = None, None, None
        before_func = self._func(self._indexes)
        for split_func, args, kwargs in splits:
            res = before_func
            parts = defaultdict(list)
            for i in self._indexes:
                parts[split_func(self._X[i])].append(i)
            for part_i, part in parts.items():
                res -= len(part) * self._func(part) / len(self._indexes)
            if max_gain is None or max_gain < res:
                max_gain = res
                max_args = args
                max_kwargs = kwargs
        return max_gain, max_args, max_kwargs

    def _class_proba(self, indexes):
        class_to_cnt = defaultdict(lambda: 0)
        for i in indexes:
            class_to_cnt[self._Y[i]] += self._W[i]
        return list(map(lambda cnt: cnt / len(indexes), class_to_cnt.values()))


class IGain(GainFunction):
    def _func(self, indexes):
        return -sum(map(lambda x: x * np.log(x), self._class_proba(indexes)))


class GiniGain(GainFunction):
    def _func(self, indexes):
        return 1 - sum(map(lambda x: x ** 2, self._class_proba(indexes)))


class DecisionTree:
    def __init__(self, X, Y, stop_predicate, gain_function_factory, gain_threshold, W=None, j_indexes=None,
                 i_indexes=None, splits_strategy="full"):
        self._X = X
        self._Y = Y
        self._W = W if W is not None else np.ones(Y.shape)
        self._classes = list(sorted(set(Y)))
        self._root = None
        self._nodes = []
        self._stop_predicate = stop_predicate
        self._gain_factory = gain_function_factory
        self._gain_threshold = gain_threshold
        self._j_indexes = j_indexes or list(range(0, self.features_count))
        self._i_indexes = i_indexes if i_indexes is not None else list(range(self.n))
        self._splits_strategy = splits_strategy

    def _new_node(self, node_class, *args, **kwargs):
        node = node_class(*args, node_id=len(self._nodes), **kwargs)
        self._nodes.append(node)
        return node

    def _classes_count_and_major(self, indexes):
        y_to_cnt = defaultdict(lambda: 0)
        for i in indexes:
            y_to_cnt[self.Y[i]] += self._W[i]
        mx, mxi = 0, 0
        for y, cnt in y_to_cnt.items():
            if mx < cnt:
                mx = cnt
                mxi = y
        return len(y_to_cnt), mxi

    def _splits(self, j, indexes):
        if self._splits_strategy == "full":
            return self.RealSplits(self.X, self.Y, j, indexes)
        else:
            return self.RealRandomSplits(self.X, self.Y, j, indexes, rng=np.random.default_rng(seed=0), tries=max(3, len(indexes) // 10))

    def _grow(self, indexes, depth=0):
        classes_count, major = self._classes_count_and_major(indexes)
        if classes_count == 1:
            return self._new_node(self.Leaf, major)

        if self._stop_predicate(self.X, self.Y, indexes, depth=depth, nodes_count=len(self._nodes)):
            return self._new_node(self.Leaf, major)

        gain = self._gain_factory(self.X, self.Y, self.W, indexes)

        max_gain, max_gain_args, max_gain_kwargs = gain(self._splits(self._j_indexes[0], indexes))
        for k in range(1, len(self._j_indexes)):
            j = self._j_indexes[k]
            cur_gain, args, kwargs = gain(self._splits(j, indexes))
            if cur_gain > max_gain:
                max_gain = cur_gain
                max_gain_args = args
                max_gain_kwargs = kwargs

        if max_gain < self._gain_threshold:
            return self._new_node(self.Leaf, major)

        node = self._new_node(self.RealBinaryNode, *max_gain_args, **max_gain_kwargs)
        next_to_indexes = defaultdict(list)
        for i in indexes:
            next_to_indexes[node.next(self.X[i])].append(i)

        if len(next_to_indexes) == 1:
            self._nodes[-1] = self.Leaf(major, node_id=node.id)
            return self._nodes[-1]

        for next_i in sorted(next_to_indexes.keys()):
            node.add_child(self._grow(next_to_indexes[next_i], depth=depth + 1))

        return node

    def grow(self):
        self._root = self._grow(self._i_indexes)

    def __call__(self, x):
        if self._root is None:
            self.grow()
        return self._root(x)

    @property
    def X(self):
        return self._X

    @property
    def Y(self):
        return self._Y

    @property
    def W(self):
        return self._W

    @property
    def n(self):
        return len(self.Y)

    def __len__(self):
        return len(self.nodes)

    @property
    def nodes(self):
        return self._nodes

    @property
    def features_count(self):
        return len(self.X[0]) if isinstance(self.X, list) else self.X.shape[1]

    class Node(ABC):
        def __init__(self, node_id):
            self._node_id = node_id
            self._children = []

        def add_child(self, node):
            self._children.append(node)

        @property
        def children(self):
            return self._children

        @property
        def id(self):
            return self._node_id

        @abstractmethod
        def __call__(self, x):
            pass

    class RealBinaryNode(Node):
        LESS = "less"
        MORE = "greater"

        def __init__(self, threshold, j, direction=LESS, *args, **kwargs):
            super().__init__(*args, **kwargs)
            self._threshold = threshold
            self._direction = direction
            self._j = j

        def next(self, x):
            if self._direction == self.LESS:
                return 1 if x[self._j] < self._threshold else 0
            else:
                return 1 if x[self._j] > self._threshold else 0

        def __call__(self, x):
            return self._children[self.next(x)](x)

        @property
        def threshold(self):
            return self._threshold

        @property
        def direction(self):
            return self._direction

        @property
        def j(self):
            return self._j

    class Leaf(Node):
        def __init__(self, klass, *args, **kwargs):
            super().__init__(*args, **kwargs)
            self._class = klass

        def __call__(self, x):
            return self._class

        @property
        def value(self):
            return self._class

    class Splits(ABC):
        def __init__(self, X, Y, j, indexes, *args, **kwargs):
            self._X = X
            self._Y = Y
            self._j = j
            self._indexes = indexes
            self._args = args
            self._kwargs = kwargs

        @abstractmethod
        def __iter__(self):
            pass

        @abstractmethod
        def __next__(self):
            pass

    class RealSplits(Splits):
        def __iter__(self):
            self._features_and_labels = []
            for i in self._indexes:
                self._features_and_labels.append((self._X[i][self._j], self._Y[i]))
            self._features_and_labels.sort(key=lambda x: (x[1], x[0]))
            self._idx = 0
            return self

        def __next__(self):
            while self._idx < len(self._features_and_labels) - 1 and \
                    (self._features_and_labels[self._idx][1] == self._features_and_labels[self._idx + 1][1]):
                self._idx += 1

            if self._idx >= len(self._features_and_labels) - 1:
                raise StopIteration

            threshold = (self._features_and_labels[self._idx][0] + self._features_and_labels[self._idx + 1][0]) / 2
            j_copy = self._j
            self._idx += 1
            return (lambda x: 1 if x[j_copy] < threshold else 0), (), {"threshold": threshold, "j": self._j}

    class RealRandomSplits(Splits):
        def __iter__(self):
            self._idx = 0
            return self

        def __next__(self):
            if self._idx > self._kwargs["tries"]:
                raise StopIteration
            self._idx += 1

            ind1 = self._kwargs["rng"].integers(0, len(self._indexes))
            ind2 = self._kwargs["rng"].integers(0, len(self._indexes))

            threshold = (self._X[ind1][self._j] + self._X[ind2][self._j]) / 2
            j_copy = self._j
            return (lambda x: 1 if x[j_copy] < threshold else 0), (), {"threshold": threshold, "j": self._j}


class RandomForest:
    def __init__(self, X, Y, tree_count, subset_size=None, features_count=None, W=None, gain_function_factory=IGain,
                 gain_threshold=0.0, seed=None):
        self._X = X
        self._Y = Y
        self._W = W
        self._gain_function_factory = gain_function_factory
        self._gain_threshold = gain_threshold
        self._tree_count = tree_count
        self._subset_size = subset_size or int(len(X))
        self._features_count = features_count or int(np.sqrt(len(X[0])))
        self._rng = np.random.default_rng(seed)

        self._trees = []

    @staticmethod
    def _stop_predicate(*args, **kwargs):
        return False

    def grow(self):
        for tree_i in range(self._tree_count):
            i_indexes = self._rng.integers(0, len(self._X), self._subset_size)
            j_indexes = self._rng.integers(0, len(self._X[0]), self._features_count)
            self._trees.append(DecisionTree(
                self._X,
                self._Y,
                self._stop_predicate,
                self._gain_function_factory,
                self._gain_threshold,
                W=self._W,
                j_indexes=j_indexes,
                i_indexes=i_indexes,
                splits_strategy="random"
            ))
            self._trees[-1].grow()

    def __call__(self, x):
        results = defaultdict(lambda: 0)
        for predict in map(lambda tree: tree(x), self._trees):
            results[predict] += 1

        max_cnt, max_cnt_i = 0, 0
        for i, cnt in results.items():
            if cnt > max_cnt:
                max_cnt = cnt
                max_cnt_i = i

        return max_cnt_i


class AdaBoostShortDecisionTree:
    def __init__(self, X, Y, steps):
        self._X = X
        self._Y = Y
        self._steps = steps

        self._trees = []
        self._b = []

    @staticmethod
    def _stop_predicate(*args, **kwargs):
        return kwargs["depth"] >= 3

    def _new_dt(self, W):
        result = DecisionTree(self._X, self._Y, self._stop_predicate, IGain, 0.0, W, splits_strategy="random")
        result.grow()
        return result

    @property
    def n(self):
        return len(self._Y)

    @staticmethod
    def _01_to_11(y):
        return -1.0 if y == 0.0 else +1.0

    @staticmethod
    def _11_to_01(y):
        return 0.0 if y == -1.0 else +1.0

    def calc(self):
        W = np.ones(self._Y.shape) / self.n
        for _ in range(self._steps):
            self._trees.append(self._new_dt(W))

            N = 0
            for i in range(self.n):
                y_pred = self._trees[-1](self._X[i])
                N += 0.0 if y_pred == self._Y[i] else W[i]

            b = (np.log((1 - N + 1e-9) / (N + 1e-9))) / 2
            self._b.append(b)

            for i in range(self.n):
                W[i] *= np.exp(-b * self._01_to_11(self._Y[i]) * self._01_to_11(self._trees[-1](self._X[i])))
            W /= W.sum()

    def _call(self, x):
        return self._11_to_01(np.sign(sum([self._b[i] * self._01_to_11(self._trees[i](x)) for i in range(self._steps)])))

    def __call__(self, x):
        if len(self._trees) == 0:
            self.calc()
        return self._call(x)

    def cut_steps(self, steps):
        return self.AdaBoostShortDecisionTreeCutSteps(self, steps)

    class AdaBoostShortDecisionTreeCutSteps:
        def __init__(self, parent, steps):
            self._parent = parent
            self._steps = steps

        def __call__(self, x):
            return self._parent._11_to_01(np.sign(sum([
                self._parent._b[i] * self._parent._01_to_11(self._parent._trees[i](x)) for i in range(self._steps)
            ])))