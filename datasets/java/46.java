import java.io.*;
import java.util.Arrays;

class GFG {

  void find3largest(int[] arr) {

    Arrays.sort(arr);

    int n = arr.length;

    int check = 0, count = 1;

    for (int i = 1; i <= n; i++) {

      if (count < 4) {

        if (check != arr[n - i]) {

          System.out.print(arr[n - i] + " ");

          check = arr[n - i];

          count++;
        }

      } else break;
    }
  }

  public static void main(String[] args) {

    GFG obj = new GFG();

    int[] arr = {12, 45, 1, -1, 45, 54, 23, 5, 0, -10};

    obj.find3largest(arr);
  }
}
