import java.io.*;

class GFG {

  static int MaxSumBS(int arr[], int n) {

    int max_sum = Integer.MIN_VALUE;

    int MSIBS[] = new int[n];

    int MSDBS[] = new int[n];

    for (int i = 0; i < n; i++) {

      MSDBS[i] = arr[i];

      MSIBS[i] = arr[i];
    }

    for (int i = 1; i < n; i++)
      for (int j = 0; j < i; j++)
        if (arr[i] > arr[j] && MSIBS[i] < MSIBS[j] + arr[i]) MSIBS[i] = MSIBS[j] + arr[i];

    for (int i = n - 2; i >= 0; i--)
      for (int j = n - 1; j > i; j--)
        if (arr[i] > arr[j] && MSDBS[i] < MSDBS[j] + arr[i]) MSDBS[i] = MSDBS[j] + arr[i];

    for (int i = 0; i < n; i++) max_sum = Math.max(max_sum, (MSDBS[i] + MSIBS[i] - arr[i]));

    return max_sum;
  }

  public static void main(String[] args) {

    int arr[] = {1, 15, 51, 45, 33, 100, 12, 18, 9};

    int n = arr.length;

    System.out.println("Maximum Sum : " + MaxSumBS(arr, n));
  }
}
