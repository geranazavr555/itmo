def _safe_div(x, y, f=0.0):
    return x / y if y != 0 else f


def _count_tp_fp_fn(cm):
    k = len(cm)
    res = []
    sum_rows = [0] * k
    sum_cols = [0] * k
    for i in range(k):
        for j in range(k):
            sum_rows[i] += cm[i][j]
            sum_cols[j] += cm[i][j]

    for i in range(k):
        res.append((cm[i][i], sum_cols[i] - cm[i][i], sum_rows[i] - cm[i][i]))
    return res


def _count_prec_recall(tp, fp, fn):
    return _safe_div(tp, tp + fp), _safe_div(tp, tp + fn)


def _count_f_score(prec, recall, beta=1.0):
    if beta ** 2 * prec + recall == 0:
        return 0.0
    return (1 + beta ** 2) * (prec * recall) / (beta ** 2 * prec + recall)


def _wavg(x, w=None):
    if w is None:
        w = [1] * len(x)
    assert len(x) == len(w)
    return sum([w[i] * x[i] for i in range(len(x))]) / sum(w)


def _count_classes(cm):
    return list(map(sum, cm))


def micro_avg_f_score(cm):
    tp_fp_fns = _count_tp_fp_fn(cm)
    tp, fp, fn = 0, 0, 0
    classes = _count_classes(cm)
    i = 0
    for _tp, _fp, _fn in tp_fp_fns:
        tp += _tp * classes[i]
        fp += _fp * classes[i]
        fn += _fn * classes[i]
        i += 1
    tp /= sum(classes)
    fp /= sum(classes)
    fn /= sum(classes)
    prec, rec = _count_prec_recall(tp, fp, fn)
    return _count_f_score(prec, rec)


def macro_avg_f_score(cm):
    prec_recs = list(map(lambda x: _count_prec_recall(*x), _count_tp_fp_fn(cm)))
    prec, recall = 0, 0
    classes = _count_classes(cm)
    i = 0
    for _prec, _recall in prec_recs:
        prec += _prec * classes[i]
        recall += _recall * classes[i]
        i += 1
    prec /= sum(classes)
    recall /= sum(classes)
    return _count_f_score(prec, recall)


def avg_f_score(cm):
    fscores = list(map(lambda y: _count_f_score(*y), map(lambda x: _count_prec_recall(*x), _count_tp_fp_fn(cm))))
    classes = _count_classes(cm)
    res = 0.0
    for i, f in enumerate(fscores):
        res += f * classes[i]
    return res / sum(classes)


def avg_f_scores(cm):
    return avg_f_score(cm), macro_avg_f_score(cm), micro_avg_f_score(cm)
