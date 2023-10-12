class GfG {

  static class node {

    int data;

    node left, right;
  }

  static int updatetree(node root) {

    if (root == null) return 0;

    if (root.left == null && root.right == null) return root.data;

    int leftsum = updatetree(root.left);

    int rightsum = updatetree(root.right);

    root.data += leftsum;

    return root.data + rightsum;
  }

  static void inorder(node node) {

    if (node == null) return;

    inorder(node.left);

    System.out.print(node.data + " ");

    inorder(node.right);
  }

  static node newNode(int data) {

    node node = new node();

    node.data = data;

    node.left = null;

    node.right = null;

    return (node);
  }

  public static void main(String[] args) {

    node root = newNode(1);

    root.left = newNode(2);

    root.right = newNode(3);

    root.left.left = newNode(4);

    root.left.right = newNode(5);

    root.right.right = newNode(6);

    updatetree(root);

    System.out.println("Inorder traversal of the modified tree is");

    inorder(root);
  }
}
