import java.io.*;
import java.util.*;

public class Codechef {
  static String reverse(String s) {
    String reverse = "";
    for (int i = s.length() - 1; i >= 0; i--) {
      reverse = reverse + s.charAt(i);
    }
    return reverse;
  }

  public static void main(String[] args) throws java.lang.Exception {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int x = m % (int) Math.pow(2, n);
    System.out.println(x);
  }
}
