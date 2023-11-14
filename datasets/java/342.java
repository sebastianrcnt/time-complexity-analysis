import java.util.Arrays;

class GFG {

  static int MAX = 100;

  static int dp[][] = new int[MAX][MAX];

  static int countRemovals(int a[], int i, int j, int k) {

    if (i >= j) return 0;
    else if ((a[j] - a[i]) <= k) return 0;
    else if (dp[i][j] != -1) return dp[i][j];
    else if ((a[j] - a[i]) > k) {

      dp[i][j] = 1 + Math.min(countRemovals(a, i + 1, j, k), countRemovals(a, i, j - 1, k));
    }

    return dp[i][j];
  }

  static int removals(int a[], int n, int k) {

    Arrays.sort(a);

    for (int[] rows : dp) Arrays.fill(rows, -1);

    if (n == 1) return 0;
    else return countRemovals(a, 0, n - 1, k);
  }

  public static void main(String[] args) {

    int a[] = {1, 3, 4, 9, 10, 11, 12, 17, 20};

    int n = a.length;

    int k = 4;

    System.out.print(removals(a, n, k));
  }
}
