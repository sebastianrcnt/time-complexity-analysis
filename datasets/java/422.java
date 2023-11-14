class Node {

  int key;

  Node left, right;

  public Node(int key) {

    this.key = key;

    left = right = null;
  }
}

class Res {

  boolean res = false;
}

class BinaryTree {

  Node root;

  int count(Node node) {

    if (node == null) return 0;

    return count(node.left) + count(node.right) + 1;
  }

  int checkRec(Node root, int n, Res res) {

    if (root == null) return 0;

    int c = checkRec(root.left, n, res) + 1 + checkRec(root.right, n, res);

    if (c == n - c) res.res = true;

    return c;
  }

  boolean check(Node root) {

    int n = count(root);

    Res res = new Res();

    checkRec(root, n, res);

    return res.res;
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
