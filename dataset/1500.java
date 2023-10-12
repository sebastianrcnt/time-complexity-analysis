package com.rampatra.searching;

public class BinarySearch {

  private static int binarySearch(int[] a, int n) {
    return binarySearch(a, n, 0, a.length - 1);
  }

  public static int binarySearch(int[] a, int n, int low, int high) {

    if (low <= high) {
      int mid = (low + high) / 2;

      if (n == a[mid]) {
        return mid;
      } else if (n < a[mid]) {
        return binarySearch(a, n, 0, mid - 1);
      } else {
        return binarySearch(a, n, mid + 1, high);
      }
    } else {
      return -1;
    }
  }

  private static int binarySearchNonRecursive(int[] a, int n) {
    int low = 0, high = a.length, mid;
    while (low <= high) {
      mid = (low + high) / 2;
      if (n == a[mid]) {
        return mid;
      } else if (n < a[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(binarySearch(new int[] {0, 2}, 2));
    System.out.println(binarySearch(new int[] {0, 1, 2, 3}, 2));
    System.out.println(binarySearch(new int[] {0, 1, 2, 3}, 3));
    System.out.println(binarySearch(new int[] {0, 2}, 0));
    System.out.println(binarySearch(new int[] {0, 1, 2, 2, 2, 3, 3}, 2));
    System.out.println("---------");
    System.out.println(binarySearchNonRecursive(new int[] {0, 2}, 2));
    System.out.println(binarySearchNonRecursive(new int[] {0, 1, 2, 3}, 2));
    System.out.println(binarySearchNonRecursive(new int[] {0, 1, 2, 3}, 3));
    System.out.println(binarySearchNonRecursive(new int[] {0, 2}, 0));
    System.out.println(binarySearchNonRecursive(new int[] {0, 1, 2, 2, 2, 3, 3}, 2));
  }
}
