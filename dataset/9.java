class Geeks {

  static void rvereseArray(int arr[], int start, int end) {

    while (start < end) {

      int temp = arr[start];

      arr[start] = arr[end];

      arr[end] = temp;

      start++;

      end--;
    }
  }

  static void printArray(int arr[], int size) {

    for (int i = 0; i < size; i++) System.out.print(arr[i] + " ");
  }

  static void splitArr(int arr[], int k, int n) {

    rvereseArray(arr, 0, n - 1);

    rvereseArray(arr, 0, n - k - 1);

    rvereseArray(arr, n - k, n - 1);
  }

  public static void main(String args[]) {

    int arr[] = {12, 10, 5, 6, 52, 36};

    int n = arr.length;

    int k = 2;

    splitArr(arr, k, n);

    printArray(arr, n);
  }
}
