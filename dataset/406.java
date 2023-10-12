class Node {

  int data;

  Node left, right;

  public Node(int data) {

    this.data = data;

    left = right = null;
  }
}

class BinaryTree {

  Node root;

  Node head;

  void BToDLL(Node root) {

    if (root == null) return;

    BToDLL(root.right);

    root.right = head;

    if (head != null) (head).left = root;

    head = root;

    BToDLL(root.left);
  }

  void printList(Node head) {

    System.out.println("Extracted Double Linked List is : ");

    while (head != null) {

      System.out.print(head.data + " ");

      head = head.right;
    }
  }

  public static void main(String[] args) {

    BinaryTree tree = new BinaryTree();

    tree.root = new Node(5);

    tree.root.left = new Node(3);

    tree.root.right = new Node(6);

    tree.root.left.right = new Node(4);

    tree.root.left.left = new Node(1);

    tree.root.right.right = new Node(8);

    tree.root.left.left.right = new Node(2);

    tree.root.left.left.left = new Node(0);

    tree.root.right.right.left = new Node(7);

    tree.root.right.right.right = new Node(9);

    tree.BToDLL(tree.root);

    tree.printList(tree.head);
  }
}
