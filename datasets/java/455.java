import java.util.*;

class solution {

  static class Node {

    int data;

    Node left, right;
  }
  ;

  static Node newNode(int num) {

    Node temp = new Node();

    temp.data = num;

    temp.left = temp.right = null;

    return temp;
  }

  static Node insert(Node root, int key) {

    if (root == null) return newNode(key);

    if (root.data > key) root.left = insert(root.left, key);
    else root.right = insert(root.right, key);

    return root;
  }

  static void storeInorder(Node ptr, Vector<Integer> vect) {

    if (ptr == null) return;

    storeInorder(ptr.left, vect);

    vect.add(ptr.data);

    storeInorder(ptr.right, vect);
  }

  static void pairSumUtil(Vector<Integer> vect1, Vector<Integer> vect2, int sum) {

    int left = 0;

    int right = vect2.size() - 1;

    while (left < vect1.size() && right >= 0) {

      if (vect1.get(left) + vect2.get(right) == sum) {

        System.out.print("(" + vect1.get(left) + ", " + vect2.get(right) + "), ");

        left++;

        right--;

      } else if (vect1.get(left) + vect2.get(right) < sum) left++;
      else right--;
    }
  }

  static void pairSum(Node root1, Node root2, int sum) {

    Vector<Integer> vect1 = new Vector<Integer>(), vect2 = new Vector<Integer>();

    storeInorder(root1, vect1);

    storeInorder(root2, vect2);

    pairSumUtil(vect1, vect2, sum);
  }

  public static void main(String args[]) {

    Node root1 = null;

    root1 = insert(root1, 8);

    root1 = insert(root1, 10);

    root1 = insert(root1, 3);

    root1 = insert(root1, 6);

    root1 = insert(root1, 1);

    root1 = insert(root1, 5);

    root1 = insert(root1, 7);

    root1 = insert(root1, 14);

    root1 = insert(root1, 13);

    Node root2 = null;

    root2 = insert(root2, 5);

    root2 = insert(root2, 18);

    root2 = insert(root2, 2);

    root2 = insert(root2, 1);

    root2 = insert(root2, 3);

    root2 = insert(root2, 4);

    int sum = 10;

    pairSum(root1, root2, sum);
  }
}
