import java.util.*;

class Node {
    Character word;
    int frequency;
    Node left;
    Node right;

    Node(Character word, int frequency, Node left, Node right) {
        this.word = word;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}

class HuffmanTree {
    Node root;

    HuffmanTree() {
        this.root = null;
    }

    Node insert(PriorityQueue<Node> queue) {
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            Node parent = new Node(null, left.frequency + right.frequency, left, right);
            queue.add(parent);
        }
        root = queue.poll();
        return root;
    }
    
    void BFS(Node root, int depth, Map<Character, Integer> depthDict) {
        if (root.left == null && root.right == null) {
            depthDict.put(root.word, depth);
        } else {
            if (root.left != null) {
                BFS(root.left, depth + 1, depthDict);
            }
            if (root.right != null) {
                BFS(root.right, depth + 1, depthDict);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (char ch : input.toCharArray()){
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> {
            if (a.frequency != b.frequency) {
                return a.frequency - b.frequency;
            } else {
                if (a.word == null && b.word == null) return 0;
                if (a.word == null) return -1;
                if (b.word == null) return 1;
                return a.word.compareTo(b.word);
            }
        });
        
        for (char word : frequency.keySet()) {
            queue.add(new Node(word, frequency.get(word), null, null));
        }

        HuffmanTree HFtree = new HuffmanTree();
        HFtree.insert(queue);

        Map<Character, Integer> depthDict = new HashMap<>();
        if (HFtree.root.left == null && HFtree.root.right == null) {
            depthDict.put(HFtree.root.word, 1);
        } else {
            HFtree.BFS(HFtree.root, 0, depthDict);
        }

        int sum = 0;
        for (char word : input.toCharArray()) {
            sum += depthDict.get(word);
        }

        System.out.println(sum);
        sc.close();
    }
}