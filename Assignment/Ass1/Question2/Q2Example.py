import random

def generate_input_data(num_lines=100):
    input_data = "10 100\n" 
    for _ in range(num_lines):
        n_i = random.randint(4, 10)
        Numbers = [str(random.randint(1, 99999)) for _ in range(n_i)]
        a_i = random.randint(1, n_i)
        input_data += f"{n_i} " + " ".join(Numbers) + f" {a_i}\n"
    return input_data

class LevelData:
    def __init__(self, n_i, Numbers, a_i):
        self.n_i = n_i
        self.Numbers = Numbers
        self.a_i = a_i

def sort(num1: str, num2: str) -> int:
    if num1 + num2 > num2 + num1:
        return -1
    elif num1 + num2 < num2 + num1:
        return 1
    else:
        return 0
    
def mergeSort(arr: list, sort) -> list:
    n = len(arr)
    if n < 2:
        return arr
    mid = n // 2
    left = arr[:mid]
    right = arr[mid:]
    left = mergeSort(left, sort) 
    right = mergeSort(right, sort)  
    merged = []
    i = j = 0
    while i < len(left) and j < len(right):
        if sort(left[i], right[j]) < 0:
            merged.append(left[i])
            i += 1
        else:
            merged.append(right[j])
            j += 1
    
    while i < len(left):
        merged.append(left[i])
        i += 1
    while j < len(right):
        merged.append(right[j])
        j += 1

    return merged

def numberConcatenate(Numbers: list, a_i: int) -> str:
    First = Numbers[a_i - 1]
    remaining = Numbers[:a_i - 1] + Numbers[a_i:]

    sorted_remaining = mergeSort(remaining, sort)
    result = First + ''.join(sorted_remaining)
    return result

if __name__ == '__main__':
    input_data = generate_input_data()
    data_lines = input_data.splitlines()
    k, T = map(int, data_lines[0].split())
    Datas = []
    
    for line in data_lines[1:T + 1]:
        data = line.split()
        n_i = int(data[0])
        Numbers = data[1:n_i + 1]
        a_i = int(data[-1])
        Datas.append(LevelData(n_i, Numbers, a_i))

    for data in Datas:
        Numbers = data.Numbers
        a_i = data.a_i
        result = numberConcatenate(Numbers, a_i)
        print(result)
