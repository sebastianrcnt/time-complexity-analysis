class GFG {

  static int maximumSum(int arr[], int n, int k) {

    for (int i = 1; i <= k; i++) {

      int min = +2147483647;

      int index = -1;

      for (int j = 0; j < n; j++) {

        if (arr[j] < min) {

          min = arr[j];

          index = j;
        }
      }

      if (min == 0) break;

      arr[index] = -arr[index];
    }

    int sum = 0;

    for (int i = 0; i < n; i++) sum += arr[i];

    return sum;
  }

  public static void main(String arg[]) {

    int arr[] = {-2, 0, 5, -1, 2};

    int k = 4;

    int n = arr.length;

    System.out.print(maximumSum(arr, n, k));
  }
}
