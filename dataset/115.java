import java.io.*;

class GFG {

  static boolean isPresent(int B[], int m, int x) {

    for (int i = 0; i < m; i++) if (B[i] == x) return true;

    return false;
  }

  static int findMaxSubarraySumUtil(int A[], int B[], int n, int m) {

    int max_so_far = -2147483648, curr_max = 0;

    for (int i = 0; i < n; i++) {

      if (isPresent(B, m, A[i])) {

        curr_max = 0;

        continue;
      }

      curr_max = Math.max(A[i], curr_max + A[i]);

      max_so_far = Math.max(max_so_far, curr_max);
    }

    return max_so_far;
  }

  static void findMaxSubarraySum(int A[], int B[], int n, int m) {

    int maxSubarraySum = findMaxSubarraySumUtil(A, B, n, m);

    if (maxSubarraySum == -2147483648) {

      System.out.println("Maximum Subarray Sum" + " " + "can't be found");

    } else {

      System.out.println("The Maximum Subarray Sum = " + maxSubarraySum);
    }
  }

  public static void main(String[] args) {

    int A[] = {3, 4, 5, -4, 6};

    int B[] = {1, 8, 5};

    int n = A.length;

    int m = B.length;

    findMaxSubarraySum(A, B, n, m);
  }
}
