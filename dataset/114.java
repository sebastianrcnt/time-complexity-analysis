class GFG {

  static int findRepeating(int arr[], int n) {

    int res = 0;

    for (int i = 0; i < n - 1; i++) res = res ^ (i + 1) ^ arr[i];

    res = res ^ arr[n - 1];

    return res;
  }

  public static void main(String[] args) {

    int arr[] = {9, 8, 2, 6, 1, 8, 5, 3, 4, 7};

    int n = arr.length;

    System.out.println(findRepeating(arr, n));
  }
}
