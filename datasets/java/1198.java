import java.io.PrintWriter;
import java.lang.String.*;
import java.util.*;

public class Test {
  static int pos = 0;
  static int arr[];
  static LinkedList l1 = new LinkedList();

  static void find(int p, char[] x, int put[], String s) {
    int c = 0;
    for (int i = 0; i < s.length(); i++) {
      if (x[p] == s.charAt(i)) {
        c++;
      }
    }
    put[p] = c;
  }

  static int mode(int m, int[] x) {
    int temp = 0;
    for (int i = x.length - 1; i >= 0; i--) {
      if (x[i] <= m) {
        temp = x[i];

        return m - temp;
      }
    }
    return m - temp;
  }

  static int mode2(int m, int[] x) {
    int temp = 0;

    for (int i = x.length - 1; i >= 0; i--) {
      if (x[i] <= m) {
        temp = x[i];

        return x[i];
      }
    }
    return 0;
  }

  static int find(int x[], int temp) {
    int j = 0;
    for (int i = x.length - 1; i >= 0; i--) {
      if (x[i] == temp) return j + 1;
      j++;
    }
    return -1;
  }

  static String ch(long[] x, long b) {
    for (int i = 0; i < x.length; i++) {
      if (x[i] == b) return "YES";
    }
    return "NO";
  }

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    PrintWriter pw = new PrintWriter(System.out);
    long n = in.nextLong();
    long count = 1;
    long temp = n / 2;
    temp += count;

    System.out.println(temp);
  }
}
