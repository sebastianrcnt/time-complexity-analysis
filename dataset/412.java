class Node {

  char data;

  Node left, right;

  public Node(char item) {

    data = item;

    left = null;

    right = null;
  }
}

class BinaryTree {

  Node convertExpression(char[] expression, int i) {

    if (i >= expression.length) return null;

    Node root = new Node(expression[i]);

    ++i;

    if (i < expression.length && expression[i] == '?')
      root.left = convertExpression(expression, i + 1);
    else if (i < expression.length) root.right = convertExpression(expression, i + 1);

    return root;
  }

  public void printTree(Node root) {

    if (root == null) return;

    System.out.print(root.data + " ");

    printTree(root.left);

    printTree(root.right);
  }

  public static void main(String args[]) {

    String exp = "a?b?c:d:e";

    BinaryTree tree = new BinaryTree();

    char[] expression = exp.toCharArray();

    Node root = tree.convertExpression(expression, 0);

    tree.printTree(root);
  }
}
