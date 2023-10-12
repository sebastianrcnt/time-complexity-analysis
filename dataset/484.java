import java.io.*;
import java.util.Arrays;

class GFG {
  static int MinOperation(int a[], int b[], int n) {

    Arrays.sort(a);

    Arrays.sort(b);

    int result = 0;

    for (int i = 0; i < n; ++i) {

      if (a[i] > b[i]) result = result + Math.abs(a[i] - b[i]);
      else if (a[i] < b[i]) result = result + Math.abs(a[i] - b[i]);
    }

    return result;
  }

  public static void main(String[] args) {

    int a[] = {3, 1, 1};

    int b[] = {1, 2, 2};

    int n = a.length;

    System.out.println(MinOperation(a, b, n));
  }
}
