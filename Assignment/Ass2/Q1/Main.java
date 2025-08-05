import java.util.*;

public class Main {

    static class Node {
        int col;
        int value;
        Node next;

        Node(int col, int value) {
            this.col = col;
            this.value = value;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int m1 = sc.nextInt();
        List<Node> matrixA = new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            int c = sc.nextInt();
            Node head = null;
            Node current = null;
            for (int j = 0; j < c; j++) {
                int col = sc.nextInt();
                int val = sc.nextInt();
                Node newNode = new Node(col, val);
                if (head == null) {
                    head = newNode;
                    current = head;
                } else {
                    current.next = newNode;
                    current = current.next;
                }
            }
            matrixA.add(head);
        }

        sc.nextLine(); 

        int n2 = sc.nextInt();
        int m2 = sc.nextInt();
        List<Node> matrixB = new ArrayList<>();
        for (int i = 0; i < n2; i++) {
            int c = sc.nextInt();
            Node head = null;
            Node current = null;
            for (int j = 0; j < c; j++) {
                int col = sc.nextInt();
                int val = sc.nextInt();
                Node newNode = new Node(col, val);
                if (head == null) {
                    head = newNode;
                    current = head;
                } else {
                    current.next = newNode;
                    current = current.next;
                }
            }
            matrixB.add(head);
        }

        List<Node> matrixC = new ArrayList<>();
        int maxRows = Math.max(n1, n2);
        for (int i = 0; i < maxRows; i++) {
            Node headC = null;
            Node currentC = null;

            Node rowA = (i < n1) ? matrixA.get(i) : null;
            Node rowB = (i < n2) ? matrixB.get(i) : null;

            while (rowA != null || rowB != null) {
                if (rowA != null && (rowB == null || rowA.col < rowB.col)) {
                    // Only rowA has an element at the column
                    Node newNode = new Node(rowA.col, rowA.value);
                    if (headC == null) {
                        headC = newNode;
                        currentC = headC;
                    } else {
                        currentC.next = newNode;
                        currentC = currentC.next;
                    }
                    rowA = rowA.next;
                } else if (rowB != null && (rowA == null || rowB.col < rowA.col)) {
                    // Only rowB has an element at the column
                    Node newNode = new Node(rowB.col, rowB.value);
                    if (headC == null) {
                        headC = newNode;
                        currentC = headC;
                    } else {
                        currentC.next = newNode;
                        currentC = currentC.next;
                    }
                    rowB = rowB.next;
                } else if (rowA != null && rowB != null && rowA.col == rowB.col) {
                    // Both rowA and rowB have elements at the same column
                    int sum = rowA.value + rowB.value;
                    if (sum != 0) {
                        Node newNode = new Node(rowA.col, sum);
                        if (headC == null) {
                            headC = newNode;
                            currentC = headC;
                        } else {
                            currentC.next = newNode;
                            currentC = currentC.next;
                        }
                    }
                    rowA = rowA.next;
                    rowB = rowB.next;
                }
            }

            matrixC.add(headC);
        }

        // Output the result matrix C
        System.out.println(maxRows + " " + Math.max(m1, m2));
        for (int i = 0; i < maxRows; i++) {
            Node rowC = matrixC.get(i);
            int count = 0;
            Node current = rowC;
            while (current != null) {
                count++;
                current = current.next;
            }
            System.out.print(count);
            current = rowC;
            while (current != null) {
                System.out.print(" " + current.col + " " + current.value);
                current = current.next;
            }
            System.out.println();
        }

        sc.close();
    }
}