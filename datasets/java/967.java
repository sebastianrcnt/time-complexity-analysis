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
    int l;
    int r;

    public card(int ch, int i) {
      this.l = ch;
      this.r = i;
    }
  }

  static class sort implements Comparator<card> {
    public int compare(card o1, card o2) {
      if (o1.l != o2.l) return (int) (o1.l - o2.l);
      else return (int) (o1.r - o2.r);
    }
  }

  static void shuffle(long a[]) {
    List<Long> l = new ArrayList<>();
    for (int i = 0; i < a.length; i++) l.add(a[i]);
    Collections.shuffle(l);
    for (int i = 0; i < a.length; i++) a[i] = l.get(i);
  }

  static boolean valid(int i, int j, int n, int m) {
    if (i < n && i >= 0 && j < m && j >= 0) return true;
    else return false;
  }

  public static void main(String[] args) {
    InputReader sc = new InputReader(System.in);
    int n = sc.nextInt();
    int s = sc.nextInt();
    card c[] = new card[n];
    for (int i = 0; i < n; i++) {
      int x = sc.nextInt();
      int y = sc.nextInt();
      c[i] = new card(x, y);
    }
    Arrays.sort(c, new sort());
    int time = 0;
    for (int i = n - 1; i >= 0; i--) {
      time += s - c[i].l;
      if ((c[i].r - time) > 0) time += c[i].r - time;
      s = c[i].l;
    }
    if (c[0].l != 0) time += c[0].l;
    System.out.println(time);
  }
}
