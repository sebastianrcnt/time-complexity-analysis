import java.io.*;

class GFG {

  static int findSubsequenceCount(String S, String T) {

    int m = T.length();

    int n = S.length();

    if (m > n) return 0;

    int mat[][] = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) mat[i][0] = 0;

    for (int j = 0; j <= n; j++) mat[0][j] = 1;

    for (int i = 1; i <= m; i++) {

      for (int j = 1; j <= n; j++) {

        if (T.charAt(i - 1) != S.charAt(j - 1)) mat[i][j] = mat[i][j - 1];
        else mat[i][j] = mat[i][j - 1] + mat[i - 1][j - 1];
      }
    }

    return mat[m][n];
  }

  public static void main(String[] args) {

    String T = "ge";

    String S = "geeksforgeeks";

    System.out.println(findSubsequenceCount(S, T));
  }
}
