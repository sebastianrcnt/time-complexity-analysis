class Solution {

  static class Node {

    Node left, right;

    int data;
  }

  static Node createNode(int x) {

    Node p = new Node();

    p.data = x;

    p.left = p.right = null;

    return p;
  }

  static void insertNode(Node root, int x) {

    Node p = root, q = null;

    while (p != null) {

      q = p;

      if (p.data < x) p = p.right;
      else p = p.left;
    }

    if (q == null) p = createNode(x);
    else {

      if (q.data < x) q.right = createNode(x);
      else q.left = createNode(x);
    }
  }

  static int maxelpath(Node q, int x) {

    Node p = q;

    int mx = -1;

    while (p.data != x) {

      if (p.data > x) {

        mx = Math.max(mx, p.data);

        p = p.left;

      } else {

        mx = Math.max(mx, p.data);

        p = p.right;
      }
    }

    return Math.max(mx, x);
  }

  static int maximumElement(Node root, int x, int y) {

    Node p = root;

    while ((x < p.data && y < p.data) || (x > p.data && y > p.data)) {

      if (x < p.data && y < p.data) p = p.left;
      else if (x > p.data && y > p.data) p = p.right;
    }

    return Math.max(maxelpath(p, x), maxelpath(p, y));
  }

  public static void main(String args[]) {

    int arr[] = {18, 36, 9, 6, 12, 10, 1, 8};

    int a = 1, b = 10;

    int n = arr.length;

    Node root = createNode(arr[0]);

    for (int i = 1; i < n; i++) insertNode(root, arr[i]);

    System.out.println(maximumElement(root, a, b));
  }
}
