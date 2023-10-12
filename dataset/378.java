import java.util.*;

class Solution {

  static class Node {

    int data;

    Node left, right;
  }

  static class INT {

    int data;
  }

  static Node getNode(int data) {

    Node new_node = new Node();

    new_node.data = data;

    new_node.left = new_node.right = null;

    return new_node;
  }

  static void storeInorderTraversal(Node root, Vector<Integer> arr) {

    if (root == null) return;

    storeInorderTraversal(root.left, arr);

    arr.add(root.data);

    storeInorderTraversal(root.right, arr);
  }

  static void replaceNodeWithSum(Node root, Vector<Integer> arr, INT i) {

    if (root == null) return;

    replaceNodeWithSum(root.left, arr, i);

    root.data = arr.get(i.data - 1) + arr.get(i.data + 1);

    i.data++;

    replaceNodeWithSum(root.right, arr, i);
  }

  static void replaceNodeWithSumUtil(Node root) {

    if (root == null) return;

    Vector<Integer> arr = new Vector<Integer>();

    arr.add(0);

    storeInorderTraversal(root, arr);

    arr.add(0);

    INT i = new INT();

    i.data = 1;

    replaceNodeWithSum(root, arr, i);
  }

  static void preorderTraversal(Node root) {

    if (root == null) return;

    System.out.print(root.data + " ");

    preorderTraversal(root.left);

    preorderTraversal(root.right);
  }

  public static void main(String args[]) {

    Node root = getNode(1);

    root.left = getNode(2);

    root.right = getNode(3);

    root.left.left = getNode(4);

    root.left.right = getNode(5);

    root.right.left = getNode(6);

    root.right.right = getNode(7);

    System.out.println("Preorder Traversal before tree modification:");

    preorderTraversal(root);

    replaceNodeWithSumUtil(root);

    System.out.println("\nPreorder Traversal after tree modification:");

    preorderTraversal(root);
  }
}
