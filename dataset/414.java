import java.util.*;

class GFG {

  static class Node {

    int data;

    Node left, right;
  }
  ;

  static Node newNode(int data) {

    Node temp = new Node();

    temp.data = data;

    temp.left = temp.right = null;

    return temp;
  }

  static Node flipBinaryTree(Node root) {

    Node curr = root;

    Node next = null;

    Node temp = null;

    Node prev = null;

    while (curr != null) {

      next = curr.left;

      curr.left = temp;

      temp = curr.right;

      curr.right = prev;

      prev = curr;

      curr = next;
    }

    return prev;
  }

  static void printLevelOrder(Node root) {

    if (root == null) return;

    Queue<Node> q = new LinkedList<Node>();

    q.add(root);

    while (true) {

      int nodeCount = q.size();

      if (nodeCount == 0) break;

      while (nodeCount > 0) {

        Node node = q.peek();

        System.out.print(node.data + " ");

        q.remove();

        if (node.left != null) q.add(node.left);

        if (node.right != null) q.add(node.right);

        nodeCount--;
      }

      System.out.println();
    }
  }

  public static void main(String args[]) {

    Node root = newNode(1);

    root.left = newNode(2);

    root.right = newNode(3);

    root.right.left = newNode(4);

    root.right.right = newNode(5);

    System.out.print("Level order traversal " + "of given tree\n");

    printLevelOrder(root);

    root = flipBinaryTree(root);

    System.out.print("\nLevel order traversal " + "of the flipped tree\n");

    printLevelOrder(root);
  }
}
