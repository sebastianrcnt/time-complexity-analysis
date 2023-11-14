import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

class GFG {

  static long calculate(long a[], int n) {

    Arrays.sort(a);

    int i, j;

    Vector<Long> s = new Vector<>();

    for (i = 0, j = n - 1; i < j; i++, j--) s.add((a[i] + a[j]));

    long mini = Collections.min(s);

    long maxi = Collections.max(s);

    return Math.abs(maxi - mini);
  }

  public static void main(String[] args) {

    long a[] = {2, 6, 4, 3};

    int n = a.length;

    System.out.println(calculate(a, n));
  }
}
