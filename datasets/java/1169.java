import java.util.*;

public class Main {
  static long m = 1000000007;

  static long powmod(long x, long y, long p) {

    long res = 1;

    x = x % p;

    while (y > 0) {

      if ((y & 1) == 1) res = (res * x) % p;

      y = y >> 1;
      x = (x * x) % p;
    }
    return res;
  }

  static long mulmod(long a, long b, long mod) {
    long res = 0;
    a = a % mod;
    while (b > 0) {

      if (b % 2 == 1) res = (res + a) % mod;

      a = (a * 2) % mod;

      b /= 2;
    }

    return res % mod;
  }

  public static void main(String args[]) throws Exception {
    Scanner sc = new Scanner(System.in);
    long x = sc.nextLong();
    long k = sc.nextLong();
    if (x > 0) {
      long d = powmod(2, k, m);
      long ans = mulmod(d, 2, m) % m;
      ans = mulmod(ans, x, m) % m;
      ans++;
      ans %= m;
      ans = (ans - d + m) % m;
      System.out.println(ans);
    } else System.out.println(0);
  }
}
