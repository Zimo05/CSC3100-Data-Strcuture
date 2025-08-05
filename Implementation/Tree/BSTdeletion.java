class BSTdeletion{

    Node delete(Node root, int x){
        if (root == null){
            return root;
        }

        if (x < root.key){
            root.left = delete(root.left, x);
        } else if (x > root.key){
            root.right = delete(root.right, x);
        } else {
            if (root.left == null && root.right == null){
                return NoChild(root);
            } else if (root.left == null || root.right == null){
                return OneChild(root);
            } else {
                return TwoChild(root);
            }  
        }

        return root;
    }
    Node NoChild(Node x){
        return null;
    }

    Node OneChild(Node x){
        if (x.left != null){
            return x.left;
        } else {
            return x.right;
        }

    }

    Node TwoChild(Node x){
        Succesor succesor = new Succesor();
        Node y = succesor.successor(x);
        int y_value = y.key;

        x.key = y_value;

        x.right = delete(y, y_value);
        return x;
    }

}