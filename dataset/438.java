class GFG {

  static int sum = 0;

  static class Node {

    int data;

    Node left, right;
  }
  ;

  static Node getNode(int data) {

    Node newNode = new Node();

    newNode.data = data;

    newNode.left = newNode.right = null;

    return newNode;
  }

  static void sumOfParentOfX(Node root, int x) {

    if (root == null) return;

    if ((root.left != null && root.left.data == x) || (root.right != null && root.right.data == x))
      sum += root.data;

    sumOfParentOfX(root.left, x);

    sumOfParentOfX(root.right, x);
  }

  static int sumOfParentOfXUtil(Node root, int x) {

    sum = 0;

    sumOfParentOfX(root, x);

    return sum;
  }

  public static void main(String args[]) {

    Node root = getNode(4);

    root.left = getNode(2);

    root.right = getNode(5);

    root.left.left = getNode(7);

    root.left.right = getNode(2);

    root.right.left = getNode(2);

    root.right.right = getNode(3);

    int x = 2;

    System.out.println("Sum = " + sumOfParentOfXUtil(root, x));
  }
}
