import numpy as np

def _nadaraya_watson_fixed(x_train, y_train, ro, K, h):
    assert len(x_train) == len(y_train)
    n = len(x_train)
    
    def _result_func(x):
        sum1 = 0.0
        sum2 = 0.0
        for i in range(n):
            k_val = K(ro(x_train[i], x) / h)
            sum1 += k_val * y_train[i]
            sum2 += k_val
        return sum1 / sum2
    
    return _result_func        
        

def _nadaraya_watson_non_fixed(x_train, y_train, ro, K, k):
    assert len(x_train) == len(y_train)
    n = len(x_train)
    assert k + 1 < n
    
    def _kth(x):
        # x_train_copy.sort(key = lambda _x: ro(_x, x))
        return (x_train[np.apply_along_axis(lambda _x: ro(_x, x), 1, x_train).argsort()])[k + 1]
        
    
    def _result_func(x):
        sum1 = 0.0
        sum2 = 0.0
        for i in range(n):
            k_val = K(ro(x_train[i], x) / ro(x_train[i], _kth(x_train[i])))
            sum1 += k_val * y_train[i]
            sum2 += k_val
        return sum1 / sum2
    
    return _result_func
    
def nadaraya_watson(x_train, y_train, ro, K, theta, is_fixed):
    if is_fixed:
        return _nadaraya_watson_fixed(x_train, y_train, ro, K, theta)
    else:
        return _nadaraya_watson_non_fixed(x_train, y_train, ro, K, theta)
