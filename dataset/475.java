import java.io.*;
import java.util.Arrays;

class MaxSum {

  static int maxSum(int arr[], int n) {

    int sum = 0;

    Arrays.sort(arr);

    for (int i = 0; i < n / 2; i++) {

      sum -= (2 * arr[i]);

      sum += (2 * arr[n - i - 1]);
    }

    return sum;
  }

  public static void main(String[] args) {

    int arr[] = {4, 2, 1, 8};

    int n = arr.length;

    System.out.println(maxSum(arr, n));
  }
}
