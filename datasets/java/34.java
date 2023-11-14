class GFG {

  static void rearrange(int arr[], int n) {

    for (int i = 0; i < n; i++) {

      arr[arr[i] % n] += i * n;
    }

    for (int i = 0; i < n; i++) {

      arr[i] /= n;
    }
  }

  static void printArray(int arr[], int n) {

    for (int i = 0; i < n; i++) {

      System.out.print(arr[i] + " ");
    }

    System.out.println();
  }

  public static void main(String[] args) {

    int arr[] = {2, 0, 1, 4, 5, 3};

    int n = arr.length;

    System.out.println("Given array is : ");

    printArray(arr, n);

    rearrange(arr, n);

    System.out.println("Modified array is :");

    printArray(arr, n);
  }
}
