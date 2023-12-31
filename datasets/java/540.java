class GFG {

  int binarySearch(int arr[], int l, int r, int x) {

    if (r >= l) {

      int mid = l + (r - l) / 2;

      if (arr[mid] == x) return mid;

      if (mid > l && arr[mid - 1] == x) return (mid - 1);

      if (mid < r && arr[mid + 1] == x) return (mid + 1);

      if (arr[mid] > x) return binarySearch(arr, l, mid - 2, x);

      return binarySearch(arr, mid + 2, r, x);
    }

    return -1;
  }

  public static void main(String args[]) {

    GFG ob = new GFG();

    int arr[] = {3, 2, 10, 4, 40};

    int n = arr.length;

    int x = 4;

    int result = ob.binarySearch(arr, 0, n - 1, x);

    if (result == -1) System.out.println("Element is not present in array");
    else System.out.println("Element is present at index " + result);
  }
}
