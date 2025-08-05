import java.util.*;

class DataPoint {
    int id;
    long ID;
    DataPoint(int id, long ID) {
        this.id = id;
        this.ID = ID;
    }
}

class Node {
    int id;
    Long ID;
    int NumChild;
    boolean die;
    Node left;
    Node right;

    Node(int id, Long ID) {
        this.id = id;
        this.ID = ID;
        this.NumChild = 0;
        this.die = false;
        this.left = null;
        this.right = null;
    }
}

class BST {
    Node root;
    Map<Integer, Node> idToNode;
    TreeSet<Node> sortedNodes;

    BST() {
        root = null;
        idToNode = new HashMap<>();
        sortedNodes = new TreeSet<>((a, b) -> Long.compare(b.ID, a.ID));
    }

    public void insert(int id, long value) {
        Node newNode = new Node(id, value);
        idToNode.put(id, newNode);
        sortedNodes.add(newNode);
    
        if (root == null) {
            root = newNode;
            return;
        }

        List<Node> path = new ArrayList<>();
        Node current = root;
        while (true) {
            path.add(current);
            if (value < current.ID) {
                if (current.left == null) {
                    current.left = newNode;
                    break;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    break;
                }
                current = current.right;
            }
        }
        for (Node node : path) {
            node.numChild++;
        }
    }
    
    public void removeNode(int id) {
        Node node = idToNode.get(id);
        if (node != null) {
            node.isRemoved = true;
            sortedNodes.remove(node);
            List<Node> path = new ArrayList<>();
            Node current = root;
            while (current != null && current != node) {
                path.add(current);
                if (node.value < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            for (Node pathNode : path) {
                pathNode.numChild--;
            }
        }
    }
    
    public void reinsertNode(int id) {
        Node node = idToNode.get(id);
        if (node != null && node.isRemoved) {
            node.isRemoved = false;
            sortedNodes.add(node);
            List<Node> path = new ArrayList<>();
            Node current = root;
            while (current != null && current != node) {
                path.add(current);
                if (node.value < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            for (Node pathNode : path) {
                pathNode.numChild++;
            }
        }
    }
    
}

class Main {
    public Integer getkthLargest(TreeMap<Integer, Long> rankMap, int k) {
        int count = 0;
        for (Map.Entry<Integer, Long> entry : rankMap.entrySet()) {
            count++;
            if (count == k) {
                return entry.getKey(); // output the id
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int Q = sc.nextInt();

        BST tree = new BST();
        List<DataPoint> dataList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Long ID = sc.nextLong();
            int id = i;
            tree.insert(id, ID);
            DataPoint dataNode = new DataPoint(id, ID);
            dataList.add(dataNode);
        }

        TreeMap<Integer, Integer> rankMap = new TreeMap<>((a, b) -> (int) (dataList.get(b - 1).ID - dataList.get(a - 1).ID));

        for (DataPoint data : dataList) {
            rankMap.put(data.id, data.id);
        }

        for (int i = 0; i < Q; i++) {
            int op = sc.nextInt();
            int id = sc.nextInt();
            int K = sc.nextInt();
            
            if (op == 1) {
                Node target = tree.PostOrder(tree.root, id);
                if (target != null) {
                    target.die = true;
                }
            }

            if (op == 2) {
                Node target = tree.PostOrder(tree.root, id);
                if (target != null) {
                    target.die = false;
                }
            }
        }
    }
}
