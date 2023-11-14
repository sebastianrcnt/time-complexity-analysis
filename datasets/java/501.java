import java.util.Arrays;

class GFG {

  static int minCost(int coin[], int n, int k) {

    Arrays.sort(coin);

    int coins_needed = (int) Math.ceil(1.0 * n / (k + 1));

    int ans = 0;

    for (int i = 0; i <= coins_needed - 1; i++) ans += coin[i];

    return ans;
  }

  public static void main(String arg[]) {

    int coin[] = {8, 5, 3, 10, 2, 1, 15, 25};

    int n = coin.length;

    int k = 3;

    System.out.print(minCost(coin, n, k));
  }
}
