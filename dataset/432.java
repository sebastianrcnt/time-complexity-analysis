public class CheckForPath {

  public static boolean existPath(Node root, int arr[], int index) {

    if (root == null) {

      return arr.length == 0;
    }

    if ((root.left == null && root.right == null)
        && (root.data == arr[index] && root.data == arr[arr.length - 1])) {

      return true;
    }

    return (index < arr.length
        && (root.data == arr[index]
            && (existPath(root.left, arr, index + 1) || existPath(root.right, arr, index + 1))));
  }

  public static void main(String args[]) {

    int arr[] = {5, 8, 6, 7};

    Node root = new Node(5);

    root.left = new Node(3);

    root.right = new Node(8);

    root.left.left = new Node(2);

    root.left.right = new Node(4);

    root.left.left.left = new Node(1);

    root.right.left = new Node(6);

    root.right.left.right = new Node(7);

    if (existPath(root, arr, 0)) {

      System.out.print("Path Exists");

    } else {

      System.out.print("Path does not Exist");
    }
  }
}

class Node {

  int data;

  Node left, right;

  Node(int data) {

    this.data = data;

    left = right = null;
  }
}
;
