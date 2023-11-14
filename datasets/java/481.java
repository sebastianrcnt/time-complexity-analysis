import java.io.*;

class GFG {
  static int MinOperation(int a[], int n, int k) {

    int result = 0;

    for (int i = 0; i < n; ++i) {

      if (a[i] != 1 && a[i] > k) {

        result = result + Math.min(a[i] % k, k - a[i] % k);

      } else {

        result = result + k - a[i];
      }
    }

    return result;
  }

  public static void main(String[] args) {

    int arr[] = {4, 5, 6};

    int n = arr.length;

    int k = 5;

    System.out.println(MinOperation(arr, n, k));
  }
}
