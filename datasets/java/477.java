import java.util.*;

class GFG {

  static int maxDifference(int arr[], int N, int k) {

    int M, S = 0, S1 = 0, max_difference = 0;

    for (int i = 0; i < N; i++) S += arr[i];

    int temp;

    for (int i = 0; i < N; i++) {

      for (int j = i + 1; j < N; j++) {

        if (arr[i] < arr[j]) {

          temp = arr[i];

          arr[i] = arr[j];

          arr[j] = temp;
        }
      }
    }

    M = Math.max(k, N - k);

    for (int i = 0; i < M; i++) S1 += arr[i];

    max_difference = S1 - (S - S1);

    return max_difference;
  }

  public static void main(String args[]) {

    int arr[] = {8, 4, 5, 2, 10};

    int N = arr.length;

    int k = 2;

    System.out.println(maxDifference(arr, N, k));
  }
}
