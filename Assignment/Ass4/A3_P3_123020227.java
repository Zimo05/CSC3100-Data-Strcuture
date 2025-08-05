import java.util.*;

class Main{
    static final int N = 3;
    static final int[] row_dir = {-1, 1, 0, 0};
    static final int[] col_dir = {0, 0, -1, 1};

    static class State {
        int[][] board;      
        int blankRow;      
        int blankCol;
        int moves;       
        int priority;    

        public State(int[][] board, int blankRow, int blankCol, int moves) {
            this.board = new int[N][N];
            for (int i = 0; i < N; i++) {
                System.arraycopy(board[i], 0, this.board[i], 0, N);
            }
            this.blankRow = blankRow;
            this.blankCol = blankCol;
            this.moves = moves;
            this.priority = moves + calculateManhattanDistance();
        }

        int calculateManhattanDistance() {
            int distance = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int value = board[i][j];
                    if (value != 0) { 
                        int targetRow = (value - 1) / N;
                        int targetCol = (value - 1) % N;
                        distance += Math.abs(i - targetRow) + Math.abs(j - targetCol);
                    }
                }
            }
            return distance;
        }

        public String boardToString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(board[i][j]);
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] initialBoard = new int[N][N];
        int blankRow = 0, blankCol = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                initialBoard[i][j] = scanner.nextInt();
                if (initialBoard[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                }
            }
        }

        System.out.println(Asearch(initialBoard, blankRow, blankCol));
    }

    static int Asearch(int[][] initialBoard, int blankRow, int blankCol) {
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.priority));
        
        Set<String> visited = new HashSet<>();

        State initialState = new State(initialBoard, blankRow, blankCol, 0);
        pq.offer(initialState);
        visited.add(initialState.boardToString());

        while (!pq.isEmpty()) {
            State current = pq.poll();

            if (isGoalState(current.board)) {
                return current.moves;
            }

            for (int dir = 0; dir < 4; dir++) {
                int newRow = current.blankRow + row_dir[dir];
                int newCol = current.blankCol + col_dir[dir];

                if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) {

                    int[][] newBoard = new int[N][N];
                    for (int i = 0; i < N; i++) {
                        System.arraycopy(current.board[i], 0, newBoard[i], 0, N);
                    }

                    newBoard[current.blankRow][current.blankCol] = newBoard[newRow][newCol];
                    newBoard[newRow][newCol] = 0;

                    State newState = new State(newBoard, newRow, newCol, current.moves + 1);
                    String boardStr = newState.boardToString();
                    if (!visited.contains(boardStr)) {
                        visited.add(boardStr);
                        pq.offer(newState);
                    }
                }
            }
        }

        return -1;
    }

    static boolean isGoalState(int[][] board) {
        int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    if (board[i][j] != 0) return false;
                } else if (board[i][j] != count++) {
                    return false;
                }
            }
        }
        return true;
    }
}