class Node {

  char data;

  Node left, right;

  Node(char item) {

    data = item;

    left = right = null;
  }
}

class Passing {

  int i;

  int m = 0;

  int n = 0;
}

class BinaryTree {

  static Node root;

  Passing p = new Passing();

  String strstr(String haystack, String needle) {

    if (haystack == null || needle == null) {

      return null;
    }

    int hLength = haystack.length();

    int nLength = needle.length();

    if (hLength < nLength) {

      return null;
    }

    if (nLength == 0) {

      return haystack;
    }

    for (int i = 0; i <= hLength - nLength; i++) {

      if (haystack.charAt(i) == needle.charAt(0)) {

        int j = 0;

        for (; j < nLength; j++) {

          if (haystack.charAt(i + j) != needle.charAt(j)) {

            break;
          }
        }

        if (j == nLength) {

          return haystack.substring(i);
        }
      }
    }

    return null;
  }

  void storeInorder(Node node, char arr[], Passing i) {

    if (node == null) {

      arr[i.i++] = '$';

      return;
    }

    storeInorder(node.left, arr, i);

    arr[i.i++] = node.data;

    storeInorder(node.right, arr, i);
  }

  void storePreOrder(Node node, char arr[], Passing i) {

    if (node == null) {

      arr[i.i++] = '$';

      return;
    }

    arr[i.i++] = node.data;

    storePreOrder(node.left, arr, i);

    storePreOrder(node.right, arr, i);
  }

  boolean isSubtree(Node T, Node S) {

    if (S == null) {

      return true;
    }

    if (T == null) {

      return false;
    }

    char inT[] = new char[100];

    String op1 = String.valueOf(inT);

    char inS[] = new char[100];

    String op2 = String.valueOf(inS);

    storeInorder(T, inT, p);

    storeInorder(S, inS, p);

    inT[p.m] = '\0';

    inS[p.m] = '\0';

    if (strstr(op1, op2) != null) {

      return false;
    }

    p.m = 0;

    p.n = 0;

    char preT[] = new char[100];

    char preS[] = new char[100];

    String op3 = String.valueOf(preT);

    String op4 = String.valueOf(preS);

    storePreOrder(T, preT, p);

    storePreOrder(S, preS, p);

    preT[p.m] = '\0';

    preS[p.n] = '\0';

    return (strstr(op3, op4) != null);
  }

  public static void main(String args[]) {

    BinaryTree tree = new BinaryTree();

    Node T = new Node('a');

    T.left = new Node('b');

    T.right = new Node('d');

    T.left.left = new Node('c');

    T.right.right = new Node('e');

    Node S = new Node('a');

    S.left = new Node('b');

    S.right = new Node('d');

    S.left.left = new Node('c');

    if (tree.isSubtree(T, S)) {

      System.out.println("Yes, S is a subtree of T");

    } else {

      System.out.println("No, S is not a subtree of T");
    }
  }
}
