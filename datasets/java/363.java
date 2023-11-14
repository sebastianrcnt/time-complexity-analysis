import java.io.*;
import java.util.*;

class GFG {

  static void printEqualSumSets(int[] arr, int n) {

    int i, currSum, sum = 0;

    for (i = 0; i < arr.length; i++) sum += arr[i];

    if ((sum & 1) == 1) {

      System.out.print("-1");

      return;
    }

    int k = sum >> 1;

    boolean[][] dp = new boolean[n + 1][k + 1];

    for (i = 1; i <= k; i++) dp[0][i] = false;

    for (i = 0; i <= n; i++) dp[i][0] = true;

    for (i = 1; i <= n; i++) {

      for (currSum = 1; currSum <= k; currSum++) {

        dp[i][currSum] = dp[i - 1][currSum];

        if (arr[i - 1] <= currSum)
          dp[i][currSum] = dp[i][currSum] | dp[i - 1][currSum - arr[i - 1]];
      }
    }

    List<Integer> set1 = new ArrayList<Integer>();

    List<Integer> set2 = new ArrayList<Integer>();

    if (!dp[n][k]) {

      System.out.print("-1\n");

      return;
    }

    i = n;

    currSum = k;

    while (i > 0 && currSum >= 0) {

      if (dp[i - 1][currSum]) {

        i--;

        set2.add(arr[i]);

      } else if (dp[i - 1][currSum - arr[i - 1]]) {

        i--;

        currSum -= arr[i];

        set1.add(arr[i]);
      }
    }

    System.out.print("Set 1 elements: ");

    for (i = 0; i < set1.size(); i++) System.out.print(set1.get(i) + " ");

    System.out.print("\nSet 2 elements: ");

    for (i = 0; i < set2.size(); i++) System.out.print(set2.get(i) + " ");
  }

  public static void main(String args[]) {

    int[] arr = new int[] {5, 5, 1, 11};

    int n = arr.length;

    printEqualSumSets(arr, n);
  }
}
