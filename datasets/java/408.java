class GFG {

  static class node {

    int key;

    node left, right;

    node(int key) {

      this.key = key;

      this.left = null;

      this.right = null;
    }
  }

  static void convert(node root) {

    if (root == null) return;

    convert(root.left);

    convert(root.right);

    if (root.left == null) root.left = root.right;
    else root.left.right = root.right;

    root.right = null;
  }

  static void downRightTraversal(node root) {

    if (root != null) {

      System.out.print(root.key + " ");

      downRightTraversal(root.right);

      downRightTraversal(root.left);
    }
  }

  static node newNode(int key) {

    node temp = new node(0);

    temp.key = key;

    temp.left = null;

    temp.right = null;

    return temp;
  }

  public static void main(String[] args) {

    node root = new node(1);

    root.left = newNode(2);

    root.right = newNode(3);

    root.right.left = newNode(4);

    root.right.right = newNode(5);

    root.right.left.left = newNode(6);

    root.right.right.left = newNode(7);

    root.right.right.right = newNode(8);

    convert(root);

    System.out.println("Traversal of the tree " + "converted to down-right form");

    downRightTraversal(root);
  }
}
