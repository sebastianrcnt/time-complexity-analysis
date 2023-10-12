import java.io.*;
import java.math.*;
import java.util.*;

public class loser {
  static class InputReader {
    public BufferedReader br;
    public StringTokenizer token;

    public InputReader(InputStream stream) {
      br = new BufferedReader(new InputStreamReader(stream), 32768);
      token = null;
    }

    public String next() {
      while (token == null || !token.hasMoreTokens()) {
        try {
          token = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return token.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }

    public long nextLong() {
      return Long.parseLong(next());
    }
  }

  static class card {
    long a;
    int i;

    public card(long a, int i) {
      this.a = a;
      this.i = i;
    }
  }

  static class sort implements Comparator<pair> {
    public int compare(pair o1, pair o2) {
      if (o1.a != o2.a) return (int) (o1.a - o2.a);
      else return (int) (o1.b - o2.b);
    }
  }

  static void shuffle(long a[]) {
    List<Long> l = new ArrayList<>();
    for (int i = 0; i < a.length; i++) l.add(a[i]);
    Collections.shuffle(l);
    for (int i = 0; i < a.length; i++) a[i] = l.get(i);
  }

  static class pair {
    int a, b;

    public pair(int a, int b) {
      this.a = a;
      this.b = b;
    }
  }

  public static void main(String[] args) {
    InputReader sc = new InputReader(System.in);
    char c[] = sc.next().toCharArray();
    int l = c.length;
    int a[] = new int[3];
    for (int i = 0; i < l; i++) {
      a[c[i] - 'a']++;
      if (i > 0 && c[i] < c[i - 1]) {
        System.out.println("NO");
        System.exit(0);
      }
    }
    if (a[0] > 0 && a[1] > 0 && (a[2] == a[1] || a[2] == a[0])) System.out.println("YES");
    else System.out.println("NO");
  }
}
