class Node {

  int data;

  Node left, right, nextRight;

  Node(int item) {

    data = item;

    left = right = nextRight = null;
  }
}

class BinaryTree {

  Node root;

  int isLeaf(Node node) {

    if (node == null) return 0;

    if (node.left == null && node.right == null) return 1;

    return 0;
  }

  int isSumTree(Node node) {

    int ls;

    int rs;

    if (node == null || isLeaf(node) == 1) return 1;

    if (isSumTree(node.left) != 0 && isSumTree(node.right) != 0) {

      if (node.left == null) ls = 0;
      else if (isLeaf(node.left) != 0) ls = node.left.data;
      else ls = 2 * (node.left.data);

      if (node.right == null) rs = 0;
      else if (isLeaf(node.right) != 0) rs = node.right.data;
      else rs = 2 * (node.right.data);

      if ((node.data == rs + ls)) return 1;
      else return 0;
    }

    return 0;
  }

  public static void main(String args[]) {

    BinaryTree tree = new BinaryTree();

    tree.root = new Node(26);

    tree.root.left = new Node(10);

    tree.root.right = new Node(3);

    tree.root.left.left = new Node(4);

    tree.root.left.right = new Node(6);

    tree.root.right.right = new Node(3);

    if (tree.isSumTree(tree.root) != 0) System.out.println("The given tree is a sum tree");
    else System.out.println("The given tree is not a sum tree");
  }
}
