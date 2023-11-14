class Node {

  int key;

  Node left, right;

  public Node(int key) {

    this.key = key;

    left = right = null;
  }
}

class BinaryTree {

  Node root;

  int count(Node node) {

    if (node == null) return 0;

    return count(node.left) + count(node.right) + 1;
  }

  boolean checkRec(Node node, int n) {

    if (node == null) return false;

    if (count(node) == n - count(node)) return true;

    return checkRec(node.left, n) || checkRec(node.right, n);
  }

  boolean check(Node node) {

    int n = count(node);

    return checkRec(node, n);
  }

  public static void main(String[] args) {

    BinaryTree tree = new BinaryTree();

    tree.root = new Node(5);

    tree.root.left = new Node(1);

    tree.root.right = new Node(6);

    tree.root.left.left = new Node(3);

    tree.root.right.left = new Node(7);

    tree.root.right.right = new Node(4);

    if (tree.check(tree.root) == true) System.out.println("YES");
    else System.out.println("NO");
  }
}
