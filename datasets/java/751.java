import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {

  static int N;
  static int[] U, V;
  static int[] A;

  public static void main(String[] args) {
    FastScanner sc = new FastScanner(System.in);
    N = sc.nextInt();
    U = new int[N - 1];
    V = new int[N - 1];
    for (int i = 0; i < N - 1; i++) {
      U[i] = sc.nextInt() - 1;
      V[i] = sc.nextInt() - 1;
    }
    A = sc.nextIntArray(N, -1);

    System.out.println(solve() ? "Yes" : "No");
  }

  static boolean solve() {
    if (A[0] != 0) return false;

    int[][] G = adjB(N, U, V);

    Map<Integer, Integer> parents = new HashMap<>();
    for (Node node : orderFromRoot(N, G, 0)) {
      parents.put(node.a, node.parent);
    }
    ArrayDeque<Integer> q = new ArrayDeque<>();
    for (int next : G[0]) {
      q.add(0);
    }

    int idx = 1;
    while (!q.isEmpty()) {
      int p = q.poll();
      int a = A[idx++];
      if (parents.get(a) != p) {
        return false;
      }

      for (int next : G[a]) {
        if (next == p) continue;

        q.add(a);
      }
    }
    return true;
  }

  static int[][] adjB(int n, int[] from, int[] to) {
    int[][] adj = new int[n][];
    int[] cnt = new int[n];
    for (int f : from) {
      cnt[f]++;
    }
    for (int t : to) {
      cnt[t]++;
    }
    for (int i = 0; i < n; i++) {
      adj[i] = new int[cnt[i]];
    }
    for (int i = 0; i < from.length; i++) {
      adj[from[i]][--cnt[from[i]]] = to[i];
      adj[to[i]][--cnt[to[i]]] = from[i];
    }
    return adj;
  }

  static Node[] orderFromRoot(int N, int[][] G, int root) {
    ArrayDeque<Node> q = new ArrayDeque<>();
    Node[] ret = new Node[N];
    int idx = 0;
    q.add(new Node(-1, root));
    while (!q.isEmpty()) {
      Node n = q.poll();
      ret[idx++] = n;
      for (int next : G[n.a]) {
        if (next == n.parent) continue;

        q.add(new Node(n.a, next));
      }
    }
    return ret;
  }

  static class Node {
    int parent, a;

    public Node(int parent, int a) {
      this.parent = parent;
      this.a = a;
    }
  }

  @SuppressWarnings("unused")
  static class FastScanner {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    FastScanner(InputStream in) {
      reader = new BufferedReader(new InputStreamReader(in));
      tokenizer = null;
    }

    String next() {
      if (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    String nextLine() {
      if (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          return reader.readLine();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken("\n");
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    int[] nextIntArray(int n) {
      int[] a = new int[n];
      for (int i = 0; i < n; i++) a[i] = nextInt();
      return a;
    }

    int[] nextIntArray(int n, int delta) {
      int[] a = new int[n];
      for (int i = 0; i < n; i++) a[i] = nextInt() + delta;
      return a;
    }

    long[] nextLongArray(int n) {
      long[] a = new long[n];
      for (int i = 0; i < n; i++) a[i] = nextLong();
      return a;
    }
  }

  static <A> void writeLines(A[] as, Function<A, String> f) {
    PrintWriter pw = new PrintWriter(System.out);
    for (A a : as) {
      pw.println(f.apply(a));
    }
    pw.flush();
  }

  static void writeLines(int[] as) {
    PrintWriter pw = new PrintWriter(System.out);
    for (int a : as) pw.println(a);
    pw.flush();
  }

  static void writeLines(long[] as) {
    PrintWriter pw = new PrintWriter(System.out);
    for (long a : as) pw.println(a);
    pw.flush();
  }

  static int max(int... as) {
    int max = Integer.MIN_VALUE;
    for (int a : as) max = Math.max(a, max);
    return max;
  }

  static int min(int... as) {
    int min = Integer.MAX_VALUE;
    for (int a : as) min = Math.min(a, min);
    return min;
  }

  static void debug(Object... args) {
    StringJoiner j = new StringJoiner(" ");
    for (Object arg : args) {
      if (arg instanceof int[]) j.add(Arrays.toString((int[]) arg));
      else if (arg instanceof long[]) j.add(Arrays.toString((long[]) arg));
      else if (arg instanceof double[]) j.add(Arrays.toString((double[]) arg));
      else if (arg instanceof Object[]) j.add(Arrays.toString((Object[]) arg));
      else j.add(arg.toString());
    }
    System.err.println(j.toString());
  }
}
