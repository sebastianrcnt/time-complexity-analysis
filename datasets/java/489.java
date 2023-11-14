import java.io.*;

class GFG {

  static void SwapInts(int array[], int position1, int position2) {

    int temp = array[position1];

    array[position1] = array[position2];

    array[position2] = temp;
  }

  static void KSwapMaximum(int[] arr, int n, int k) {

    for (int i = 0; i < n - 1 && k > 0; ++i) {

      int indexPosition = i;

      for (int j = i + 1; j < n; ++j) {

        if (k <= j - i) break;

        if (arr[j] > arr[indexPosition]) indexPosition = j;
      }

      for (int j = indexPosition; j > i; --j) SwapInts(arr, j, j - 1);

      k -= indexPosition - i;
    }
  }

  public static void main(String args[]) {

    int[] arr = {3, 5, 4, 1, 2};

    int n = arr.length;

    int k = 3;

    KSwapMaximum(arr, n, k);

    for (int i = 0; i < n; ++i) System.out.print(arr[i] + " ");
  }
}
