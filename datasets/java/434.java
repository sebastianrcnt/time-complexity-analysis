import java.util.ArrayList;

public class PrintPath {

  public static boolean hasPath(Node root, ArrayList<Integer> arr, int x) {

    if (root == null) return false;

    arr.add(root.data);

    if (root.data == x) return true;

    if (hasPath(root.left, arr, x) || hasPath(root.right, arr, x)) return true;

    arr.remove(arr.size() - 1);

    return false;
  }

  public static void printPath(Node root, int x) {

    ArrayList<Integer> arr = new ArrayList<>();

    if (hasPath(root, arr, x)) {

      for (int i = 0; i < arr.size() - 1; i++) System.out.print(arr.get(i) + "->");

      System.out.print(arr.get(arr.size() - 1));

    } else System.out.print("No Path");
  }

  public static void main(String args[]) {

    Node root = new Node(1);

    root.left = new Node(2);

    root.right = new Node(3);

    root.left.left = new Node(4);

    root.left.right = new Node(5);

    root.right.left = new Node(6);

    root.right.right = new Node(7);

    int x = 5;

    printPath(root, x);
  }
}

class Node {

  int data;

  Node left, right;

  Node(int data) {

    this.data = data;

    left = right = null;
  }
}
;
