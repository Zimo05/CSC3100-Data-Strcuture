class Node:
    def __init__(self):
        self.num = 0
        self.data = []
    def add(self, element):
        self.data.append(element)

def Multiplication(row_A, matrix_B, m, p):
    



def main():
    n, m = map(int, input().split())
    matrix_A = [None] * n
    for i in range(n):
        node = Node()
        row_info = input().split() # List form: [num, []]
        num = int(row_info[0])
        node.num = num
        for j in range(num):
            col = row_info[1 + 2 * j]
            val = row_info[2 + 2 * j]
            node.add([col, val])
        matrix_A[i] = node
    
    input()

    m, p = map(int, input().split())
    matrix_B = [None] * m
    for i in range(m):
        node = Node()
        row_info = input().split()
        num = int(row_info[0])
        node.num = num
        for j in range(num):
            col = row_info[1 + 2 * j]
            val = row_info[2 + 2 * j]
            node.add([col, val])
        matrix_B[i] = node

    matrix_C = ""
    

     

