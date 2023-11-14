class Test {

  static int arr1[] = new int[] {0, 1, 0, 1, 1, 1, 1};

  static int arr2[] = new int[] {1, 1, 1, 1, 1, 0, 1};

  static int longestCommonSum(int n) {

    int maxLen = 0;

    for (int i = 0; i < n; i++) {

      int sum1 = 0, sum2 = 0;

      for (int j = i; j < n; j++) {

        sum1 += arr1[j];

        sum2 += arr2[j];

        if (sum1 == sum2) {

          int len = j - i + 1;

          if (len > maxLen) maxLen = len;
        }
      }
    }

    return maxLen;
  }

  public static void main(String[] args) {

    System.out.print("Length of the longest common span with same sum is ");

    System.out.println(longestCommonSum(arr1.length));
  }
}
