import java.io.*;
import java.util.*;

class GFG {

  static int maxTripletSum(int arr[], int n) {

    Arrays.sort(arr);

    return arr[n - 1] + arr[n - 2] + arr[n - 3];
  }

  public static void main(String args[]) {

    int arr[] = {1, 0, 8, 6, 4, 2};

    int n = arr.length;

    System.out.println(maxTripletSum(arr, n));
  }
}
