import java.io.*;

class GFG {

  static int lookup[][] = new int[1000][1000];

  static int countSeqUtil(int n, int dif) {

    if (Math.abs(dif) > n) return 0;

    if (n == 1 && dif == 0) return 2;

    if (n == 1 && Math.abs(dif) == 1) return 1;

    if (lookup[n][n + dif] != -1) return lookup[n][n + dif];

    int res =
        countSeqUtil(n - 1, dif + 1) + 2 * countSeqUtil(n - 1, dif) + countSeqUtil(n - 1, dif - 1);

    return lookup[n][n + dif] = res;
  }

  static int countSeq(int n) {

    for (int k = 0; k < lookup.length; k++) {

      for (int j = 0; j < lookup.length; j++) {

        lookup[k][j] = -1;
      }
    }

    return countSeqUtil(n, 0);
  }

  public static void main(String[] args) {

    int n = 2;

    System.out.println("Count of sequences is " + countSeq(2));
  }
}
