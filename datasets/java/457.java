import java.util.*;

class GFG {

  static void pairs(int arr[], int n, int k) {

    int smallest = Integer.MAX_VALUE;

    int count = 0;

    for (int i = 0; i < n; i++)
      for (int j = i + 1; j < n; j++) {

        if (Math.abs(arr[i] + arr[j] - k) < smallest) {

          smallest = Math.abs(arr[i] + arr[j] - k);

          count = 1;

        } else if (Math.abs(arr[i] + arr[j] - k) == smallest) count++;
      }

    System.out.println("Minimal Value = " + smallest);

    System.out.println("Total Pairs = " + count);
  }

  public static void main(String[] args) {

    int arr[] = {3, 5, 7, 5, 1, 9, 9};

    int k = 12;

    int n = arr.length;

    pairs(arr, n, k);
  }
}
