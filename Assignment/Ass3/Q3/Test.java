import java.util.*;

class DataPoint implements Comparable<DataPoint> {
    int id;
    long value;
    boolean Die;
    
    DataPoint(int id, long value) {
        this.id = id;
        this.value = value;
        this.Die = false;
    }
    
    public int compareTo(DataPoint other) {
        if (this.value != other.value) {
            return Long.compare(other.value, this.value);
        } else {
            return Integer.compare(this.id, other.id);
        }
    }
}

class Node {
    int id;
    long value;
    boolean isRemoved;
    int subtreeSize;
    Node left, right;

    Node(int id, long value) {
        this.id = id;
        this.value = value;
        this.isRemoved = false;
        this.subtreeSize = 1;
        this.left = null;
        this.right = null;
    }
}

class BST {
    Node root;
    Map<Integer, Node> idToNode;
    TreeSet<DataPoint> activeData;

    BST(List<DataPoint> dataList) {
        root = null;
        idToNode = new HashMap<>();
        activeData = new TreeSet<>();
        
        for (DataPoint dp : dataList) {
            activeData.add(dp);
            insert(dp.id, dp.value);
        }
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
            current.subtreeSize++; // Increment size for all nodes along the path
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
    }

    public void removeNode(int id) {
        Node node = idToNode.get(id);
        if (node != null && !node.isRemoved) {
            node.isRemoved = true;
            // Update activeData
            for (DataPoint dp : activeData) {
                if (dp.id == id) {
                    dp.Die = true;
                    break;
                }
            }
            updateSubtreeSizes(node, -1);
        }
    }

    public void reinsertNode(int id) {
        Node node = idToNode.get(id);
        if (node != null && node.isRemoved) {
            node.isRemoved = false;
            for (DataPoint dp : activeData) {
                if (dp.id == id) {
                    dp.Die = false;
                    break;
                }
            }
            updateSubtreeSizes(node, 1);
        }
    }

    private void updateSubtreeSizes(Node node, int delta) {
        Node current = root;
        while (current != null) {
            current.subtreeSize += delta;
            if (node.value < current.value) {
                current = current.left;
            } else if (node.value > current.value) {
                current = current.right;
            } else {
                break;
            }
        }
    }

    public int getKthLargestSubtreeSize(int k) {
        if (k < 1 || k > activeData.size()) {
            return -1;
        }
        
        int count = 0;
        for (DataPoint dp : activeData) {
            if (! dp.Die) {
                count++;
                if (count == k) {
                    Node node = idToNode.get(dp.id);
                    return node.subtreeSize;
                }
            }
        }
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int Q = sc.nextInt();

        List<DataPoint> dataList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            long value = sc.nextLong();
            dataList.add(new DataPoint(i, value));
        }

        BST bst = new BST(dataList);

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