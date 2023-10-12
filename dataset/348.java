import java.io.*;
import java.util.*;

public class GFG {

  static int LCSubStr(String X, String Y) {

    int m = X.length();

    int n = Y.length();

    int result = 0;

    int[][] len = new int[2][n];

    int currRow = 0;

    for (int i = 0; i < m; i++) {

      for (int j = 0; j < n; j++) {

        if (i == 0 || j == 0) {

          len[currRow][j] = 0;

        } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {

          len[currRow][j] = len[(1 - currRow)][(j - 1)] + 1;

          result = Math.max(result, len[currRow][j]);

        } else {

          len[currRow][j] = 0;
        }
      }

      currRow = 1 - currRow;
    }

    return result;
  }

  public static void main(String args[]) {

    String X = "GeeksforGeeks";

    String Y = "GeeksQuiz";

    System.out.print(LCSubStr(X, Y));
  }
}
