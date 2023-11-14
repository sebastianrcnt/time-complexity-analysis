import java.util.*;

class GFG {

  static int MAX_CHAR = 26;

  public static void commonCharacters(String str[], int n) {

    Boolean[] prim = new Boolean[MAX_CHAR];

    Arrays.fill(prim, new Boolean(true));

    for (int i = 0; i < n; i++) {

      Boolean[] sec = new Boolean[MAX_CHAR];

      Arrays.fill(sec, new Boolean(false));

      for (int j = 0; j < str[i].length(); j++) {

        if (prim[str[i].charAt(j) - 'a']) sec[str[i].charAt(j) - 'a'] = true;
      }

      System.arraycopy(sec, 0, prim, 0, MAX_CHAR);
    }

    for (int i = 0; i < 26; i++)
      if (prim[i]) {

        System.out.print(Character.toChars(i + 97));

        System.out.print(" ");
      }
  }

  public static void main(String[] args) {

    String str[] = {"geeksforgeeks", "gemkstones", "acknowledges", "aguelikes"};

    int n = str.length;

    commonCharacters(str, n);
  }
}
