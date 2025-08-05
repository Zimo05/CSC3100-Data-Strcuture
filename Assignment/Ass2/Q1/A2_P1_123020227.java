import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Node {
    int num;
    List<int[]> data;

    public Node() {
        this.num = 0;
        this.data = new ArrayList<>();
    }

    public void add(int[] element) {
        data.add(element);
    }
}

public class Main {

    public static void addition(Node row_a_node, Node row_b_node) {
        List<int[]> result = new ArrayList<>();
        int ind_row_a = 0;
        int ind_row_b = 0;

        while (ind_row_a < row_a_node.data.size() || ind_row_b < row_b_node.data.size()) {
            if (ind_row_a < row_a_node.data.size() && (ind_row_b >= row_b_node.data.size() ||
                    row_a_node.data.get(ind_row_a)[0] < row_b_node.data.get(ind_row_b)[0])) {
                result.add(new int[]{row_a_node.data.get(ind_row_a)[0], row_a_node.data.get(ind_row_a)[1]});
                ind_row_a++;
            } else if (ind_row_b < row_b_node.data.size() && (ind_row_a >= row_a_node.data.size() ||
                    row_b_node.data.get(ind_row_b)[0] < row_a_node.data.get(ind_row_a)[0])) {
                result.add(new int[]{row_b_node.data.get(ind_row_b)[0], row_b_node.data.get(ind_row_b)[1]});
                ind_row_b++;
            } else {
                int sum_value = row_a_node.data.get(ind_row_a)[1] + row_b_node.data.get(ind_row_b)[1];
                if (sum_value != 0) {
                    result.add(new int[]{row_a_node.data.get(ind_row_a)[0], sum_value});
                }
                ind_row_a++;
                ind_row_b++;
            }
        }

        row_a_node.data = result;
        row_a_node.num = result.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();  // Consume the rest of the line after reading integers

        Node[] matrix_a = new Node[n];
        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            Node node = new Node();
            int num = Integer.parseInt(line[0]);
            node.num = num;
            for (int j = 0; j < num; j++) {
                int col = Integer.parseInt(line[1 + 2 * j]);
                int val = Integer.parseInt(line[2 + 2 * j]);
                node.add(new int[]{col, val});
            }
            matrix_a[i] = node;
        }

        sc.nextLine(); // Skip the blank line
        sc.nextLine(); // Skip the blank line

        System.out.println(n + " " + m);

        int front = 0;
        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            Node row_b_node = new Node();
            int num = Integer.parseInt(line[0]);
            row_b_node.num = num;
            for (int j = 0; j < num; j++) {
                int col = Integer.parseInt(line[1 + 2 * j]);
                int val = Integer.parseInt(line[2 + 2 * j]);
                row_b_node.add(new int[]{col, val});
            }

            Node row_a_node = matrix_a[front];
            addition(row_a_node, row_b_node);

            System.out.print(row_a_node.num);
            for (int[] entry : row_a_node.data) {
                System.out.print(" " + entry[0] + " " + entry[1]);
            }
            System.out.println();
            front++;
        }

        sc.close();
    }
}
