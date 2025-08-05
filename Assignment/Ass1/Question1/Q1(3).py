"""
t(num of test case)   

Each cases:
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

def checkMatch(gene, DNA):
    if len(gene) != len(DNA):
        return -1
    
    geneMap = {}
    dnaMap = {}
    
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

def main():
    t = int(input())  

    for _ in range(t):
        n = int(input())
        gene = list(map(int, input().split()))
        m = int(input())
        
        for _ in range(m):
            DNA = input().strip()
            dna_integers = [ord(char) for char in DNA]
            if checkMatch(gene, dna_integers) > 0:
                print("YES")
            else:
                print("NO")

if __name__ == "__main__":
    main()
