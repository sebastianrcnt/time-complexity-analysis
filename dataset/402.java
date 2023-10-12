class Node {

  int data;

  Node left, right;

  Node(int item) {

    data = item;

    left = right = null;
  }
}

class BinaryTree {

  Node root;

  Node buildTree(int inorder[], int start, int end, Node node) {

    if (start > end) return null;

    int i = max(inorder, start, end);

    node = new Node(inorder[i]);

    if (start == end) return node;

    node.left = buildTree(inorder, start, i - 1, node.left);

    node.right = buildTree(inorder, i + 1, end, node.right);

    return node;
  }

  int max(int arr[], int strt, int end) {

    int i, max = arr[strt], maxind = strt;

    for (i = strt + 1; i <= end; i++) {

      if (arr[i] > max) {

        max = arr[i];

        maxind = i;
      }
    }

    return maxind;
  }

  void printInorder(Node node) {

    if (node == null) return;

    printInorder(node.left);

    System.out.print(node.data + " ");

    printInorder(node.right);
  }

  public static void main(String args[]) {

    BinaryTree tree = new BinaryTree();

    int inorder[] = new int[] {5, 10, 40, 30, 28};

    int len = inorder.length;

    Node mynode = tree.buildTree(inorder, 0, len - 1, tree.root);

    System.out.println("Inorder traversal of the constructed tree is ");

    tree.printInorder(mynode);
  }
}
