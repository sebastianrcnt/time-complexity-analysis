class Solution {

  static class Node {

    int data;

    Node left, right;
  }

  static Node temp = new Node();

  static Node newNode(int data) {

    Node temp = new Node();

    temp.data = data;

    temp.left = temp.right = null;

    return temp;
  }

  static Node leftMostNode(Node node) {

    while (node != null && node.left != null) node = node.left;

    return node;
  }

  static Node rightMostNode(Node node) {

    while (node != null && node.right != null) node = node.right;

    return node;
  }

  static Node findInorderRecursive(Node root, Node x) {

    if (root == null) return null;

    if (root == x
        || (temp = findInorderRecursive(root.left, x)) != null
        || (temp = findInorderRecursive(root.right, x)) != null) {

      if (temp != null) {

        if (root.left == temp) {

          System.out.print("Inorder Successor of " + x.data);

          System.out.print(" is " + root.data + "\n");

          return null;
        }
      }

      return root;
    }

    return null;
  }

  static void inorderSuccesor(Node root, Node x) {

    if (x.right != null) {

      Node inorderSucc = leftMostNode(x.right);

      System.out.print("Inorder Successor of " + x.data + " is ");

      System.out.print(inorderSucc.data + "\n");
    }

    if (x.right == null) {

      int f = 0;

      Node rightMost = rightMostNode(root);

      if (rightMost == x) System.out.print("No inorder successor! Right most node.\n");
      else findInorderRecursive(root, x);
    }
  }

  public static void main(String args[]) {

    Node root = newNode(1);

    root.left = newNode(2);

    root.right = newNode(3);

    root.left.left = newNode(4);

    root.left.right = newNode(5);

    root.right.right = newNode(6);

    inorderSuccesor(root, root.right);

    inorderSuccesor(root, root.left.left);

    inorderSuccesor(root, root.right.right);
  }
}
