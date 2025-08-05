class LevelData:
    def __init__(self, n_i, Numbers, a_i):
        self.n_i = n_i
        self.Numbers = Numbers
        self.a_i = a_i

def numberConcatenate(Numbers: list, a_i: int):
    First = Numbers[a_i - 1]
    remaining = Numbers[:a_i - 1] + Numbers[a_i:]

    sorted_remaining = sorted(remaining, key=lambda x: x*10, reverse=True) 
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
