import java.io.*;

class GFG {

  static void constructDp(int dp[][], String str) {

    int l = str.length();

    int[][] isPalin = new int[l + 1][l + 1];

    for (int i = 0; i <= l; i++) {

      for (int j = 0; j <= l; j++) {

        isPalin[i][j] = dp[i][j] = 0;
      }
    }

    for (int i = l - 1; i >= 0; i--) {

      isPalin[i][i] = 1;

      dp[i][i] = 1;

      for (int j = i + 1; j < l; j++) {

        isPalin[i][j] =
            (str.charAt(i) == str.charAt(j) && (i + 1 > j - 1 || (isPalin[i + 1][j - 1]) != 0))
                ? 1
                : 0;

        dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1] + isPalin[i][j];
      }
    }
  }

  static int countOfPalindromeInRange(int dp[][], int l, int r) {

    return dp[l][r];
  }

  public static void main(String args[]) {

    int MAX = 50;

    String str = "xyaabax";

    int[][] dp = new int[MAX][MAX];

    constructDp(dp, str);

    int l = 3;

    int r = 5;

    System.out.println(countOfPalindromeInRange(dp, l, r));
  }
}
