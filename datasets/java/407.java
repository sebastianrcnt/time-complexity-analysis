class Node {

  int data;

  Node left, right;

  Node(int item) {

    data = item;

    left = right = null;
  }
}

class BinaryTree {

  Node root;

  void convertTree(Node node) {

    int left_data = 0, right_data = 0, diff;

    if (node == null || (node.left == null && node.right == null)) return;
    else {

      convertTree(node.left);

      convertTree(node.right);

      if (node.left != null) left_data = node.left.data;

      if (node.right != null) right_data = node.right.data;

      diff = left_data + right_data - node.data;

      if (diff > 0) node.data = node.data + diff;

      if (diff < 0) increment(node, -diff);
    }
  }

  void increment(Node node, int diff) {

    if (node.left != null) {

      node.left.data = node.left.data + diff;

      increment(node.left, diff);

    } else if (node.right != null) {

      node.right.data = node.right.data + diff;

      increment(node.right, diff);
    }
  }

  void printInorder(Node node) {

    if (node == null) return;

    printInorder(node.left);

    System.out.print(node.data + " ");

    printInorder(node.right);
  }

  public static void main(String args[]) {

    BinaryTree tree = new BinaryTree();

    tree.root = new Node(50);

    tree.root.left = new Node(7);

    tree.root.right = new Node(2);

    tree.root.left.left = new Node(3);

    tree.root.left.right = new Node(5);

    tree.root.right.left = new Node(1);

    tree.root.right.right = new Node(30);

    System.out.println("Inorder traversal before conversion is :");

    tree.printInorder(tree.root);

    tree.convertTree(tree.root);

    System.out.println("");

    System.out.println("Inorder traversal after conversion is :");

    tree.printInorder(tree.root);
  }
}
