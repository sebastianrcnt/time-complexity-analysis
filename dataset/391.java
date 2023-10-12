class Sol {

  static class Node {

    char key;

    Node left, right;
  }
  ;

  static void preorder(Node root1, Node root2, int lvl) {

    if (root1 == null || root2 == null) return;

    if (lvl % 2 == 0) {

      char t = root1.key;

      root1.key = root2.key;

      root2.key = t;
    }

    preorder(root1.left, root2.right, lvl + 1);

    preorder(root1.right, root2.left, lvl + 1);
  }

  static void reverseAlternate(Node root) {

    preorder(root.left, root.right, 0);
  }

  static void printInorder(Node root) {

    if (root == null) return;

    printInorder(root.left);

    System.out.print(root.key + " ");

    printInorder(root.right);
  }

  static Node newNode(int key) {

    Node temp = new Node();

    temp.left = temp.right = null;

    temp.key = (char) key;

    return temp;
  }

  public static void main(String args[]) {

    Node root = newNode('a');

    root.left = newNode('b');

    root.right = newNode('c');

    root.left.left = newNode('d');

    root.left.right = newNode('e');

    root.right.left = newNode('f');

    root.right.right = newNode('g');

    root.left.left.left = newNode('h');

    root.left.left.right = newNode('i');

    root.left.right.left = newNode('j');

    root.left.right.right = newNode('k');

    root.right.left.left = newNode('l');

    root.right.left.right = newNode('m');

    root.right.right.left = newNode('n');

    root.right.right.right = newNode('o');

    System.out.print("Inorder Traversal of given tree\n");

    printInorder(root);

    reverseAlternate(root);

    System.out.print("\n\nInorder Traversal of modified tree\n");

    printInorder(root);
  }
}
