import math
import random

random.seed(0)

class MatrixNode:
    def __init__(self):
        self.num = 0
        self.data = []
    def add(self, element):
        self.data.append(element)

def dot_product(v, w):
    return sum(v[i] * w[i] for i in range(len(v)))

def vec_norm(vector):
    return math.sqrt(sum(x * x for x in vector))

def normalize(vector):
    norm = vec_norm(vector)
    for i in range(len(vector)):
        vector[i] /= norm
    return vector

def multiply(matrix_A, vector):
    return [sum(val * vector[col] for col, val in row_node.data) for row_node in matrix_A]

def power_iteration(matrix_A, tolerance, max_epoch):
    n = len(matrix_A)
    pre_eigenvector = [random.uniform(-1, 1) for _ in range(n)]
    normalize(pre_eigenvector)
    lambda_prev = 0.0
    for _ in range(max_epoch):
        w = multiply(matrix_A, pre_eigenvector)
        lambda_new = dot_product(pre_eigenvector, w)
        normalize(w)
        if abs(lambda_new - lambda_prev) < tolerance:
            return lambda_new, w
        pre_eigenvector = w
        lambda_prev = lambda_new
    return lambda_new, w

def main():
    m, n = map(int, input().split())
    matrix_A = [None] * m
    for i in range(m):
        row_A = MatrixNode()
        line = input().split()
        num = int(line[0])
        row_A.num = num
        for j in range(num):
            col = int(line[1 + 2 * j])
            val = int(line[2 + 2 * j])
            row_A.add([col, val])
        matrix_A[i] = row_A
    tolerance = 1e-9
    max_epoch = 600
    eigenvalue, eigenvector = power_iteration(matrix_A, tolerance, max_epoch)
    print(abs(eigenvalue))

if __name__ == "__main__":
    main()