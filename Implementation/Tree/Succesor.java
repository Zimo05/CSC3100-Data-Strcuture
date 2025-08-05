class Node {
    int key;
    Node left, right, parent;

    public Node(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.parent = null;  // Optional, helps in Case 2
    }
}

class Succesor {
    public Node successor(Node x){
        if (x.left != null){
            return findMin(x.left);
        }

        Node y = x.parent;
        while (y!= null && x == y.right){
            x = y;
            y = y.parent;
        }

        return y;
    }
}
