import numpy as np

from abc import abstractmethod, ABC


class Function(ABC):
    def __init__(self, *args):
        self._args = args
        self._calced_args = None
        self._result = None

    @property
    def args(self):
        if self._calced_args is None:
            self._calced_args = list(map(lambda x: x(), self._args))
        return self._calced_args

    def __call__(self):
        if self._result is None:
            self._result = self.f()
        return self._result

    @abstractmethod
    def f(self):
        pass

    @property
    @abstractmethod
    def shape(self):
        pass


class DifferentiableFunction(Function, ABC):
    def __init__(self, *args):
        super().__init__(*args)
        self._dX_mem = None

    @abstractmethod
    def _dX(self, dY):
        pass

    def dX(self, dY):
        if self._dX_mem is None:
            self._dX_mem = self._dX(dY)
            if not isinstance(self._dX_mem, list) and not isinstance(self._dX_mem, tuple):
                self._dX_mem = [self._dX_mem]
        return self._dX_mem


class Tnh(DifferentiableFunction):
    def __init__(self, arg):
        super().__init__(arg)

    def _dX(self, dY):
        return dY * (1 - self() ** 2)

    def f(self):
        return np.tanh(self.args[0])

    @property
    def shape(self):
        return self.args[0].shape


class Rlu(DifferentiableFunction):
    def __init__(self, alpha, arg):
        super().__init__(arg)
        self.alpha = alpha

    def _dx(self, x):
        return self.alpha if x < 0 else 1

    def _dX(self, dY):
        shape = self.shape
        result = np.zeros(shape)
        for ij in np.ndindex(*shape):
            result[ij] = self._dx(self.args[0][ij])
        return result * dY

    def _f(self, x):
        return self.alpha * x if x < 0 else x

    def f(self):
        shape = self.shape
        result = np.zeros(shape)
        for ij in np.ndindex(*shape):
            result[ij] = self._f(self.args[0][ij])
        return result

    @property
    def shape(self):
        return self.args[0].shape


class Mul(DifferentiableFunction):
    def __init__(self, a, b):
        super().__init__(a, b)

    def _dX(self, dY):
        return np.dot(dY, np.transpose(self.args[1])), np.dot(np.transpose(self.args[0]), dY)

    def f(self):
        return np.dot(self.args[0], self.args[1])

    @property
    def shape(self):
        return self.args[0].shape[0], self.args[1].shape[-1]


class Sum(DifferentiableFunction):
    def __init__(self, *args):
        super().__init__(*args)

    def _dX(self, dY):
        return [dY] * len(self.args)

    def f(self):
        result = np.zeros(self.shape)
        for arg in self.args:
            result += arg
        return result

    @property
    def shape(self):
        return self.args[0].shape


class Had(DifferentiableFunction):
    def __init__(self, *args):
        super().__init__(*args)

    def _dX(self, dY):
        results = []
        for i in range(len(self.args)):
            cur = np.ones(self.shape)
            for j in range(len(self.args)):
                if i != j:
                    cur *= self.args[j]
            cur *= dY
            results.append(cur)
        return results

    def f(self):
        result = np.ones(self.shape)
        for arg in self.args:
            result *= arg
        return result

    @property
    def shape(self):
        return self.args[0].shape


class Var(Function):
    def __init__(self):
        super().__init__()
        self._X = None
        self._shape = None

    def f(self):
        return self.X

    @property
    def shape(self):
        return self._shape

    @shape.setter
    def shape(self, value):
        self._shape = value

    @property
    def X(self):
        return self._X

    @X.setter
    def X(self, value):
        if value.shape != self.shape:
            raise RuntimeError
        self._X = value


class MatrixFunction:
    def __init__(self):
        self.nodes = []
        self.inputs = []
        self.outputs = []

    def add_node(self, cls, *args_ind):
        self.nodes.append(cls(*map(lambda arg_i: self.nodes[arg_i], args_ind)))

    def add_input_proto(self, *shape):
        node = Var()
        node.shape = shape
        self.nodes.append(node)
        self.inputs.append(node)

    def add_output(self, cls, *args_ind):
        self.add_node(cls, *args_ind)
        self.add_last_as_output()

    def add_last_as_output(self):
        self.outputs.append(self.nodes[-1])

    def set_input(self, i, X):
        self.inputs[i].X = X

    def set_dY(self, i, dY):
        self.outputs[i].dY_in = dY

    def get_input_shape(self, i):
        return self.inputs[i].shape

    def get_output_shape(self, i):
        return self.outputs[i].shape

    def calc_outputs(self):
        result = []
        for output in self.outputs:
            result.append(output())
        return result

    def calc_dX(self):
        for i in range(len(self.nodes) - 1, -1, -1):
            if isinstance(self.nodes[i], Var):
                continue
            dY_outs = self.nodes[i].dX(self.nodes[i].dY_in)
            for j, arg in enumerate(self.nodes[i]._args):
                if not hasattr(arg, "dY_in"):
                    arg.dY_in = dY_outs[j].copy()
                else:
                    arg.dY_in += dY_outs[j]
        return map(lambda x: (x.dY_in if hasattr(x, "dY_in") else np.zeros(x.shape)), self.inputs)


if __name__ == '__main__':
    n, m, k = map(int, input().split())

    function = MatrixFunction()

    for i in range(n):
        tokens = input().split()
        if tokens[0] in ("sum", "had"):
            tokens = [tokens[0]] + tokens[2:]
        if tokens[0] == "var":
            function.add_input_proto(*map(int, tokens[1:]))
            if i >= n - k:
                function.add_last_as_output()
        else:
            tokens[0] = tokens[0][0].upper() + tokens[0][1:]
            cls = globals()[tokens[0]]

            if tokens[0] == "Rlu":
                inv_alpha_copy = int(tokens[1])
                cls = lambda *args, **kwargs: Rlu(1 / inv_alpha_copy, *args, **kwargs)
                tokens = [tokens[0]] + tokens[2:]

            if i >= n - k:
                function.add_output(cls, *map(lambda x: int(x) - 1, tokens[1:]))
            else:
                function.add_node(cls, *map(lambda x: int(x) - 1, tokens[1:]))

    for i in range(m):
        a = []
        shape = function.get_input_shape(i)
        for j in range(shape[0]):
            a.append(list(map(int, input().split())))
        function.set_input(i, np.array(a, dtype="float64"))

    for output in function.calc_outputs():
        shape = output.shape
        for i in range(shape[0]):
            for j in range(shape[1]):
                print(output[i][j], end=" ")
            print()

    for i in range(k):
        a = []
        shape = function.get_output_shape(i)
        for j in range(shape[0]):
            a.append(list(map(int, input().split())))
        function.set_dY(i, np.array(a, dtype="float64"))

    for dX in function.calc_dX():
        shape = dX.shape
        for i in range(shape[0]):
            for j in range(shape[1]):
                print(dX[i][j], end=" ")
            print()
