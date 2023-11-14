import java.util.*;

class GfG {

  static int preIndex = 0;

  static class Node {

    int data;

    Node left, right;
  }

  static Node newNode(int data) {

    Node temp = new Node();

    temp.data = data;

    temp.left = null;

    temp.right = null;

    return temp;
  }

  static int search(int arr[], int strt, int end, int value) {

    for (int i = strt; i <= end; i++) {

      if (arr[i] == value) return i;
    }

    return -1;
  }

  static Node buildTree(int in[], int pre[], int inStrt, int inEnd) {

    if (inStrt > inEnd) return null;

    Node tNode = newNode(pre[preIndex++]);

    if (inStrt == inEnd) return tNode;

    int inIndex = search(in, inStrt, inEnd, tNode.data);

    tNode.left = buildTree(in, pre, inStrt, inIndex - 1);

    tNode.right = buildTree(in, pre, inIndex + 1, inEnd);

    return tNode;
  }

  static int checkPostorder(Node node, int postOrder[], int index) {

    if (node == null) return index;

    index = checkPostorder(node.left, postOrder, index);

    index = checkPostorder(node.right, postOrder, index);

    if (node.data == postOrder[index]) index++;
    else return -1;

    return index;
  }

  public static void main(String[] args) {

    int inOrder[] = {4, 2, 5, 1, 3};

    int preOrder[] = {1, 2, 4, 5, 3};

    int postOrder[] = {4, 5, 2, 3, 1};

    int len = inOrder.length;

    Node root = buildTree(inOrder, preOrder, 0, len - 1);

    int index = checkPostorder(root, postOrder, 0);

    if (index == len) System.out.println("Yes");
    else System.out.println("No");
  }
}
