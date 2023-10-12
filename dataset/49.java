import java.io.*;
import java.util.*;

class GFG {

  static void findElements(int arr[], int n) {

    int first = Integer.MIN_VALUE;

    int second = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {

      if (arr[i] > first) {

        second = first;

        first = arr[i];

      } else if (arr[i] > second) second = arr[i];
    }

    for (int i = 0; i < n; i++) if (arr[i] < second) System.out.print(arr[i] + " ");
  }

  public static void main(String args[]) {

    int arr[] = {2, -6, 3, 5, 1};

    int n = arr.length;

    findElements(arr, n);
  }
}
