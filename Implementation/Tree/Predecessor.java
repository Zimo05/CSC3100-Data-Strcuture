class Predecessor {
    public Node predecessor(Node x){
        if (x.left != null){
            return findMax(x.left);
        }

        Node y = x.parent;
        while(y != null && x == y.left){
            x = y;
            y = y.parent;
        }
        return y;
    }
}
