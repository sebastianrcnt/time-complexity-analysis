import java.io.*;

class GFG {

  static int count(int n) {

    if (n < 4) return -1;

    int rem = n % 4;

    if (rem == 0) return n / 4;

    if (rem == 1) {

      if (n < 9) return -1;

      return (n - 9) / 4 + 1;
    }

    if (rem == 2) return (n - 6) / 4 + 1;

    if (rem == 3) {

      if (n < 15) return -1;

      return (n - 15) / 4 + 2;
    }

    return 0;
  }

  public static void main(String[] args) {

    int n = 90;

    System.out.println(count(n));

    n = 143;

    System.out.println(count(n));
  }
}
