class GFG {

  static int maxSubarrayXOR(int arr[], int n) {

    int ans = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {

      int curr_xor = 0;

      for (int j = i; j < n; j++) {

        curr_xor = curr_xor ^ arr[j];

        ans = Math.max(ans, curr_xor);
      }
    }

    return ans;
  }

  public static void main(String args[]) {

    int arr[] = {8, 1, 2, 12};

    int n = arr.length;

    System.out.println("Max subarray XOR is " + maxSubarrayXOR(arr, n));
  }
}
