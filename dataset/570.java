import java.util.*;

public class Main {
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    if (n == k) {
      String s = new String();
      for (int i = 0; i < k; i++) {
        s = s + "1";
      }
      System.out.println(s);
    } else {
      int a = (n - k) / 2;
      String s = new String();
      while (s.length() < n) {
        for (int i = 0; i < a && s.length() < n; i++) {
          s = s + "1";
        }
        if (s.length() < n) {
          s = s + "0";
        }
      }
      System.out.println(s);
    }
  }
}
