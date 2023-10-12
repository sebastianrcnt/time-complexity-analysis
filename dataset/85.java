class Test {

  static int arr1[] = new int[] {0, 1, 0, 1, 1, 1, 1};

  static int arr2[] = new int[] {1, 1, 1, 1, 1, 0, 1};

  static int longestCommonSum(int n) {

    int maxLen = 0;

    int preSum1 = 0, preSum2 = 0;

    int diff[] = new int[2 * n + 1];

    for (int i = 0; i < diff.length; i++) {

      diff[i] = -1;
    }

    for (int i = 0; i < n; i++) {

      preSum1 += arr1[i];

      preSum2 += arr2[i];

      int curr_diff = preSum1 - preSum2;

      int diffIndex = n + curr_diff;

      if (curr_diff == 0) maxLen = i + 1;
      else if (diff[diffIndex] == -1) diff[diffIndex] = i;
      else {

        int len = i - diff[diffIndex];

        if (len > maxLen) maxLen = len;
      }
    }

    return maxLen;
  }

  public static void main(String[] args) {

    System.out.print("Length of the longest common span with same sum is ");

    System.out.println(longestCommonSum(arr1.length));
  }
}
