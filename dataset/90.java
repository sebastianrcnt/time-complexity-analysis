import java.io.*;
import java.util.*;

public class GFG {

  static void sortit(int[] arr, int n) {

    for (int i = 0; i < n; i++) {

      arr[i] = i + 1;
    }
  }

  public static void main(String args[]) {

    int[] arr = {10, 7, 9, 2, 8, 3, 5, 4, 6, 1};

    int n = arr.length;

    sortit(arr, n);

    for (int i = 0; i < n; i++) System.out.print(arr[i] + " ");
  }
}
