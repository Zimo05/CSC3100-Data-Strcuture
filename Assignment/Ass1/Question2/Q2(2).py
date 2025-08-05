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

def main():
    k, T = map(int, input().split())
    Datas = []
    
    for _ in range(T):  
        data = input().split()
        n_i = int(data[0])
        Numbers = data[1:n_i+1]  
        a_i = int(data[-1])
        Datas.append(LevelData(n_i, Numbers, a_i))

    for data in Datas:
        Numbers = data.Numbers
        a_i = data.a_i
        result = numberConcatenate(Numbers, a_i)
        print(result)


if __name__ == '__main__':
    main()
