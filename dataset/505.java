import java.io.*;
import java.util.Arrays;

class GFG {

  static int minOps(int arr[], int n, int k) {

    Arrays.sort(arr);

    int max = arr[arr.length - 1];

    int res = 0;

    for (int i = 0; i < n; i++) {

      if ((max - arr[i]) % k != 0) return -1;
      else res += (max - arr[i]) / k;
    }

    return res;
  }

  public static void main(String[] args) {

    int arr[] = {21, 33, 9, 45, 63};

    int n = arr.length;

    int k = 6;

    System.out.println(minOps(arr, n, k));
  }
}
