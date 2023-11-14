import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DoggoRecoloring {
  public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  public static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = nextInt();
    String s = nextString();
    boolean b = false;

    if (n == 1 || n > 26) b = true;
    else
      for (int i = 0; i < n; i++)
        for (int j = i + 1; j < n; j++) if (s.charAt(i) == s.charAt(j)) b = true;

    System.out.println(b ? "Yes" : "No");
  }

  public static String nextLine() throws IOException {
    return in.readLine();
  }

  public static String nextString() throws IOException {
    while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(in.readLine());
    return st.nextToken();
  }

  public static int nextInt() throws IOException {
    return Integer.parseInt(nextString());
  }

  public static long nextLong() throws IOException {
    return Long.parseLong(nextString());
  }

  public static int[] intArray(int n) throws IOException {
    int[] a = new int[n];
    for (int i = 0; i < n; i++) a[i] = nextInt();
    return a;
  }

  public static int[][] intArray(int n, int m) throws IOException {
    int[][] a = new int[n][m];
    for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = nextInt();
    return a;
  }

  public static long[] longArray(int n) throws IOException {
    long[] a = new long[n];
    for (int i = 0; i < n; i++) a[i] = nextLong();
    return a;
  }
}