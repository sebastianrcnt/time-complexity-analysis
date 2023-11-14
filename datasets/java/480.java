import java.util.Arrays;

class MinSum {

  static long findMinSum(long a[], long b[], long n) {

    Arrays.sort(a);

    Arrays.sort(b);

    long sum = 0;

    for (int i = 0; i < n; i++) sum = sum + Math.abs(a[i] - b[i]);

    return sum;
  }

  public static void main(String[] args) {

    long a[] = {4, 1, 8, 7};

    long b[] = {2, 3, 6, 5};

    int n = a.length;

    System.out.println(findMinSum(a, b, n));
  }
}
