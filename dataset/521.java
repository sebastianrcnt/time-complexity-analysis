import java.util.*;

class GfG {

  static int getMedian(int ar1[], int ar2[], int n) {

    if (n <= 0) return -1;

    if (n == 1) return (ar1[0] + ar2[0]) / 2;

    if (n == 2) return (Math.max(ar1[0], ar2[0]) + Math.min(ar1[1], ar2[1])) / 2;

    int m1 = median(ar1, n);

    int m2 = median(ar2, n);

    if (m1 == m2) return m1;

    if (m1 < m2) {

      if (n % 2 == 0) return getMedian(ar1 + n / 2 - 1, ar2, n - n / 2 + 1);

      return getMedian(ar1 + n / 2, ar2, n - n / 2);
    }

    if (n % 2 == 0) return getMedian(ar2 + n / 2 - 1, ar1, n - n / 2 + 1);

    return getMedian(ar2 + n / 2, ar1, n - n / 2);
  }

  static int median(int arr[], int n) {

    if (n % 2 == 0) return (arr[n / 2] + arr[n / 2 - 1]) / 2;
    else return arr[n / 2];
  }

  public static void main(String[] args) {

    int ar1[] = {1, 2, 3, 6};

    int ar2[] = {4, 6, 8, 10};

    int n1 = ar1.length;

    int n2 = ar2.length;

    if (n1 == n2) System.out.println("Median is " + getMedian(ar1, ar2, n1));
    else System.out.println("Doesn't work for arrays " + "of unequal size");
  }
}
