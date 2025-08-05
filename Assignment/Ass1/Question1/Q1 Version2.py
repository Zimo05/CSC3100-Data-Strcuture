"""
t(num of test case)   
n(length)
n integer a_i(genetic sequence1)
m(num of DNA sequence)
s_j:(consisting of uppercase letters 'A' to 'Z')
    1
    2
    3
    ...
    m

"""
class LevelData:
    def __init__(self, t, gene, DNAs):
        self.t = t  
        self.gene = gene
        self.DNAs = DNAs

def checkMatch(gene, DNA):
    if len(gene) != len(DNA):
        return -1
    
    geneMap = dict()
    dnaMap = dict() 

    for i in range(len(gene)):
        geneChar = gene[i]
        dnaChar = DNA[i]

        if geneChar in geneMap:
            if geneMap[geneChar] != dnaChar:
                return -1
        else:
            geneMap[geneChar] = dnaChar

        if dnaChar in dnaMap:
            if dnaMap[dnaChar] != geneChar:
                return -1
        else:
            dnaMap[dnaChar] = geneChar

    return 1

def testCase(gene, DNAs):
    results = []
    for DNA in DNAs:
        if checkMatch(gene, DNA) < 0:
            results.append('NO')
        else:
            results.append('YES')
    return results

def main():
    Datas = []
    t = int(input())

    for _ in range(t):
        n = int(input())
        gene = list(map(int, input().split()))
        m = int(input())
        DNAs = []
        for _ in range(m):
            DNAs.append(input().strip()) 
        Datas.append(LevelData(t, gene, DNAs))
    
    for data in Datas:
        gene = data.gene
        DNAs = data.DNAs
        results = testCase(gene, DNAs)
        for result in results:
            print(result)

if __name__ == "__main__":
    main()
