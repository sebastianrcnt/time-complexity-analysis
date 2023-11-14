import java.io.*;
import java.math.*;

class GFG {

  static int permutationCoeff(int n, int k) {

    int P[][] = new int[n + 2][k + 2];

    for (int i = 0; i <= n; i++) {

      for (int j = 0; j <= Math.min(i, k); j++) {

        if (j == 0) P[i][j] = 1;
        else P[i][j] = P[i - 1][j] + (j * P[i - 1][j - 1]);

        P[i][j + 1] = 0;
      }
    }

    return P[n][k];
  }

  public static void main(String args[]) {

    int n = 10, k = 2;

    System.out.println("Value of P( " + n + "," + k + ")" + " is " + permutationCoeff(n, k));
  }
}
