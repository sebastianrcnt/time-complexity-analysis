class Main {

  static int count(int arr[], int x, int n) {

    int i;

    int j;

    i = first(arr, 0, n - 1, x, n);

    if (i == -1) return i;

    j = last(arr, i, n - 1, x, n);

    return j - i + 1;
  }

  static int first(int arr[], int low, int high, int x, int n) {

    if (high >= low) {

      int mid = (low + high) / 2;

      if ((mid == 0 || x > arr[mid - 1]) && arr[mid] == x) return mid;
      else if (x > arr[mid]) return first(arr, (mid + 1), high, x, n);
      else return first(arr, low, (mid - 1), x, n);
    }

    return -1;
  }

  static int last(int arr[], int low, int high, int x, int n) {

    if (high >= low) {

      int mid = (low + high) / 2;

      if ((mid == n - 1 || x < arr[mid + 1]) && arr[mid] == x) return mid;
      else if (x < arr[mid]) return last(arr, low, (mid - 1), x, n);
      else return last(arr, (mid + 1), high, x, n);
    }

    return -1;
  }

  public static void main(String args[]) {

    int arr[] = {1, 2, 2, 3, 3, 3, 3};

    int x = 3;

    int n = arr.length;

    int c = count(arr, x, n);

    System.out.println(x + " occurs " + c + " times");
  }
}
