import java.io.*;
import java.util.*;

public class Codechef {
  public static void main(String[] args) throws java.lang.Exception {

    Scanner in = new Scanner(System.in);
    long x = in.nextLong();
    long k = in.nextLong();

    long mod = 1000000007;
    long get = power(2, k, mod);
    long ans = ((get % mod) * ((2 * x) % mod)) % mod - get + 1;
    if (ans < 0) ans += mod;
    if (x == 0) ans = 0;
    System.out.println(ans);
  }

  static long power(long x, long y, long p) {

    long res = 1;

    x = x % p;

    while (y > 0) {

      if ((y & 1) == 1) res = (res * x) % p;

      y = y >> 1;
      x = (x * x) % p;
    }
    return res;
  }
}
