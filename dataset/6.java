class Test {

  static int arr[] = new int[] {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};

  static int maxSum() {

    int arrSum = 0;

    int currVal = 0;

    for (int i = 0; i < arr.length; i++) {

      arrSum = arrSum + arr[i];

      currVal = currVal + (i * arr[i]);
    }

    int maxVal = currVal;

    for (int j = 1; j < arr.length; j++) {

      currVal = currVal + arrSum - arr.length * arr[arr.length - j];

      if (currVal > maxVal) maxVal = currVal;
    }

    return maxVal;
  }

  public static void main(String[] args) {

    System.out.println("Max sum is " + maxSum());
  }
}
