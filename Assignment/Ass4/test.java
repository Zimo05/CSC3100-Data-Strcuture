import java.util.*;

class Node{
    int[][] pos;
    int[] blank;
    int moves;
    

}

class Main{
    static int ManhattanDistance(int x, int y, int GoalX, int GoalY){
        return Math.abs(x - GoalX) + Math.abs(y - GoalY); 
    }

    static Node getNeighbor(Node node){

    }

    static Node Asearch(PriorityQueue<Node> queue){

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Construct board
        int[][] Board = new int[3][3];
        //Construct goal as a map
        int[][] goalState = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };
        Map<Integer, int[]> goal = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int tile = goalState[i][j]; 
                goal.put(tile, new int[]{i, j});
            }
        }
        // Number of moves
        int[] numMove = new int[9];

        //Priority queue to track manhattanDistance 
        PriorityQueue<Node> queue = new PriorityQueue<>();

    }
    
}

