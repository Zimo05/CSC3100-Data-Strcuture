import java.util.*;

class Node {
    int data;
    Node left;
    Node right;
    Node(int x) {
        this.data = x;
        this.left = null;
        this.right = null;
    }
}

class BST {
    Node root;
    
    BST() {
        this.root = null;
    }

    void insert(int num) {
        root = insertRec(root, num);
    }

    private Node insertRec(Node root, int num) {
        if (root == null) {
            return new Node(num);
        }

        if (num < root.data) {
            root.left = insertRec(root.left, num);
        } else if (num > root.data) { 
            root.right = insertRec(root.right, num);
        }
        return root;
    }
    

    void inOrder() {
        inOrderRec(root);
        System.out.println();
    }
    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.data + " ");
            inOrderRec(node.right);
        }
    }

    void preOrder() {
        preOrderRec(root);
        System.out.println();
    }
    private void preOrderRec(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(Node node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.print(node.data + " ");
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST bst = new BST();
        String input = sc.nextLine().trim();
        String[] arr = input.split(" ");
        for (String s : arr) {
            int num = Integer.parseInt(s);
            bst.insert(num);
        }

        bst.inOrder();
        bst.preOrder();
        bst.postOrder();

        sc.close();
        
    }
}