import java.io.*;

class GFG {

  final int MAX = 1000000;

  static int breakSum(int n) {

    int dp[] = new int[n + 1];

    dp[0] = 0;
    dp[1] = 1;

    for (int i = 2; i <= n; i++) dp[i] = Math.max(dp[i / 2] + dp[i / 3] + dp[i / 4], i);

    return dp[n];
  }

  public static void main(String[] args) {

    int n = 24;

    System.out.println(breakSum(n));
  }
}
