import java.util.*;

class GFG {

  public static void splitArr(int arr[], int n, int k) {

    for (int i = 0; i < k; i++) {

      int x = arr[0];

      for (int j = 0; j < n - 1; ++j) arr[j] = arr[j + 1];

      arr[n - 1] = x;
    }
  }

  public static void main(String[] args) {

    int arr[] = {12, 10, 5, 6, 52, 36};

    int n = arr.length;

    int position = 2;

    splitArr(arr, 6, position);

    for (int i = 0; i < n; ++i) System.out.print(arr[i] + " ");
  }
}
