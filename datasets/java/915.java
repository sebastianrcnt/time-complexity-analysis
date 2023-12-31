import java.io.*;
import java.util.*;

public class D {

  static final boolean stdin = true;
  static final String filename = "";
  static FastScanner br;
  static PrintWriter pw;

  public static void main(String[] args) throws IOException {

    if (stdin) {
      br = new FastScanner();
      pw = new PrintWriter(new OutputStreamWriter(System.out));
    } else {
      br = new FastScanner(filename + ".in");
      pw = new PrintWriter(new FileWriter(filename + ".out"));
    }

    Solver solver = new Solver();
    solver.solve(br, pw);
  }

  static class Solver {
    static long mod = (long) (1e10);

    public void solve(FastScanner br, PrintWriter pw) throws IOException {
      int n = br.ni();
      Integer[] in = br.nIa(n);
      TreeSet<Integer> ts = new TreeSet<Integer>();
      for (int i = 0; i < n; i++) {
        ts.add(in[i]);
      }
      String twoSol = "";
      for (int i = 0; i <= 30; i++) {
        for (int j : in) {
          if (ts.contains(j + (int) Math.pow(2, i))) {
            if (ts.contains(j - (int) Math.pow(2, i))) {
              pw.println(3);
              pw.println(j + " " + (j + (int) Math.pow(2, i)) + " " + (j - (int) Math.pow(2, i)));
              pw.close();
              System.exit(0);
            } else {
              twoSol = (j + " " + (j + (int) Math.pow(2, i)));
            }
          }
        }
      }
      if (twoSol.isEmpty()) {
        pw.println(1);
        pw.println(in[0]);
      } else {
        pw.println(2);
        pw.println(twoSol);
      }
      pw.close();
    }

    static long gcd(long a, long b) {
      if (a > b) return gcd(b, a);
      if (a == 0) return b;
      return gcd(b % a, a);
    }

    static long lcm(long a, long b) {
      return a * (b / gcd(a, b));
    }

    static long pow(long a, long b) {
      if (b == 0) return 1L;
      long val = pow(a, b / 2);
      if (b % 2 == 0) return val * val % mod;
      else return val * val % mod * a % mod;
    }
  }

  static class Point implements Comparable<Point> {
    int a;
    int b;

    Point(int a, int b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public int compareTo(Point o) {
      return this.a - o.a;
    }
  }

  public static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner(String s) {
      try {
        br = new BufferedReader(new FileReader(s));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    public FastScanner() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    ArrayList<Integer>[] ng(int n, int e) {
      ArrayList<Integer>[] adj = new ArrayList[n];
      for (int i = 0; i < n; i++) {
        adj[i] = new ArrayList<Integer>();
      }
      for (int i = 0; i < e; i++) {
        int a = ni() - 1;
        int b = ni() - 1;
        adj[a].add(b);
        adj[b].add(a);
      }
      return adj;
    }

    Integer[] nIa(int n) {
      Integer[] arr = new Integer[n];
      for (int i = 0; i < n; i++) {
        arr[i] = ni();
      }
      return arr;
    }

    int[] nia(int n) {
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = ni();
      }
      return arr;
    }

    Long[] nLa(int n) {
      Long[] arr = new Long[n];
      for (int i = 0; i < n; i++) {
        arr[i] = nl();
      }
      return arr;
    }

    long[] nla(int n) {
      long[] arr = new long[n];
      for (int i = 0; i < n; i++) {
        arr[i] = nl();
      }
      return arr;
    }

    String[] nsa(int n) {
      String[] arr = new String[n];
      for (int i = 0; i < n; i++) {
        arr[i] = nt();
      }
      return arr;
    }

    String nt() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int ni() {
      return Integer.parseInt(nt());
    }

    long nl() {
      return Long.parseLong(nt());
    }

    double nd() {
      return Double.parseDouble(nt());
    }
  }
}
