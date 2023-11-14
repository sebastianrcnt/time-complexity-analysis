class Test {

  static int arr[] = new int[] {12, 34, 10, 6, 40};

  static int findLargestSumPair() {

    int first, second;

    if (arr[0] > arr[1]) {

      first = arr[0];

      second = arr[1];

    } else {

      first = arr[1];

      second = arr[0];
    }

    for (int i = 2; i < arr.length; i++) {

      if (arr[i] > first) {

        second = first;

        first = arr[i];

      } else if (arr[i] > second && arr[i] != first) second = arr[i];
    }

    return (first + second);
  }

  public static void main(String[] args) {

    System.out.println("Max Pair Sum is " + findLargestSumPair());
  }
}
