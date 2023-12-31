class Node {

  char data;

  Node left, right;

  Node(char item) {

    data = item;

    left = right = null;
  }
}

class Index {

  int index;
}

class BinaryTree {

  Node root;

  Index index_obj = new Index();

  void storeAlternate(Node node, char arr[], Index index, int l) {

    if (node == null) {

      return;
    }

    storeAlternate(node.left, arr, index, l + 1);

    if (l % 2 != 0) {

      arr[index.index] = node.data;

      index.index++;
    }

    storeAlternate(node.right, arr, index, l + 1);
  }

  void modifyTree(Node node, char arr[], Index index, int l) {

    if (node == null) {

      return;
    }

    modifyTree(node.left, arr, index, l + 1);

    if (l % 2 != 0) {

      node.data = arr[index.index];

      (index.index)++;
    }

    modifyTree(node.right, arr, index, l + 1);
  }

  void reverse(char arr[], int n) {

    int l = 0, r = n - 1;

    while (l < r) {

      char temp = arr[l];

      arr[l] = arr[r];

      arr[r] = temp;

      l++;

      r--;
    }
  }

  void reverseAlternate() {

    reverseAlternate(root);
  }

  void reverseAlternate(Node node) {

    char[] arr = new char[100];

    storeAlternate(node, arr, index_obj, 0);

    reverse(arr, index_obj.index);

    index_obj.index = 0;

    modifyTree(node, arr, index_obj, 0);
  }

  void printInorder() {

    printInorder(root);
  }

  void printInorder(Node node) {

    if (node == null) {

      return;
    }

    printInorder(node.left);

    System.out.print(node.data + " ");

    printInorder(node.right);
  }

  public static void main(String args[]) {

    BinaryTree tree = new BinaryTree();

    tree.root = new Node('a');

    tree.root.left = new Node('b');

    tree.root.right = new Node('c');

    tree.root.left.left = new Node('d');

    tree.root.left.right = new Node('e');

    tree.root.right.left = new Node('f');

    tree.root.right.right = new Node('g');

    tree.root.left.left.left = new Node('h');

    tree.root.left.left.right = new Node('i');

    tree.root.left.right.left = new Node('j');

    tree.root.left.right.right = new Node('k');

    tree.root.right.left.left = new Node('l');

    tree.root.right.left.right = new Node('m');

    tree.root.right.right.left = new Node('n');

    tree.root.right.right.right = new Node('o');

    System.out.println("Inorder Traversal of given tree");

    tree.printInorder();

    tree.reverseAlternate();

    System.out.println("");

    System.out.println("");

    System.out.println("Inorder Traversal of modified tree");

    tree.printInorder();
  }
}
