import java.util.*;

class Node {

  int data;

  Node left, right;

  Node(int key) {

    int data = key;

    left = right = null;
  }
}

class GFG {

  static boolean checkLevelLeafNode(Node root) {

    if (root == null) return true;

    Queue<Node> q = new LinkedList<>();

    q.add(root);

    int result = Integer.MAX_VALUE;

    int level = 0;

    while (q.size() != 0) {

      int size = q.size();

      level++;

      while (size > 0) {

        Node temp = q.remove();

        if (temp.left != null) {

          q.add(temp.left);

          if (temp.left.left == null && temp.left.right == null) {

            if (result == Integer.MAX_VALUE) result = level;
            else if (result != level) return false;
          }
        }

        if (temp.right != null) {

          q.add(temp.right);

          if (temp.right.left == null && temp.right.right == null) {

            if (result == Integer.MAX_VALUE) result = level;
            else if (result != level) return false;
          }
        }

        size--;
      }
    }

    return true;
  }

  public static void main(String args[]) {

    Node root = new Node(1);

    root.left = new Node(2);

    root.right = new Node(3);

    root.left.right = new Node(4);

    root.right.left = new Node(5);

    root.right.right = new Node(6);

    boolean result = checkLevelLeafNode(root);

    if (result == true) System.out.println("All leaf nodes are at same level");
    else System.out.println("Leaf nodes not at same level");
  }
}
