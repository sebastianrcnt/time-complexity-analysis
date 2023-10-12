import java.io.*;

class GFG {

  static int findMaxAverage(int arr[], int n, int k) {

    if (k > n) return -1;

    int sum = arr[0];

    for (int i = 1; i < k; i++) sum += arr[i];

    int max_sum = sum, max_end = k - 1;

    for (int i = k; i < n; i++) {

      sum = sum + arr[i] - arr[i - k];

      if (sum > max_sum) {

        max_sum = sum;

        max_end = i;
      }
    }

    return max_end - k + 1;
  }

  public static void main(String[] args) {

    int arr[] = {1, 12, -5, -6, 50, 3};

    int k = 4;

    int n = arr.length;

    System.out.println(
        "The maximum average"
            + " subarray of length "
            + k
            + " begins at index "
            + findMaxAverage(arr, n, k));
  }
}
