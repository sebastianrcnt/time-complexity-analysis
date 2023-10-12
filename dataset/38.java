class GFG {

  static void rearrange(int arr[], int n) {

    int temp;

    for (int i = 0; i < n - 1; i++) {

      if (i % 2 == 0 && arr[i] > arr[i + 1]) {

        temp = arr[i];

        arr[i] = arr[i + 1];

        arr[i + 1] = temp;
      }

      if (i % 2 != 0 && arr[i] < arr[i + 1]) {

        temp = arr[i];

        arr[i] = arr[i + 1];

        arr[i + 1] = temp;
      }
    }
  }

  static void printArray(int arr[], int size) {

    for (int i = 0; i < size; i++) System.out.print(arr[i] + " ");

    System.out.println();
  }

  public static void main(String[] args) {

    int arr[] = {6, 4, 2, 1, 8, 3};

    int n = arr.length;

    System.out.print("Before rearranging: \n");

    printArray(arr, n);

    rearrange(arr, n);

    System.out.print("After rearranging: \n");

    printArray(arr, n);
  }
}
