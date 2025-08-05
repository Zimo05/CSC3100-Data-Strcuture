class Node:
    def __init__(self):
        self.num = 0
        self.data = []

    def add(self, element):
        self.data.append(element)

def addition(row_a_node, row_b_node):
    result = []
    ind_row_a = 0
    ind_row_b = 0
    
    while ind_row_a < len(row_a_node.data) or ind_row_b < len(row_b_node.data):
        if (ind_row_a < len(row_a_node.data) and 
            (ind_row_b >= len(row_b_node.data) or 
            row_a_node.data[ind_row_a][0] < row_b_node.data[ind_row_b][0])):
            result.append([row_a_node.data[ind_row_a][0], row_a_node.data[ind_row_a][1]])
            ind_row_a += 1
        elif (ind_row_b < len(row_b_node.data) and 
              (ind_row_a >= len(row_a_node.data) or 
               row_b_node.data[ind_row_b][0] < row_a_node.data[ind_row_a][0])):
            result.append([row_b_node.data[ind_row_b][0], row_b_node.data[ind_row_b][1]])
            ind_row_b += 1
        else:
            sum_value = row_a_node.data[ind_row_a][1] + row_b_node.data[ind_row_b][1]
            if sum_value != 0:
                result.append([row_a_node.data[ind_row_a][0], sum_value])
            ind_row_a += 1
            ind_row_b += 1
    row_a_node.data = result
    row_a_node.num = len(result)

def main():
    n, m = map(int, input().split())
    matrix_a = [None] * n
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
    input()
    print(f"{n} {m}")
    front = 0
    for _ in range(n):
        line = input().split()
        row_b_node = Node()
        num = int(line[0])
        row_b_node.num = num
        for j in range(num):
            col = int(line[1 + 2 * j])
            val = int(line[2 + 2 * j])
            row_b_node.add([col, val])
        row_a_node = matrix_a[front]
        addition(row_a_node, row_b_node)
        print(row_a_node.num, end="")
        for col, val in row_a_node.data:
            print(f" {col} {val}", end="")
        print()
        front += 1

if __name__ == "__main__":
    main()