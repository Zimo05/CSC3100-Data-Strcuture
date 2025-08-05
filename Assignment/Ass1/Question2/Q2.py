class LevelData:
    def __init__(self, n_i, Numbers, a_i):
        self.n_i = n_i
        self.Numbers = Numbers
        self.a_i = a_i

def merge_sort(blocks, comparator):
    if len(blocks) <= 1:
        return blocks
    mid = len(blocks) // 2
    left = merge_sort(blocks[:mid], comparator)  # Recursively sort the left half
    right = merge_sort(blocks[mid:], comparator)  # Recursively sort the right half
    
    # Merge the sorted halves
    return merge(left, right, comparator)

def merge(left, right, comparator):
    result = []
    while left and right:
        if comparator(left[0], right[0]) <= 0:  # Compare the first element of each
            result.append(left.pop(0))
        else:
            result.append(right.pop(0))
    
    # Append the remaining elements from left or right
    result.extend(left)
    result.extend(right)
    
    return result

def custom_comparator(a, b):
    # Comparator function for sorting
    # Compare two numbers by their string concatenation
    if a + b > b + a:
        return -1  # a should come before b
    elif a + b < b + a:
        return 1   # b should come before a
    else:
        return 0   # a and b are equal in terms of concatenation order

def numberConcatenate(Numbers: list, a_i: int):
    # Extract the first element (fixed block)
    First = Numbers[a_i - 1]
    # Remaining numbers excluding the fixed block
    remaining = Numbers[:a_i - 1] + Numbers[a_i:]

    # Sort the remaining numbers using merge sort and the custom comparator
    sorted_remaining = merge_sort(remaining, custom_comparator)

    # Concatenate the fixed block and sorted remaining blocks
    result = First + ''.join(sorted_remaining)
    return result

def main():
    k, T = map(int, input().split())  # Read input
    Datas = []
    
    for _ in range(T):  # For each test case
        data = input().split()
        n_i = int(data[0])  # Number of numbers in the test case
        Numbers = data[1:n_i+1]  # List of numbers as strings
        a_i = int(data[-1])  # The index of the fixed block
        Datas.append(LevelData(n_i, Numbers, a_i))  # Store test case in Datas

    for data in Datas:
        Numbers = data.Numbers
        a_i = data.a_i
        result = numberConcatenate(Numbers, a_i)  # Get the result for this test case
        print(result)  # Print the result

if __name__ == '__main__':
    main()  # Call main function to execute the program
