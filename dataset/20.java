import java.io.*;
import java.util.*;

class GFG {

  static int maxSum(int arr[], int n) {

    int sum = 0;

    int i;

    int pivot = findPivot(arr, n);

    int diff = n - 1 - pivot;

    for (i = 0; i < n; i++) {

      sum = sum + ((i + diff) % n) * arr[i];
    }

    return sum;
  }

  static int findPivot(int arr[], int n) {

    int i;

    for (i = 0; i < n; i++) {

      if (arr[i] > arr[(i + 1) % n]) return i;
    }

    return 0;
  }

  public static void main(String args[]) {

    int arr[] = {8, 3, 1, 2};

    int n = arr.length;

    int max = maxSum(arr, n);

    System.out.println(max);
  }
}
