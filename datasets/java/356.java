import java.io.*;

class GFG {

  static int nonDecNums(int n) {

    int[][] a = new int[n + 1][10];

    for (int i = 0; i <= 9; i++) a[0][i] = 1;

    for (int i = 1; i <= n; i++) a[i][9] = 1;

    for (int i = 1; i <= n; i++) for (int j = 8; j >= 0; j--) a[i][j] = a[i - 1][j] + a[i][j + 1];

    return a[n][0];
  }

  public static void main(String[] args) {

    int n = 2;

    System.out.println("Non-decreasing digits = " + nonDecNums(n));
  }
}
