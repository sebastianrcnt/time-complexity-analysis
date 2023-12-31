class GFG {

  static int[] dp = new int[1000];

  static int countFriendsPairings(int n) {

    if (dp[n] != -1) return dp[n];

    if (n > 2) return dp[n] = countFriendsPairings(n - 1) + (n - 1) * countFriendsPairings(n - 2);
    else return dp[n] = n;
  }

  public static void main(String[] args) {

    for (int i = 0; i < 1000; i++) dp[i] = -1;

    int n = 4;

    System.out.println(countFriendsPairings(n));
  }
}
