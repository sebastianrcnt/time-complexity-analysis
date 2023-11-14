import java.io.*;

class GFG {

  static int offeringNumber(int n, int templeHeight[]) {

    int sum = 0;

    for (int i = 0; i < n; ++i) {

      int left = 0, right = 0;

      for (int j = i - 1; j >= 0; --j) {

        if (templeHeight[j] < templeHeight[j + 1]) ++left;
        else break;
      }

      for (int j = i + 1; j < n; ++j) {

        if (templeHeight[j] < templeHeight[j - 1]) ++right;
        else break;
      }

      sum += Math.max(right, left) + 1;
    }

    return sum;
  }

  public static void main(String[] args) {
    int arr1[] = {1, 2, 2};
    System.out.println(offeringNumber(3, arr1));
    int arr2[] = {1, 4, 3, 6, 2, 1};

    System.out.println(offeringNumber(6, arr2));
  }
}
