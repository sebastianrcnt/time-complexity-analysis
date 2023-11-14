class GFG {

  static int maxRevenue(int m, int[] x, int[] revenue, int n, int t) {

    int[] maxRev = new int[m + 1];

    for (int i = 0; i < m + 1; i++) maxRev[i] = 0;

    int nxtbb = 0;

    for (int i = 1; i <= m; i++) {

      if (nxtbb < n) {

        if (x[nxtbb] != i) maxRev[i] = maxRev[i - 1];
        else {

          if (i <= t) maxRev[i] = Math.max(maxRev[i - 1], revenue[nxtbb]);
          else maxRev[i] = Math.max(maxRev[i - t - 1] + revenue[nxtbb], maxRev[i - 1]);

          nxtbb++;
        }

      } else maxRev[i] = maxRev[i - 1];
    }

    return maxRev[m];
  }

  public static void main(String[] args) {

    int m = 20;

    int[] x = new int[] {6, 7, 12, 13, 14};

    int[] revenue = new int[] {5, 6, 5, 3, 1};

    int n = x.length;

    int t = 5;

    System.out.println(maxRevenue(m, x, revenue, n, t));
  }
}
