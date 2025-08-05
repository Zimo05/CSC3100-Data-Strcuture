import java.util.*;

class Node{
    int neighbour;
    long weight;
    boolean isKey;
    Node next;
    Node pre;

    Node(int neighbour, long weight, boolean isKey) {
        this.neighbour = neighbour;
        this.weight = weight;
        this.isKey = isKey;
        this.next = null;
        this.pre = null;
    }
}

class Main{
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int D = sc.nextInt();

        int[] keys = new int[N + 1];
        for (int i = 0; i < D; i++) {
            int key = sc.nextInt();
            keys[key] = 1;
        }

        Map<Integer, Node> Graph = new HashMap<>();

        
    }
}
