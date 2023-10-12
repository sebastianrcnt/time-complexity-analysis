import java.io.*;
import java.util.*;

class GFG {

  static int maxSumPairWithDifferenceLessThanK(int arr[], int N, int K) {

    Arrays.sort(arr);

    int dp[] = new int[N];

    dp[0] = 0;

    for (int i = 1; i < N; i++) {

      dp[i] = dp[i - 1];

      if (arr[i] - arr[i - 1] < K) {

        if (i >= 2) dp[i] = Math.max(dp[i], dp[i - 2] + arr[i] + arr[i - 1]);
        else dp[i] = Math.max(dp[i], arr[i] + arr[i - 1]);
      }
    }

    return dp[N - 1];
  }

  public static void main(String[] args) {

    int arr[] = {3, 5, 10, 15, 17, 12, 9};

    int N = arr.length;

    int K = 4;

    System.out.println(maxSumPairWithDifferenceLessThanK(arr, N, K));
  }
}
