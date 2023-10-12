class GFG {

  static int findMinDiff(int[] arr, int n) {

    int diff = Integer.MAX_VALUE;

    for (int i = 0; i < n - 1; i++)
      for (int j = i + 1; j < n; j++)
        if (Math.abs((arr[i] - arr[j])) < diff) diff = Math.abs((arr[i] - arr[j]));

    return diff;
  }

  public static void main(String[] args) {

    int arr[] = new int[] {1, 5, 3, 19, 18, 25};

    System.out.println("Minimum difference is " + findMinDiff(arr, arr.length));
  }
}
