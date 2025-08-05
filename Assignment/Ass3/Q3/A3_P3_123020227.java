import java.util.*;

class BST{
    class Node {
        int id;
        int ID;
        Node left;
        Node right;
        Node parent;
        boolean die;

        Node(int id, int ID) {
            this.id = id;
            this.ID = ID;
            left = null;
            right = null;
            parent = null;
            die = false;
        }
    }

    Node root;
    BST() {
        root = null;
    }

    public void insert(int id, int ID) {
        if (root == null) {
            root = new Node(id, ID);
        } else {
            insert(root, id, ID);
        }
    }
    private void insert(Node node, int id, int ID) {
        if (id < node.id) {
            if (node.left == null) {
                node.left = new Node(id, ID);
            } else {
                insert(node.left, id, ID);
            }
        } else if (id > node.id) {
            if (node.right == null) {
                node.right = new Node(id, ID);
            } else {
                insert(node.right, id, ID);
            }
        }
    }

    public void deleteNode(Node root, int id) {
        if (root == null) {
            return;
        }
        if (id < root.id) {
            deleteNode(root.left, id);
        } else if (id > root.id) {
            deleteNode(root.right, id);
        } else {

            if (root.left == null && root.right == null) {
                root = null;
                return; 
            }
        }
    }

    public static Node postOrder(Node root, int id){
        if (root == null) return null;

        Node leftResult = postOrder(root.left, id);
        if (leftResult != null) {
            return leftResult;
        }
        Node rightResult = postOrder(root.right, id);
        if (rightResult != null) {
            return rightResult;
        }
        if (root.left == null && root.right == null && root.id == id) {
            return root;
        }
    
        return null;        
    }
}

class Main{

    public static void RankQueueInsert(Deque<Integer> rank, int ID) {
        if (rank.isEmpty()) {
            rank.add(ID);
            return;
        }
        int first = rank.getFirst();
        int last = rank.getLast();
    
        int difMin = Math.abs(ID - first);
        int difMax = Math.abs(ID - last);
    
        if (difMin < difMax) {
            ListIterator<Integer> tmp = ((LinkedList<Integer>) rank).listIterator();
            while (tmp.hasNext()) {
                if (ID <= tmp.next()) {
                    tmp.previous();
                    tmp.add(ID);
                    return;
                }
            }
            rank.addLast(ID);
        } 
        else {
            ListIterator<Integer> tmp = ((LinkedList<Integer>) rank).listIterator(rank.size());
            while (tmp.hasPrevious()) {
                if (ID >= tmp.previous()) {
                    tmp.next();
                    tmp.add(ID);
                    return;
                }
            }
            rank.addFirst(ID);
        }
    }

    public static void RankQueueDelete(Deque<Integer> rank, int ID) {

        int first = rank.getFirst();
        int last = rank.getLast();

        int difMin = Math.abs(ID - first); 
        int difMax = Math.abs(ID - last);
    
        if (difMin <= difMax) {
            ListIterator<Integer> tmp = ((LinkedList<Integer>) rank).listIterator();
            while (tmp.hasNext()) {
                if (tmp.next() == ID) {
                    tmp.remove();
                    return;
                }
            }
        } 
        else {
            ListIterator<Integer> tmp = ((LinkedList<Integer>) rank).listIterator(rank.size());
            while (tmp.hasPrevious()) {
                if (tmp.previous() == ID) {
                    tmp.remove(); 
                    return;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int Q = sc.nextInt();
        
        Deque<Integer> rank = new LinkedList<>();
        BST bst = new BST();


    }   
    
}
