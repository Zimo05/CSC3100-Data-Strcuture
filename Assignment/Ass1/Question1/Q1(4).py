t=int(input())
n=[]
m=[]
s=[]
d=[]
output=[]
for _ in range(t):
    lenth=int(input())
    n.append(lenth)

    ge_sequence = list(map(int, input().split()))  # 不要把数据预处理写进算法（check）里，所有数据在预处理部份全部写好，不然可能会出现index对应错误
    s.append(ge_sequence) # [[ge 1], [ge 2], ...]
    m_value = int(input())  
    m.append(m_value) # [m1, m2, ...]

    dna_sequences = []
    for _ in range(m_value):
        dna = input() 
        dna_sequences.append(dna) # [case 1.1, case 1.2, ...]
    d.append(dna_sequences) # 一个case的dna存储在一个list里，[[case 1], [case 2], ...]


def check(se, dna):
    dic = {} 
    reverse_dic = {}
    
    for i in range(len(dna)):
        
        if dna[i] in dic:
            if dic[dna[i]] != se[i]:  
                return False
        else:
            dic[dna[i]] = se[i]

        if se[i] in reverse_dic:
            if reverse_dic[se[i]] != dna[i]:  
                return False
        else:
            reverse_dic[se[i]] = dna[i] 
    
    return True

      
for case in range(t):
    dna_list = d[case]
    for i in range(m[case]): 
        if len(dna_list[i]) == n[case]: 
            if check(s[case], dna_list[i]):
                output.append('YES')
            else:
                output.append('NO')
        else:
            output.append('NO')


for result in output:
    print(result)
