import java.util.*;

class GFG {

  static int findMaxSubarraySumUtil(int A[], int B[], int n, int m) {

    int max_so_far = Integer.MIN_VALUE, curr_max = 0;

    for (int i = 0; i < n; i++) {

      if (Arrays.binarySearch(B, A[i]) >= 0) {

        curr_max = 0;

        continue;
      }

      curr_max = Math.max(A[i], curr_max + A[i]);

      max_so_far = Math.max(max_so_far, curr_max);
    }

    return max_so_far;
  }

  static void findMaxSubarraySum(int A[], int B[], int n, int m) {

    Arrays.sort(B);

    int maxSubarraySum = findMaxSubarraySumUtil(A, B, n, m);

    if (maxSubarraySum == Integer.MIN_VALUE) {

      System.out.println("Maximum subarray sum cant be found");

    } else {

      System.out.println("The Maximum subarray sum = " + maxSubarraySum);
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
