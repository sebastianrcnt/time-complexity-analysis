import java.util.*;

class GFG {

  public static int maxLenSub(int arr[], int n) {

    int mls[] = new int[n], max = 0;

    for (int i = 0; i < n; i++) mls[i] = 1;

    for (int i = 1; i < n; i++)
      for (int j = 0; j < i; j++)
        if (Math.abs(arr[i] - arr[j]) <= 1 && mls[i] < mls[j] + 1) mls[i] = mls[j] + 1;

    for (int i = 0; i < n; i++) if (max < mls[i]) max = mls[i];

    return max;
  }

  public static void main(String[] args) {

    int arr[] = {2, 5, 6, 3, 7, 6, 5, 8};

    int n = arr.length;

    System.out.println("Maximum length subsequence = " + maxLenSub(arr, n));
  }
}
