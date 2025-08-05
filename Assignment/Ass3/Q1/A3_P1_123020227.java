import java.util.*;

class Node {
    int Performance;
    int Reliability;
    int Total;

    Node(int Performance, int Reliability) {
        this.Performance = Performance;
        this.Reliability = Reliability;
        this.Total = Performance + Reliability;
    }
}

class Main {
    static void sort(Stack<Node> inputStack) {
        Stack<Node> tempStack = new Stack<Node>();
        while (!inputStack.isEmpty()) {
            Node current = inputStack.pop();
            Node tempNode = tempStack.peek();
            while (!tempStack.isEmpty() && tempNode.Total < current.Total) {
                inputStack.push(tempStack.pop());
            }
            tempStack.push(current);
        }
        while (!tempStack.isEmpty()) {
            inputStack.push(tempStack.pop());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numPro = sc.nextInt();
        int numCus = sc.nextInt();

        int PerMax = Integer.MIN_VALUE;
        int RelMax = Integer.MIN_VALUE;

        Queue<Integer> performanceQueue = new LinkedList<>();
        for (int i = 0; i < numPro; i++) {
            int tempPer = sc.nextInt();
            performanceQueue.add(tempPer);
            if (tempPer > PerMax) {
                PerMax = tempPer;
            }
        }

        Stack<Node> stack = new Stack<Node>();
        for (int i = 0; i < numPro; i++) {
            int tempRel = sc.nextInt();
            if (tempRel > RelMax) {
                RelMax = tempRel;
            }
            int tempPer = performanceQueue.poll();
            Node node = new Node(tempPer, tempRel);
            stack.push(node);
        }
        sort(stack); 
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numCus; i++) {
            int cusPer = sc.nextInt();
            int cusRel = sc.nextInt();

            if (cusPer > PerMax || cusRel > RelMax) {
                result.append(-1);
            } else {
                int maxTotal = -1;
                for (int j = stack.size() - 1; j >= 0; j--) {
                    Node node = stack.get(j);
                    if (node.Performance >= cusPer && node.Reliability >= cusRel) {
                        maxTotal = node.Total;
                        break;
                    }
                }
                result.append(maxTotal);
            }
            if (i < numCus - 1) {
                result.append(" ");
            }
        }

        System.out.println(result.toString());

        sc.close();
    }
}