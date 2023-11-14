class GFG {

  public static int findFrequency(int arr[], int n, int left, int right, int element) {

    int count = 0;

    for (int i = left - 1; i < right; ++i) if (arr[i] == element) ++count;

    return count;
  }

  public static void main(String[] args) {

    int arr[] = {2, 8, 6, 9, 8, 6, 8, 2, 11};

    int n = arr.length;

    System.out.println("Frequency of 2 from 1 to 6 = " + findFrequency(arr, n, 1, 6, 2));

    System.out.println("Frequency of 8 from 4 to 9 = " + findFrequency(arr, n, 4, 9, 8));
  }
}
