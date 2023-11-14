class Node {

  int data;

  Node left, right;

  Node(int d) {

    data = d;

    left = right = null;
  }
}

class BinarySearchTree {

  Node root;

  BinarySearchTree() {

    root = null;
  }

  void inorder() {

    inorderUtil(this.root);
  }

  void inorderUtil(Node node) {

    if (node == null) return;

    inorderUtil(node.left);

    System.out.print(node.data + " ");

    inorderUtil(node.right);
  }

  public void insert(int data) {

    this.root = this.insertRec(this.root, data);
  }

  Node insertRec(Node node, int data) {

    if (node == null) {

      this.root = new Node(data);

      return this.root;
    }

    if (data <= node.data) {

      node.left = this.insertRec(node.left, data);

    } else {

      node.right = this.insertRec(node.right, data);
    }

    return node;
  }

  public class Sum {

    int sum = 0;
  }

  void modifyBSTUtil(Node node, Sum S) {

    if (node == null) return;

    this.modifyBSTUtil(node.right, S);

    S.sum = S.sum + node.data;

    node.data = S.sum;

    this.modifyBSTUtil(node.left, S);
  }

  void modifyBST(Node node) {

    Sum S = new Sum();

    this.modifyBSTUtil(node, S);
  }

  public static void main(String[] args) {

    BinarySearchTree tree = new BinarySearchTree();

    tree.insert(50);

    tree.insert(30);

    tree.insert(20);

    tree.insert(40);

    tree.insert(70);

    tree.insert(60);

    tree.insert(80);

    tree.modifyBST(tree.root);

    tree.inorder();
  }
}
