import java.io.*;
import java.math.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer s = new StringTokenizer(st.readLine());
    long n = Long.parseLong(s.nextToken());
    long k = Long.parseLong(s.nextToken());
    long ans = k / n;
    if (k % n != 0) ans++;
    System.out.println(ans + "");
  }
}
