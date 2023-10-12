import java.io.*;

class GFG {

  static int findMaxAverage(int[] arr, int n, int k) {

    if (k > n) return -1;

    int[] csum = new int[n];

    csum[0] = arr[0];

    for (int i = 1; i < n; i++) csum[i] = csum[i - 1] + arr[i];

    int max_sum = csum[k - 1], max_end = k - 1;

    for (int i = k; i < n; i++) {

      int curr_sum = csum[i] - csum[i - k];

      if (curr_sum > max_sum) {

        max_sum = curr_sum;

        max_end = i;
      }
    }

    return max_end - k + 1;
  }

  public static void main(String[] args) {

    int[] arr = {1, 12, -5, -6, 50, 3};

    int k = 4;

    int n = arr.length;

    System.out.println(
        "The maximum "
            + "average subarray of length "
            + k
            + " begins at index "
            + findMaxAverage(arr, n, k));
  }
}
