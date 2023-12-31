import static java.lang.Math.*;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main implements Runnable {
  static class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public InputReader(InputStream stream) {
      this.stream = stream;
    }

    public int read() {
      if (numChars == -1) throw new InputMismatchException();

      if (curChar >= numChars) {
        curChar = 0;
        try {
          numChars = stream.read(buf);
        } catch (IOException e) {
          throw new InputMismatchException();
        }

        if (numChars <= 0) return -1;
      }
      return buf[curChar++];
    }

    public String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }

    public int nextInt() {
      int c = read();

      while (isSpaceChar(c)) c = read();

      int sgn = 1;

      if (c == '-') {
        sgn = -1;
        c = read();
      }

      int res = 0;
      do {
        if (c < '0' || c > '9') throw new InputMismatchException();
        res *= 10;
        res += c - '0';
        c = read();
      } while (!isSpaceChar(c));

      return res * sgn;
    }

    public long nextLong() {
      int c = read();
      while (isSpaceChar(c)) c = read();
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      long res = 0;

      do {
        if (c < '0' || c > '9') throw new InputMismatchException();
        res *= 10;
        res += c - '0';
        c = read();
      } while (!isSpaceChar(c));
      return res * sgn;
    }

    public double nextDouble() {
      int c = read();
      while (isSpaceChar(c)) c = read();
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      double res = 0;
      while (!isSpaceChar(c) && c != '.') {
        if (c == 'e' || c == 'E') return res * Math.pow(10, nextInt());
        if (c < '0' || c > '9') throw new InputMismatchException();
        res *= 10;
        res += c - '0';
        c = read();
      }
      if (c == '.') {
        c = read();
        double m = 1;
        while (!isSpaceChar(c)) {
          if (c == 'e' || c == 'E') return res * Math.pow(10, nextInt());
          if (c < '0' || c > '9') throw new InputMismatchException();
          m /= 10;
          res += (c - '0') * m;
          c = read();
        }
      }
      return res * sgn;
    }

    public String readString() {
      int c = read();
      while (isSpaceChar(c)) c = read();
      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = read();
      } while (!isSpaceChar(c));

      return res.toString();
    }

    public boolean isSpaceChar(int c) {
      if (filter != null) return filter.isSpaceChar(c);
      return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public String next() {
      return readString();
    }

    public interface SpaceCharFilter {
      public boolean isSpaceChar(int ch);
    }
  }

  public static void main(String args[]) throws Exception {
    new Thread(null, new Main(), "Main", 1 << 26).start();
  }

  public void run() {
    InputReader sc = new InputReader(System.in);
    PrintWriter w = new PrintWriter(System.out);

    int n = sc.nextInt();
    long s = sc.nextLong();
    long arr[] = new long[n];
    int i = 0;
    for (i = 0; i < n; i++) {
      arr[i] = sc.nextLong();
    }
    Arrays.sort(arr);

    long count = 0;
    if (arr[n / 2] == s) {

      w.print(0);
    } else {
      int temp = n / 2;

      if (arr[temp] > s) {
        while (arr[temp] > s) {
          count = count + (arr[temp] - s);
          temp--;
          if (temp < 0) {
            break;
          }
        }
      } else {

        while (arr[temp] < s) {

          count = count + (s - arr[temp]);
          temp++;
          if (temp >= n) {
            break;
          }
        }
      }
      w.print(count);
    }

    w.close();
  }
}
