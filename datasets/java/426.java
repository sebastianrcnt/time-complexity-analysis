import java.util.LinkedList;
import java.util.Queue;

public class CompleteBTree {

  static class Node {

    int data;

    Node left;

    Node right;

    Node(int d) {

      data = d;

      left = null;

      right = null;
    }
  }

  static boolean isCompleteBT(Node root) {

    if (root == null) return true;

    Queue<Node> queue = new LinkedList<>();

    boolean flag = false;

    queue.add(root);

    while (!queue.isEmpty()) {

      Node temp_node = queue.remove();

      if (temp_node.left != null) {

        if (flag == true) return false;

        queue.add(temp_node.left);

      } else flag = true;

      if (temp_node.right != null) {

        if (flag == true) return false;

        queue.add(temp_node.right);

      } else flag = true;
    }

    return true;
  }

  public static void main(String[] args) {

    Node root = new Node(1);

    root.left = new Node(2);

    root.right = new Node(3);

    root.left.left = new Node(4);

    root.left.right = new Node(5);

    root.right.right = new Node(6);

    if (isCompleteBT(root) == true) System.out.println("Complete Binary Tree");
    else System.out.println("NOT Complete Binary Tree");
  }
}
