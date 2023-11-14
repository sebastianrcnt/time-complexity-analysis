public class LisTree {

  static class node {

    int data, liss;
    node left, right;

    public node(int data) {

      this.data = data;

      this.liss = 0;
    }
  }

  static int liss(node root) {

    if (root == null) return 0;

    if (root.liss != 0) return root.liss;

    if (root.left == null && root.right == null) return root.liss = 1;

    int liss_excl = liss(root.left) + liss(root.right);

    int liss_incl = 1;

    if (root.left != null) {

      liss_incl += (liss(root.left.left) + liss(root.left.right));
    }

    if (root.right != null) {

      liss_incl += (liss(root.right.left) + liss(root.right.right));
    }

    return root.liss = Math.max(liss_excl, liss_incl);
  }

  public static void main(String[] args) {

    node root = new node(20);

    root.left = new node(8);

    root.left.left = new node(4);

    root.left.right = new node(12);

    root.left.right.left = new node(10);

    root.left.right.right = new node(14);

    root.right = new node(22);

    root.right.right = new node(25);

    System.out.println("Size of the Largest Independent Set is " + liss(root));
  }
}
