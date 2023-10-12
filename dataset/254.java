import java.io.*;

public class GFG {

  static int countNonEmptySubstr(String str) {

    int n = str.length();

    return n * (n + 1) / 2;
  }

  public static void main(String args[]) {

    String s = "abcde";

    System.out.println(countNonEmptySubstr(s));
  }
}
