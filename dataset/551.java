import java.util.Scanner;

public class Dialog1 {
  private static int n;
  private static String s;
  private static char[] a;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    n = input.nextInt();
    s = input.next();
    a = s.toCharArray();
    for (int i = 0; i < 200; ++i) {
      int cur = i;
      boolean fl = true;
      for (int j = 0; j < n; ++j) {
        if (a[j] == '+') ++cur;
        else --cur;
        if (cur < 0) fl = false;
      }
      if (fl) {
        System.out.print(cur);
        return;
      }
    }
  }
}
