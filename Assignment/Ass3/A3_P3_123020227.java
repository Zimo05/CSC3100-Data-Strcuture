import java.util.*;

class Node {
    int id;
    long value;
    int numChild;
    boolean isRemoved;
    Node left, right;

    Node(int id, long value) {
        this.id = id;
        this.value = value;
        this.numChild = 0;
        this.isRemoved = false;
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
        sortedNodes = new TreeSet<>((a, b) -> Long.compare(b.value, a.value));
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
            if (value < current.value) {
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
    
    public Node findKthLargest(int k) {
        Node current = root;
        while (current != null) {
            int rightSize = (current.right != null && !current.right.isRemoved) ? current.right.numChild + 1 : 0;
            int currentSize = current.isRemoved ? 0 : 1;
            
            if (rightSize + currentSize >= k) {
                if (rightSize >= k) {
                    current = current.right;
                } else {
                    return current;
                }
            } else {
                k -= (rightSize + currentSize);
                current = current.left;
            }
        }
        return null;
    }

    public int getKthLargestSubtreeSize(int k) {
        Node node = findKthLargest(k);
        if (node == null || node.isRemoved) {
            return 0;
        }
        return node.numChild + 1;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int Q = sc.nextInt();

        BST bst = new BST();
        for (int i = 1; i <= n; i++) {
            long value = sc.nextLong();
            bst.insert(i, value);
        }

        for (int i = 0; i < Q; i++) {
            int op = sc.nextInt();
            int id = sc.nextInt();
            int K = sc.nextInt();

            if (op == 1) {
                bst.removeNode(id);
            } else if (op == 2) {
                bst.reinsertNode(id);
            }

            System.out.println(bst.getKthLargestSubtreeSize(K));
        }
    }
}