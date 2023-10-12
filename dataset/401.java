import java.util.*;

class GFG {

  public static void ancestorMatrix(Node root, int matrix[][], int size) {

    if (root == null) return;

    ancestorMatrix(root.left, matrix, size);

    ancestorMatrix(root.right, matrix, size);

    if (root.left != null) {

      matrix[root.data][root.left.data] = 1;

      for (int i = 0; i < size; i++) {

        if (matrix[root.left.data][i] == 1) matrix[root.data][i] = 1;
      }
    }

    if (root.right != null) {

      matrix[root.data][root.right.data] = 1;

      for (int i = 0; i < size; i++) {

        if (matrix[root.right.data][i] == 1) matrix[root.data][i] = 1;
      }
    }
  }

  public static void main(String[] args) {

    Node tree_root = new Node(5);

    tree_root.left = new Node(1);

    tree_root.right = new Node(2);

    tree_root.left.left = new Node(0);

    tree_root.left.right = new Node(4);

    tree_root.right.left = new Node(3);

    int size = 6;

    int matrix[][] = new int[size][size];

    ancestorMatrix(tree_root, matrix, size);

    for (int i = 0; i < size; i++) {

      for (int j = 0; j < size; j++) {

        System.out.print(matrix[i][j] + " ");
      }

      System.out.println();
    }
  }

  static class Node {

    public int data;

    public Node left, right;

    public Node(int data) {

      this.data = data;

      this.left = this.right = null;
    }
  }
}
