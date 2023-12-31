class RearrangeArray {

  void rearrangeUtil(int arr[], int n, int i) {

    int val = -(i + 1);

    i = arr[i] - 1;

    while (arr[i] > 0) {

      int new_i = arr[i] - 1;

      arr[i] = val;

      val = -(i + 1);

      i = new_i;
    }
  }

  void rearrange(int arr[], int n) {

    int i;

    for (i = 0; i < n; i++) arr[i]++;

    for (i = 0; i < n; i++) {

      if (arr[i] > 0) rearrangeUtil(arr, n, i);
    }

    for (i = 0; i < n; i++) arr[i] = (-arr[i]) - 1;
  }

  void printArray(int arr[], int n) {

    int i;

    for (i = 0; i < n; i++) System.out.print(arr[i] + " ");

    System.out.println("");
  }

  public static void main(String[] args) {

    RearrangeArray arrange = new RearrangeArray();

    int arr[] = {2, 0, 1, 4, 5, 3};

    int n = arr.length;

    System.out.println("Given array is ");

    arrange.printArray(arr, n);

    arrange.rearrange(arr, n);

    System.out.println("Modified array is ");

    arrange.printArray(arr, n);
  }
}
