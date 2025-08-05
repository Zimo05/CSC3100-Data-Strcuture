import java.util.*;

class Node{
    int neighbour;
    long weight;
    Node(int neighbour, long weight){
        this.neighbour = neighbour;
        this.weight = weight;
    }
}
class Main{
    
    static void BFS(long[] distance, int start, List<List<Node>> Graph){
        int[] color = new int[Graph.size()];
        color[start] = 1;
        Queue<Integer> Q = new LinkedList<>();
        Q.add(start);
        while (!Q.isEmpty()){
            int u = Q.poll();
            for (Node node : Graph.get(u)){
                int n = node.neighbour;
                long w = node.weight;
                if (color[n] == 0){
                    distance[n] = distance[u] + w;
                    color[n] = 1;
                    Q.add(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int D = sc.nextInt();

        int[] keys = new int[D];
        for (int i = 0; i < D; i++) {
            keys[i] = sc.nextInt();
        }

        List<List<Node>> Graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            Graph.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i ++){
            int n1 = sc.nextInt();
            long w = sc.nextLong();
            int n2 = i + 1;
            Graph.get(n1).add(new Node(n2, w));
            Graph.get(n2).add(new Node(n1, w));
        }
// arbitrary key
        int key1 = keys[0];
        long[] distance1 = new long[N + 1];
        BFS(distance1, key1, Graph);

// 1st key
        long farthestDis1 = 0;
        int key2 = 0;
        for (int k : keys){
            if (distance1[k] > farthestDis1){
                farthestDis1 = distance1[k];
                key2 = k;
            } 
        }
// distance using 1st key
        long[] distanceA = new long[N + 1];
        BFS(distanceA, key2, Graph);

// find 2nd key
        long farthestDis2 = 0;
        int key3 = 0;
        for (int j : keys){
            if (distanceA[j] > farthestDis2){
                farthestDis2 = distanceA[j];
                key3 = j;
            } 
        }

// distance using 2nd key
        long[] distanceB = new long[N + 1];
        BFS(distanceB, key3, Graph);

        for (int X = 1; X <= N; X++) {
            System.out.println(Math.max(distanceA[X], distanceB[X]));
        }

    }
}
