def check_match(genes, fragment):
    # Check length
    if len(genes) != len(fragment):
        return False
    fragment_mapping = {}
    # Iterate through fragment
    for i in range(len(genes)):
        # Convert the number to a letter
        gene_char = chr(genes[i] + ord('A') - 1)  # Map number to letters ('1' -> 'A', '2' -> 'B', etc.)
        fragment_char = fragment[i]
        
        # Check the mapping for gene sequence
        if fragment_char not in fragment_mapping:
            fragment_mapping[fragment_char] = gene_char
        elif fragment_mapping.get(fragment_char) != gene_char:
            return False
    return True

def test_case(n, gene_sequence, m, fragments):
    results = []
    for fragment in fragments:
        if check_match(gene_sequence, fragment):
            results.append("YES")
        else:
            results.append("NO")
    return results

def main():
    t = int(input())
    for _ in range(t):
        # gene sequence
        n = int(input())
        gene_sequence = list(map(int, input().split()))
        # DNA sequence
        m = int(input())
        fragments = [input().strip() for _ in range(m)]
        results = test_case(n, gene_sequence, m, fragments)
    for result in results:
        print(result)

if __name__ == "__main__":
    main()
