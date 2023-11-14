class GFG {

  static void printArray(int A[], int size) {

    for (int i = 0; i < size; i++) System.out.print(A[i] + " ");

    System.out.println("");

    ;
  }

  static void reverse(int arr[], int l, int r) {

    if (l < r) {

      arr = swap(arr, l, r);

      reverse(arr, ++l, --r);
    }
  }

  static void merge(int arr[], int l, int m, int r) {

    int i = l;

    int j = m + 1;

    while (i <= m && arr[i] < 0) i++;

    while (j <= r && arr[j] < 0) j++;

    reverse(arr, i, m);

    reverse(arr, m + 1, j - 1);

    reverse(arr, i, j - 1);
  }

  static void RearrangePosNeg(int arr[], int l, int r) {

    if (l < r) {

      int m = l + (r - l) / 2;

      RearrangePosNeg(arr, l, m);

      RearrangePosNeg(arr, m + 1, r);

      merge(arr, l, m, r);
    }
  }

  static int[] swap(int[] arr, int i, int j) {

    int temp = arr[i];

    arr[i] = arr[j];

    arr[j] = temp;

    return arr;
  }

  public static void main(String[] args) {

    int arr[] = {-12, 11, -13, -5, 6, -7, 5, -3, -6};

    int arr_size = arr.length;

    RearrangePosNeg(arr, 0, arr_size - 1);

    printArray(arr, arr_size);
  }
}
