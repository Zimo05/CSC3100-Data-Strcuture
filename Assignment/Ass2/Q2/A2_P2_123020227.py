class Node:
    def __init__(self):
        self.num = 0
        self.data = []
    def add(self, element):
        self.data.append(element)

def multiply_rows(row_a_node, matrix_b):
    result = {}
    for col_a, val_a in row_a_node.data:
        row_b_node = matrix_b[col_a]
        for col_b, val_b in row_b_node.data:
            if col_b not in result:
                result[col_b] = 0
            result[col_b] += val_a * val_b
    result_list = []
    for col in sorted(result.keys()): 
        if result[col] != 0:
            result_list.append([col, result[col]])
    return result_list

def main():
    n, m = map(int, input().split())
    matrix_a = [None] * n
    matrix_b = [None] * m
    for i in range(n):
        line = input().split()
        node = Node()
        num = int(line[0])
        node.num = num
        for j in range(num):
            col = int(line[1 + 2 * j])
            val = int(line[2 + 2 * j])
            node.add([col, val])
        matrix_a[i] = node
    input()
    m, p = map(int, input().split())
    for i in range(m):
        line = input().split()
        node = Node()
        num = int(line[0])
        node.num = num
        for j in range(num):
            col = int(line[1 + 2 * j])
            val = int(line[2 + 2 * j])
            node.add([col, val])
        matrix_b[i] = node

    matrixC = ""
    front = 0
    for i in range(n):
        row_a_node = matrix_a[i]
        result_data = multiply_rows(row_a_node, matrix_b, m, p)
        row_string = str(len(result_data))
        for col, val in result_data:
            row_string += f" {col} {val}"
        matrixC += row_string + "\n"
    print(f"{n} {p}")
    print(matrixC, end="")

if __name__ == "__main__":
    main()
