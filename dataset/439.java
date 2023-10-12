class LinkedList {

  static LNode head;

  class LNode {

    int data;

    LNode next, prev;

    LNode(int d) {

      data = d;

      next = prev = null;
    }
  }

  class TNode {

    int data;

    TNode left, right;

    TNode(int d) {

      data = d;

      left = right = null;
    }
  }

  TNode sortedListToBST() {

    int n = countNodes(head);

    return sortedListToBSTRecur(n);
  }

  TNode sortedListToBSTRecur(int n) {

    if (n <= 0) return null;

    TNode left = sortedListToBSTRecur(n / 2);

    TNode root = new TNode(head.data);

    root.left = left;

    head = head.next;

    root.right = sortedListToBSTRecur(n - n / 2 - 1);

    return root;
  }

  int countNodes(LNode head) {

    int count = 0;

    LNode temp = head;

    while (temp != null) {

      temp = temp.next;

      count++;
    }

    return count;
  }

  void push(int new_data) {

    LNode new_node = new LNode(new_data);

    new_node.prev = null;

    new_node.next = head;

    if (head != null) head.prev = new_node;

    head = new_node;
  }

  void printList(LNode node) {

    while (node != null) {

      System.out.print(node.data + " ");

      node = node.next;
    }
  }

  void preOrder(TNode node) {

    if (node == null) return;

    System.out.print(node.data + " ");

    preOrder(node.left);

    preOrder(node.right);
  }

  public static void main(String[] args) {

    LinkedList llist = new LinkedList();

    llist.push(7);

    llist.push(6);

    llist.push(5);

    llist.push(4);

    llist.push(3);

    llist.push(2);

    llist.push(1);

    System.out.println("Given Linked List ");

    llist.printList(head);

    TNode root = llist.sortedListToBST();

    System.out.println("");

    System.out.println("Pre-Order Traversal of constructed BST ");

    llist.preOrder(root);
  }
}
