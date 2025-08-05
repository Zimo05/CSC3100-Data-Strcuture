class Node:
    def __init__(self, performance, reliability):
        self.Performance = performance
        self.Reliability = reliability
        self.Total = performance + reliability

def main():
    numPro, numCus = map(int, input().split()) 

    performance = list(map(int, input().split()))
    reliability = list(map(int, input().split()))

    products = [Node(performance[i], reliability[i]) for i in range(numPro)]
    products.sort(key=lambda x: x.Total, reverse=True)

    answers = ""
    for _ in range(numCus):
        cusPer, cusRel = map(int, input().split())

        maxTotal = -1

        for node in products:
            if node.Performance >= cusPer and node.Reliability >= cusRel:
                maxTotal = node.Total
                break
        answers += str(maxTotal) + " "

    print(answers)

if __name__ == '__main__':
    main()