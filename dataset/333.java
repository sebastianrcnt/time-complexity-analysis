import java.util.*;

class GFG {

  public static int maxSum(int grid[][], int n) {

    int incl = Math.max(grid[0][0], grid[1][0]);

    int excl = 0, excl_new;

    for (int i = 1; i < n; i++) {

      excl_new = Math.max(excl, incl);

      incl = excl + Math.max(grid[0][i], grid[1][i]);

      excl = excl_new;
    }

    return Math.max(excl, incl);
  }

  public static void main(String[] args) {

    int grid[][] = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};

    int n = 5;

    System.out.println(maxSum(grid, n));
  }
}
