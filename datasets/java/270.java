import java.util.*;

class GFG {

  static boolean isVowel(char ch) {

    if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') return true;

    return false;
  }

  static String createAltStr(String str1, String str2, int start, int l) {

    String finalStr = "";

    for (int i = 0, j = start; j < l; i++, j++)
      finalStr = (finalStr + str1.charAt(i)) + str2.charAt(j);

    return finalStr;
  }

  static String findAltStr(String str) {

    int nv = 0, nc = 0;

    String vstr = "", cstr = "";

    int l = str.length();

    for (int i = 0; i < l; i++) {

      char ch = str.charAt(i);

      if (isVowel(ch)) {

        nv++;

        vstr = vstr + ch;

      } else {

        nc++;

        cstr = cstr + ch;
      }
    }

    if (Math.abs(nv - nc) >= 2) return "no such string";

    if (nv > nc) return (vstr.charAt(0) + createAltStr(cstr, vstr, 1, nv));

    if (nc > nv) return (cstr.charAt(0) + createAltStr(vstr, cstr, 1, nc));

    if (cstr.charAt(0) < vstr.charAt(0)) return createAltStr(cstr, vstr, 0, nv);

    return createAltStr(vstr, cstr, 0, nc);
  }

  public static void main(String args[]) {

    String str = "geeks";

    System.out.println(findAltStr(str));
  }
}
