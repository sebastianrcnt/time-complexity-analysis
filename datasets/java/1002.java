import java.io.*;
import java.math.*;
import java.util.*;

public class round569d2b {

  public static void main(String args[]) {
    FastScanner in = new FastScanner(System.in);

    int n = in.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = in.nextInt();
    }
    if (n % 2 == 0) {
      for (int i = 0; i < n; i++) {
        if (arr[i] >= 0) {
          arr[i] = -1 * arr[i] - 1;
        }
      }
    } else {
      int max = Integer.MIN_VALUE;
      int maxIndex = 0;
      for (int i = 0; i < n; i++) {
        int elem = arr[i];
        if (elem < 0) {
          elem = -1 * elem - 1;
        }
        if (elem > max) {
          max = elem;
          maxIndex = i;
        }
      }
      for (int i = 0; i < n; i++) {
        if (i == maxIndex) {
          if (arr[i] < 0) {
            arr[i] = -1 * arr[i] - 1;
          }
        } else {
          if (arr[i] >= 0) {
            arr[i] = -1 * arr[i] - 1;
          }
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append(arr[i] + " ");
    }
    System.out.println(sb);
  }

  static int greatestDivisor(int n) {
    int limit = (int) Math.sqrt(n);
    int max = 1;
    for (int i = 2; i <= limit; i++) {
      if (n % i == 0) {
        max = Integer.max(max, i);
        max = Integer.max(max, n / i);
      }
    }
    return max;
  }

  static boolean[] sieveOfEratosthenes(int n) {

    boolean prime[] = new boolean[n + 1];
    for (int i = 0; i <= n; i++) prime[i] = true;

    prime[0] = false;
    prime[1] = false;

    for (int p = 2; p * p <= n; p++) {

      if (prime[p] == true) {

        for (int i = p * p; i <= n; i += p) prime[i] = false;
      }
    }

    return prime;
  }

  private static int bin_gteq(int[] a, int key) {
    int low = 0;
    int high = a.length;
    int max_limit = high;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (a[mid] < key) {
        low = mid + 1;
      } else high = mid;
    }

    return high == max_limit ? -1 : high;
  }

  public static int gcd(int a, int b) {
    if (a == 0) return b;

    return gcd(b % a, a);
  }

  static class Tuple<X, Y> {
    public final X x;
    public final Y y;

    public Tuple(X x, Y y) {
      this.x = x;
      this.y = y;
    }

    public String toString() {
      return "(" + x + "," + y + ")";
    }
  }

  static class Tuple3<X, Y, Z> {
    public final X x;
    public final Y y;
    public final Z z;

    public Tuple3(X x, Y y, Z z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public String toString() {
      return "(" + x + "," + y + "," + z + ")";
    }
  }

  static Tuple3<Integer, Integer, Integer> gcdExtended(int a, int b, int x, int y) {

    if (a == 0) {
      x = 0;
      y = 1;
      return new Tuple3(0, 1, b);
    }

    int x1 = 1, y1 = 1;
    Tuple3<Integer, Integer, Integer> tuple = gcdExtended(b % a, a, x1, y1);
    int gcd = tuple.z;
    x1 = tuple.x;
    y1 = tuple.y;

    x = y1 - (b / a) * x1;
    y = x1;

    return new Tuple3(x, y, gcd);
  }

  static int inv(int a, int m) {
    int m0 = m, t, q;
    int x0 = 0, x1 = 1;

    if (m == 1) return 0;

    while (a > 1) {

      q = a / m;

      t = m;

      m = a % m;
      a = t;

      t = x0;

      x0 = x1 - q * x0;

      x1 = t;
    }

    if (x1 < 0) x1 += m0;

    return x1;
  }

  static int findMinX(int num[], int rem[], int k) {

    int prod = 1;
    for (int i = 0; i < k; i++) prod *= num[i];

    int result = 0;

    for (int i = 0; i < k; i++) {
      int pp = prod / num[i];
      result += rem[i] * inv(pp, num[i]) * pp;
    }

    return result % prod;
  }

  static class FastScanner {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int chars;

    public FastScanner(InputStream stream) {
      this.stream = stream;
    }

    int read() {
      if (chars == -1) throw new InputMismatchException();
      if (curChar >= chars) {
        curChar = 0;
        try {
          chars = stream.read(buf);
        } catch (IOException e) {
          throw new InputMismatchException();
        }
        if (chars <= 0) return -1;
      }
      return buf[curChar++];
    }

    boolean isSpaceChar(int c) {
      return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    boolean isEndline(int c) {
      return c == '\n' || c == '\r' || c == -1;
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }

    public long nextLong() {
      return Long.parseLong(next());
    }

    public double nextDouble() {
      return Double.parseDouble(next());
    }

    public String next() {
      int c = read();
      while (isSpaceChar(c)) c = read();
      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = read();
      } while (!isSpaceChar(c));
      return res.toString();
    }

    public String nextLine() {
      int c = read();
      while (isEndline(c)) c = read();
      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = read();
      } while (!isEndline(c));
      return res.toString();
    }
  }
}
