import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {
    public static List<Integer> topologicalSort(int V, List<List<Integer>> Graph){
        int[] Indegree = new int[V];
        List<Integer> sortedArr = new ArrayList<>();
        for (int i = 0; i < V; i ++){
            for (int v: Graph.get(i)){
                Indegree[v]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++){
            if (Indegree[i] == 0){
                queue.add(i);
            }
        }
        while(!queue.isEmpty()){
            int u = queue.poll();
            sortedArr.add(u);
            for (int neighbor : Graph.get(u)){
                Indegree[neighbor]--;
                if (Indegree[neighbor] == 0){
                    queue.add(neighbor);
                }
            }
        }
        return sortedArr;
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Define edges (u -> v)
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        List<Integer> result = topologicalSort(V, adj);
        System.out.println("Topological Order: " + result);
    }
}

