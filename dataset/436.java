import java.util.*;

class GfG {

  static class Node {

    int data;

    Node left, right;
  }

  static void printOddNodes(Node root) {

    if (root == null) return;

    Queue<Node> q = new LinkedList<Node>();

    q.add(root);

    boolean isOdd = true;

    while (true) {

      int nodeCount = q.size();

      if (nodeCount == 0) break;

      while (nodeCount > 0) {

        Node node = q.peek();

        if (isOdd == true) System.out.print(node.data + " ");

        q.remove();

        if (node.left != null) q.add(node.left);

        if (node.right != null) q.add(node.right);

        nodeCount--;
      }

      isOdd = !isOdd;
    }
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

    printOddNodes(root);
  }
}
