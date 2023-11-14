import java.util.*;

class ListNode {

  int data;

  ListNode next;

  ListNode(int d) {

    data = d;

    next = null;
  }
}

class BinaryTreeNode {

  int data;

  BinaryTreeNode left, right = null;

  BinaryTreeNode(int data) {

    this.data = data;

    left = right = null;
  }
}

class BinaryTree {

  ListNode head;

  BinaryTreeNode root;

  void push(int new_data) {

    ListNode new_node = new ListNode(new_data);

    new_node.next = head;

    head = new_node;
  }

  BinaryTreeNode convertList2Binary(BinaryTreeNode node) {

    Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();

    if (head == null) {

      node = null;

      return null;
    }

    node = new BinaryTreeNode(head.data);

    q.add(node);

    head = head.next;

    while (head != null) {

      BinaryTreeNode parent = q.peek();

      BinaryTreeNode pp = q.poll();

      BinaryTreeNode leftChild = null, rightChild = null;

      leftChild = new BinaryTreeNode(head.data);

      q.add(leftChild);

      head = head.next;

      if (head != null) {

        rightChild = new BinaryTreeNode(head.data);

        q.add(rightChild);

        head = head.next;
      }

      parent.left = leftChild;

      parent.right = rightChild;
    }

    return node;
  }

  void inorderTraversal(BinaryTreeNode node) {

    if (node != null) {

      inorderTraversal(node.left);

      System.out.print(node.data + " ");

      inorderTraversal(node.right);
    }
  }

  public static void main(String[] args) {

    BinaryTree tree = new BinaryTree();

    tree.push(36);

    tree.push(30);

    tree.push(25);

    tree.push(15);

    tree.push(12);

    tree.push(10);

    BinaryTreeNode node = tree.convertList2Binary(tree.root);

    System.out.println("Inorder Traversal of the" + " constructed Binary Tree is:");

    tree.inorderTraversal(node);
  }
}
