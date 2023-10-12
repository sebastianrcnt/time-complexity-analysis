import java.io.*;
import java.util.Arrays;

public class GFG {

  static void preprocess(int[] coin, int n) {

    Arrays.sort(coin);

    for (int i = 1; i <= n - 1; i++) coin[i] += coin[i - 1];
  }

  static int minCost(int[] coin, int n, int k) {

    int coins_needed = (int) Math.ceil(1.0 * n / (k + 1));

    return coin[coins_needed - 1];
  }

  public static void main(String[] args) {

    int[] coin = {8, 5, 3, 10, 2, 1, 15, 25};

    int n = coin.length;

    preprocess(coin, n);

    int k = 3;

    System.out.println(minCost(coin, n, k));

    k = 7;

    System.out.println(minCost(coin, n, k));
  }
}
