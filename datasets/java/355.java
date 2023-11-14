import java.io.*;

class Grid {

  static int count(int n) {

    int[] dp = new int[n + 1];

    dp[0] = 0;

    for (int i = 1; i <= n; i++) {

      if (i >= 1 && i <= 3) dp[i] = 1;
      else if (i == 4) dp[i] = 2;
      else {

        dp[i] = dp[i - 1] + dp[i - 4];
      }
    }

    return dp[n];
  }

  public static void main(String[] args) {

    int n = 5;

    System.out.println("Count of ways is: " + count(n));
  }
}
