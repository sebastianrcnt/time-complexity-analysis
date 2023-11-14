class Node {

  int data;

  Node left, right;

  Node(int item) {

    data = item;

    left = right = null;
  }
}

class Index {

  int index = 0;
}

class BinaryTree {

  Node root;

  Index myindex = new Index();

  Node constructTreeUtil(int pre[], char preLN[], Index index_ptr, int n, Node temp) {

    int index = index_ptr.index;

    if (index == n) return null;

    temp = new Node(pre[index]);

    (index_ptr.index)++;

    if (preLN[index] == 'N') {

      temp.left = constructTreeUtil(pre, preLN, index_ptr, n, temp.left);

      temp.right = constructTreeUtil(pre, preLN, index_ptr, n, temp.right);
    }

    return temp;
  }

  Node constructTree(int pre[], char preLN[], int n, Node node) {

    int index = 0;

    return constructTreeUtil(pre, preLN, myindex, n, node);
  }

  void printInorder(Node node) {

    if (node == null) return;

    printInorder(node.left);

    System.out.print(node.data + " ");

    printInorder(node.right);
  }

  public static void main(String args[]) {

    BinaryTree tree = new BinaryTree();

    int pre[] = new int[] {10, 30, 20, 5, 15};

    char preLN[] = new char[] {'N', 'N', 'L', 'L', 'L'};

    int n = pre.length;

    Node mynode = tree.constructTree(pre, preLN, n, tree.root);

    System.out.println("Following is Inorder Traversal of the" + "Constructed Binary Tree: ");

    tree.printInorder(mynode);
  }
}
