import os
import os.path
import pathlib
from collections import namedtuple

import numpy as np


def load_data_lab2(path):
    with open(path, "r", encoding="utf-8") as file:
        lines = file.readlines()
        feature_count = int(lines[0])
        train_n = int(lines[1])
        lines = lines[2:]

        train_X = []
        train_Y = []
        for i in range(train_n):
            row = list(map(float, lines[i].split()))
            assert len(row) == feature_count + 1
            train_X.append(row[:-1])
            train_Y.append(row[-1])

        lines = lines[train_n:]

        test_n = int(lines[0])
        lines = lines[1:]
        test_X = []
        test_Y = []
        for i in range(test_n):
            row = list(map(float, lines[i].split()))
            assert len(row) == feature_count + 1
            test_X.append(row[:-1])
            test_Y.append(row[-1])

        return np.array(train_X), np.array(train_Y), np.array(test_X), np.array(test_Y)


Message = namedtuple("Message", ("id1", "id2", "label", "subject", "content"))


class Lab4DataProvider:
    def __init__(self, path):
        self._path = pathlib.Path(path)
        self._folds = []

    def load(self):
        parts = filter(lambda x: x.name.startswith("part"),
                       filter(os.path.isdir, map(lambda x: self._path / x, os.listdir(self._path))))
        for part_path in parts:
            self._folds.append(self._load_part(pathlib.Path(part_path)))
        return self

    def _load_part(self, part_path):
        msgs = map(pathlib.Path,
                   filter(lambda x: x.name.endswith(".txt"),
                          filter(os.path.isfile, map(lambda x: part_path / x, os.listdir(part_path)))))
        part = []
        for message_path in msgs:
            name = message_path.name[:-len(".txt")]
            if "legit" in name:
                id1 = int(name[:name.find("legit")])
                id2 = int(name[name.find("legit") + 5:])
                label = 0
            elif "spmsg" in name:
                id1 = int(name[:name.find("spmsg")])
                id2 = int(name[name.find("spmsg") + 5:])
                label = +1
            else:
                raise RuntimeError

            part.append(Message(id1, id2, label, *self._read_message(message_path)))

        return part

    @staticmethod
    def _read_message(message_path):
        with open(message_path, "r", encoding="cp1251") as file:
            lines = file.readlines()
            subject = list(map(int, lines[0][len("Subject: "):-1].split()))
            content = list(map(int, lines[2][:-1].split()))
            return subject, content

    @property
    def folds(self):
        return self._folds

    def apply_transformer(self, msg_transformer=None, fold_transformer=None, save=False):
        result = []
        for fold in self._folds:
            subresult = []
            for msg in fold:
                subresult.append(msg_transformer(msg) if msg is not None else msg)
            if fold_transformer is not None:
                subresult = fold_transformer(subresult)
            result.append(subresult)
        if save:
            self._folds = result
            return self
        else:
            return result


if __name__ == '__main__':
    x = Lab4DataProvider(r"C:\Programing\sem6\ml\lab4\data").load()
    print(x)
