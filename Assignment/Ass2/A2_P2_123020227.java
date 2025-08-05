import java.util.*;

class Node {
    int num;
    List<int[]> data;

    public Node() {
        this.num = 0;
        this.data = new ArrayList<>();
    }

    public void add(int[] element) {
        this.data.add(element);
    }
}

public class Main {

    public static List<int[]> multiplyRows(Node rowA, List<Node> matrixB) {
        Map<Integer, Integer> result = new TreeMap<>();
        
        for (int[] entryA : rowA.data) {
            int colA = entryA[0];
            int valA = entryA[1];
            
            Node rowB = matrixB.get(colA);
            
            for (int[] entryB : rowB.data) {
                int colB = entryB[0];
                int valB = entryB[1];
                
                result.put(colB, result.getOrDefault(colB, 0) + valA * valB);
            }
        }

        List<int[]> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            if (entry.getValue() != 0) {
                resultList.add(new int[] {entry.getKey(), entry.getValue()});
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Node> matrixA = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            Node node = new Node();
            int num = scanner.nextInt();
            node.num = num;
            for (int j = 0; j < num; j++) {
                int col = scanner.nextInt();
                int val = scanner.nextInt();
                node.add(new int[] {col, val});
            }
            matrixA.add(node);
        }

        scanner.nextLine();

        m = scanner.nextInt();
        int p = scanner.nextInt();
        List<Node> matrixB = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            Node node = new Node();
            int num = scanner.nextInt();
            node.num = num;
            for (int j = 0; j < num; j++) {
                int col = scanner.nextInt();
                int val = scanner.nextInt();
                node.add(new int[] {col, val});
            }
            matrixB.add(node);
        }
        StringBuilder matrixC = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Node rowA = matrixA.get(i);
            List<int[]> resultData = multiplyRows(rowA, matrixB);
            
            StringBuilder rowString = new StringBuilder();
            rowString.append(resultData.size());
            for (int[] entry : resultData) {
                rowString.append(" ").append(entry[0]).append(" ").append(entry[1]);
            }
            matrixC.append(rowString).append("\n");
        }
        System.out.println(n + " " + p);
        System.out.print(matrixC.toString());

        scanner.close();
    }
}
