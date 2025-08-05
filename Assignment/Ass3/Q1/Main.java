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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numPro = sc.nextInt();
        int numCus = sc.nextInt();

        int PerMax = Integer.MIN_VALUE;
        int RelMax = Integer.MIN_VALUE;

        int[] performance = new int[numPro];
        for (int i = 0; i < numPro; i++) {
            performance[i] = sc.nextInt();
            if (performance[i] > PerMax) {
                PerMax = performance[i];
            }
        }

        int[] reliability = new int[numPro];
        for (int i = 0; i < numPro; i++) {
            reliability[i] = sc.nextInt();
            if (reliability[i] > RelMax) {
                RelMax = reliability[i];
            }
        }

        Node[] products = new Node[numPro];
        for (int i = 0; i < numPro; i++) {
            products[i] = new Node(performance[i], reliability[i]);
        }

        Arrays.sort(products, (a, b) -> Integer.compare(b.Total, a.Total));

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numCus; i++) {
            int cusPer = sc.nextInt();
            int cusRel = sc.nextInt();
            int maxTotal = -1;

            if (cusPer > PerMax || cusRel > RelMax) {
                result.append(-1);
            } else {
                for (int j = 0; j < numPro; j++) {
                    Node node = products[j];
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
