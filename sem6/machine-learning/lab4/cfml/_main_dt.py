import numpy as np

import dt


def get_stop_predicate(h):
    def stop_predicate(*args, depth, **kwargs):
        return depth >= h

    return stop_predicate


if __name__ == '__main__':
    m, k, h = map(int, input().split())
    n = int(input())
    X, Y = [], []
    for i in range(n):
        line = list(map(int, input().split()))
        X.append(line[:-1])
        Y.append(line[-1])

    tree = dt.DecisionTree(
        np.array(X, dtype=np.float64),
        np.array(Y, dtype=np.float64),
        get_stop_predicate(h),
        dt.IGain,
        0.0
    )
    tree.grow()

    print(len(tree))
    for node in tree.nodes:
        if len(node.children) > 0:
            print("Q", node.j + 1, node.threshold, node.children[1].id + 1, node.children[0].id + 1)
        else:
            print("C", int(node.value))
