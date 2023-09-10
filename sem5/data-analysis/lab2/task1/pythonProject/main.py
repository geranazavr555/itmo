import numpy as np

from interpolation import interpolation

print(interpolation.lagrange(np.array([1, 2, 3]), np.array([7, 2, 15])))