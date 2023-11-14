public class GFG {

  static final int MAX_CHAR = 26;

  static int countSubstringWithEqualEnds(String s) {

    int result = 0;

    int n = s.length();

    int[] count = new int[MAX_CHAR];

    for (int i = 0; i < n; i++) count[s.charAt(i) - 'a']++;

    for (int i = 0; i < MAX_CHAR; i++) result += (count[i] * (count[i] + 1) / 2);

    return result;
  }

  public static void main(String args[]) {

    String s = "abcab";

    System.out.println(countSubstringWithEqualEnds(s));
  }
}
