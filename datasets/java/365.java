class GFG {

  static int max(int x, int y) {

    return (x > y ? x : y);
  }

  static int maxTasks(int[] high, int[] low, int n) {

    int[] task_dp = new int[n + 1];

    task_dp[0] = 0;

    task_dp[1] = high[0];

    for (int i = 2; i <= n; i++)
      task_dp[i] = Math.max(high[i - 1] + task_dp[i - 2], low[i - 1] + task_dp[i - 1]);

    return task_dp[n];
  }

  public static void main(String[] args) {

    int n = 5;

    int[] high = {3, 6, 8, 7, 6};

    int[] low = {1, 5, 4, 5, 3};

    System.out.println(maxTasks(high, low, n));
  }
}
