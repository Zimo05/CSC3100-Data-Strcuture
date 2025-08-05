import java.util.*;

class LevelData {
    public int t;
    public List<Integer> gene;
    public List<String> DNAs;

    public LevelData(int t, List<Integer> gene, List<String> DNAs) {
        this.t = t;
        this.gene = gene;
        this.DNAs = DNAs;
    }
}

public class Main {
    
    public static int checkMatch(List<Integer> gene, String DNA) {
        if (gene.size() != DNA.length()) {
            return -1;
        }
        
        Map<Character, Character> geneMap = new HashMap<>();
        Map<Character, Character> dnaMap = new HashMap<>();

        for (int i = 0; i < gene.size(); i++) {
            char geneChar = (char)(int)gene.get(i);
            char dnaChar = DNA.charAt(i);

            if (geneMap.containsKey(geneChar)) {
                if (geneMap.get(geneChar) != dnaChar) {
                    return -1;
                }
            } else {
                geneMap.put(geneChar, dnaChar);
            }

            if (dnaMap.containsKey(dnaChar)) {
                if (dnaMap.get(dnaChar) != geneChar) {
                    return -1;
                }
            } else {
                dnaMap.put(dnaChar, geneChar);
            }
        }

        return 1;
    }

    public static List<String> testCase(List<Integer> gene, List<String> DNAs) {
        List<String> results = new ArrayList<>();
        for (String DNA : DNAs) {
            if (checkMatch(gene, DNA) < 0) {
                results.add("NO");
            } else {
                results.add("YES");
            }
        }
        return results;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<LevelData> Datas = new ArrayList<>();

        int t = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            List<Integer> gene = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                gene.add(scanner.nextInt());
            }
            scanner.nextLine(); 

            int m = scanner.nextInt();
            scanner.nextLine(); 
            List<String> DNAs = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                DNAs.add(scanner.nextLine().strip());
            }

            Datas.add(new LevelData(t, gene, DNAs));
        }

        for (LevelData data : Datas) {
            List<String> results = testCase(data.gene, data.DNAs);
            for (String result : results) {
                System.out.println(result);
            }
        }

        scanner.close();
    }
}