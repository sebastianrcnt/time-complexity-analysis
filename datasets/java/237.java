import java.util.*;

class solution {

  static void printFirstNegativeInteger(int arr[], int n, int k) {

    boolean flag;

    for (int i = 0; i < (n - k + 1); i++) {

      flag = false;

      for (int j = 0; j < k; j++) {

        if (arr[i + j] < 0) {

          System.out.print((arr[i + j]) + " ");

          flag = true;

          break;
        }
      }

      if (!flag) System.out.print("0" + " ");
    }
  }

  public static void main(String args[]) {

    int arr[] = {12, -1, -7, 8, -15, 30, 16, 28};

    int n = arr.length;

    int k = 3;

    printFirstNegativeInteger(arr, n, k);
  }
}
