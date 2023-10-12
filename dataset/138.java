import java.util.Arrays;

class GFG {

  static int maximum(int a, int b, int c) {

    return Math.max(Math.max(a, b), c);
  }

  static int minimum(int a, int b, int c) {

    return Math.min(Math.min(a, b), c);
  }

  static void smallestDifferenceTriplet(int arr1[], int arr2[], int arr3[], int n) {

    Arrays.sort(arr1);

    Arrays.sort(arr2);

    Arrays.sort(arr3);

    int res_min = 0, res_max = 0, res_mid = 0;

    int i = 0, j = 0, k = 0;

    int diff = 2147483647;

    while (i < n && j < n && k < n) {

      int sum = arr1[i] + arr2[j] + arr3[k];

      int max = maximum(arr1[i], arr2[j], arr3[k]);

      int min = minimum(arr1[i], arr2[j], arr3[k]);

      if (min == arr1[i]) i++;
      else if (min == arr2[j]) j++;
      else k++;

      if (diff > (max - min)) {

        diff = max - min;

        res_max = max;

        res_mid = sum - (max + min);

        res_min = min;
      }
    }

    System.out.print(res_max + ", " + res_mid + ", " + res_min);
  }

  public static void main(String[] args) {

    int arr1[] = {5, 2, 8};

    int arr2[] = {10, 7, 12};

    int arr3[] = {9, 14, 6};

    int n = arr1.length;

    smallestDifferenceTriplet(arr1, arr2, arr3, n);
  }
}
