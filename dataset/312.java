class GFG {

  static int countSeq(int n) {

    int nCr = 1, res = 1;

    for (int r = 1; r <= n; r++) {

      nCr = (nCr * (n + 1 - r)) / r;

      res += nCr * nCr;
    }

    return res;
  }

  public static void main(String args[]) {

    int n = 2;

    System.out.print("Count of sequences is ");

    System.out.println(countSeq(n));
  }
}
