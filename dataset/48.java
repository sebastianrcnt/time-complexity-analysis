import java.io.*;
import java.util.*;

class GFG {

  static void findElements(int arr[], int n) {

    Arrays.sort(arr);

    for (int i = 0; i < n - 2; i++) System.out.print(arr[i] + " ");
  }

  public static void main(String args[]) {

    int arr[] = {2, -6, 3, 5, 1};

    int n = arr.length;

    findElements(arr, n);
  }
}
