class GfG {

  static class Node {

    int data;

    Node left, right;
  }

  static void printOddNodes(Node root, boolean isOdd) {

    if (root == null) return;

    if (isOdd == true) System.out.print(root.data + " ");

    printOddNodes(root.left, !isOdd);

    printOddNodes(root.right, !isOdd);
  }

  static Node newNode(int data) {

    Node node = new Node();

    node.data = data;

    node.left = null;

    node.right = null;

    return (node);
  }

  public static void main(String[] args) {

    Node root = newNode(1);

    root.left = newNode(2);

    root.right = newNode(3);

    root.left.left = newNode(4);

    root.left.right = newNode(5);

    printOddNodes(root, true);
  }
}
