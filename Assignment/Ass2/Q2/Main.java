import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class Node{
    public int num;
    public LinkedList<ArrayList<Integer>> row_data;
    public Node next;

    public Node() {
        this.num = 0;
        this.row_data = new LinkedList<>();
        this.next = null;
    }
}

class Main{

    public static void Multiplication(LinkedList<int[]> row_A, ArrayList<int[]> Matrix_B, int m, int p){
        

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        LinkedList<Node> Matrix_A = new LinkedList<>();
        for (int i = 0; i < n; i ++){
            int num = scanner.nextInt();
            Node row_info = new Node();
            row_info.num = num;
            for (int j = 0; j < num; j ++){
                int col = scanner.nextInt();
                int val = scanner.nextInt();
                ArrayList<Integer> entry = new ArrayList<>();
                entry.add(col);
                entry.add(val);
                row_info.row_data.add(entry);
            }
            Matrix_A.add(row_info);
        
        }

        scanner.nextLine();

        m = scanner.nextInt();
        int p = scanner.nextInt();
        LinkedList<Node> Matrix_B = new LinkedList<>();
        for (int i = 0; i < m; i ++){
            int num = scanner.nextInt();
            Node row_info = new Node();
            row_info.num = num;
            for (int j = 0; j < num; j ++){
                int col = scanner.nextInt();
                int val = scanner.nextInt();
                ArrayList<Integer> entry = new ArrayList<>();
                entry.add(col);
                entry.add(val);
                row_info.row_data.add(entry);
            }
            Matrix_B.add(row_info);
        }
        String Matrix_C = new String();
        
        Node head = Matrix_A.getFirst();
        while (head != null){
        }
    }
}