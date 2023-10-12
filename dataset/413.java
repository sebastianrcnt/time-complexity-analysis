import java.util.LinkedList;
import java.util.Queue;

public class FlipTree {

  public static Node flipBinaryTree(Node root) {

    if (root == null) return root;

    if (root.left == null && root.right == null) return root;

    Node flippedRoot = flipBinaryTree(root.left);

    root.left.left = root.right;

    root.left.right = root;

    root.left = root.right = null;

    return flippedRoot;
  }

  public static void printLevelOrder(Node root) {

    if (root == null) return;

    Queue<Node> q = new LinkedList<>();

    q.add(root);

    while (true) {

      int nodeCount = q.size();

      if (nodeCount == 0) break;

      while (nodeCount > 0) {

        Node node = q.remove();

        System.out.print(node.data + " ");

        if (node.left != null) q.add(node.left);

        if (node.right != null) q.add(node.right);

        nodeCount--;
      }

      System.out.println();
    }
  }

  public static void main(String args[]) {

    Node root = new Node(1);

    root.left = new Node(2);

    root.right = new Node(1);

    root.right.left = new Node(4);

    root.right.right = new Node(5);

    System.out.println("Level order traversal of given tree");

    printLevelOrder(root);

    root = flipBinaryTree(root);

    System.out.println("Level order traversal of flipped tree");

    printLevelOrder(root);
  }
}

class Node {

  int data;

  Node left, right;

  Node(int data) {

    this.data = data;
  }
}
;
