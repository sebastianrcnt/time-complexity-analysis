import java.io.*;
import java.util.*;

class CountTriangles {

  static int findNumberOfTriangles(int arr[]) {

    int n = arr.length;

    Arrays.sort(arr);

    int count = 0;

    for (int i = 0; i < n - 2; ++i) {

      int k = i + 2;

      for (int j = i + 1; j < n; ++j) {

        while (k < n && arr[i] + arr[j] > arr[k]) ++k;

        if (k > j) count += k - j - 1;
      }
    }

    return count;
  }

  public static void main(String[] args) {

    int arr[] = {10, 21, 22, 100, 101, 200, 300};

    System.out.println("Total number of triangles is " + findNumberOfTriangles(arr));
  }
}
