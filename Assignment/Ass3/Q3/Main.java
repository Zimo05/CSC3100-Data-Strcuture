import java.util.*;

class DataPoint {
    int id;
    long value;
    boolean Die;
    DataPoint(int id, long value) {
        this.id = id;
        this.value = value;
        Die = false;
    }
}

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

    BST() {
        root = null;
        idToNode = new HashMap<>();
    }

    public void insert(int id, long value) {
        Node newNode = new Node(id, value);
        idToNode.put(id, newNode);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        while (true) {
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
        
        updateParentChildCount(newNode);
    }

    private void updateParentChildCount(Node node) {
        Node current = root;
        while (current != null) {
            if (node.value < current.value) {
                current.numChild++;
                current = current.left;
            } else if (node.value > current.value) {
                current.numChild++;
                current = current.right;
            } else {
                break;
            }
        }
    }

    public void removeNode(int id) {
        Node node = idToNode.get(id);
        if (node != null) {
            node.isRemoved = true;
            sortedNodes.remove(node);
            // 更新父节点的numChild
            updateParentChildCountOnRemove(node);
        }
    }

    private void updateParentChildCountOnRemove(Node node) {
        Node current = root;
        while (current != null) {
            if (node.value < current.value) {
                current.numChild--;
                current = current.left;
            } else if (node.value > current.value) {
                current.numChild--;
                current = current.right;
            } else {
                break;
            }
        }
    }

    public void reinsertNode(int id) {
        Node node = idToNode.get(id);
        if (node != null && node.isRemoved) {
            node.isRemoved = false;
            updateParentChildCount(node);
        }
    }

    public int getKthLargestSubtreeSize(int k) {
        if (k < 1 || k > sortedNodes.size()) {
            return -1;
        }
        Iterator<Node> iterator = sortedNodes.iterator();
        Node kthNode = null;
        for (int i = 0; i < k; i++) {
            kthNode = iterator.next();
        }
        return kthNode.numChild + 1;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int Q = sc.nextInt();

        BST bst = new BST();
        List<DataPoint> dataList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            long value = sc.nextLong();
            bst.insert(i, value);
            dataList.add(new DataPoint(i, value));
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

// 用dataList来找到Kth Largest，然后记得要动态处理dataList,每次找的时候while(!Die && i < K)