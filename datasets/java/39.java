import java.io.*;
import java.util.*;

class Multipy {

  static void modify(int arr[], int n) {

    if (n <= 1) return;

    int prev = arr[0];

    arr[0] = arr[0] * arr[1];

    for (int i = 1; i < n - 1; i++) {

      int curr = arr[i];

      arr[i] = prev * arr[i + 1];

      prev = curr;
    }

    arr[n - 1] = prev * arr[n - 1];
  }

  public static void main(String[] args) {

    int arr[] = {2, 3, 4, 5, 6};

    int n = arr.length;

    modify(arr, n);

    for (int i = 0; i < n; i++) System.out.print(arr[i] + " ");
  }
}
