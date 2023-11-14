import java.util.*;

public class GfG {

  public static String noAdjacentDup(String s1) {

    int n = s1.length();

    char[] s = s1.toCharArray();

    for (int i = 1; i < n; i++) {

      if (s[i] == s[i - 1]) {

        s[i] = 'a';

        while (s[i] == s[i - 1] || (i + 1 < n && s[i] == s[i + 1])) s[i]++;

        i++;
      }
    }

    return (new String(s));
  }

  public static void main(String argc[]) {

    String s = "geeksforgeeks";

    System.out.println(noAdjacentDup(s));
  }
}
