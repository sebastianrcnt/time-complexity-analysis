package com.interview.algorithms.array;

public class FloorAndCeilingInASortedArray {

  private static int ceilSearch(int arr[], int low, int high, int x) {
    int mid;

    if (x <= arr[low]) return low;

    if (x > arr[high]) return -1;

    mid = (low + high) / 2;

    if (arr[mid] == x) return mid;
    else if (x > arr[mid]) {
      if (mid + 1 <= high && x <= arr[mid + 1]) return mid + 1;
      else return ceilSearch(arr, mid + 1, high, x);
    } else {
      if (mid - 1 >= low && x > arr[mid - 1]) return mid;
      else return ceilSearch(arr, low, mid - 1, x);
    }
  }

  public static void main(String[] args) {
    int arr[] = {1, 2, 8, 10, 10, 12, 19};
    int n = arr.length;
    int x = 11;
    int index = ceilSearch(arr, 0, n - 1, x);
    if (index == -1) System.out.println("Ceiling of doesn't exist in array " + x);
    else System.out.println("ceiling of" + x + " is " + arr[index]);
  }
}
