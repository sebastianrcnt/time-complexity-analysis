import java.util.Arrays;

class GFG {

  static int largestSubset(int[] a, int n) {

    Arrays.sort(a);

    int[] dp = new int[n];

    dp[n - 1] = 1;

    for (int i = n - 2; i >= 0; i--) {

      int mxm = 0;

      for (int j = i + 1; j < n; j++) {

        if (a[j] % a[i] == 0) {

          mxm = Math.max(mxm, dp[j]);
        }
      }

      dp[i] = 1 + mxm;
    }

    return Arrays.stream(dp).max().getAsInt();
  }

  public static void main(String[] args) {

    int[] a = {1, 3, 6, 13, 17, 18};

    int n = a.length;

    System.out.println(largestSubset(a, n));
  }
}
