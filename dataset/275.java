import java.util.HashMap;

public class GFG {

  static void printMinIndexChar(String str, String patt) {

    HashMap<Character, Integer> hm = new HashMap<>();

    int minIndex = Integer.MAX_VALUE;

    int m = str.length();

    int n = patt.length();

    for (int i = 0; i < m; i++) if (!hm.containsKey(str.charAt(i))) hm.put(str.charAt(i), i);

    for (int i = 0; i < n; i++)
      if (hm.containsKey(patt.charAt(i)) && hm.get(patt.charAt(i)) < minIndex)
        minIndex = hm.get(patt.charAt(i));

    if (minIndex != Integer.MAX_VALUE)
      System.out.println("Minimum Index Character = " + str.charAt(minIndex));
    else System.out.println("No character present");
  }

  public static void main(String[] args) {

    String str = "geeksforgeeks";

    String patt = "set";

    printMinIndexChar(str, patt);
  }
}
