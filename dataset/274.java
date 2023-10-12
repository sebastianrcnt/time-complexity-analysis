public class GFG {

  static void printMinIndexChar(String str, String patt) {

    int minIndex = Integer.MAX_VALUE;

    int m = str.length();

    int n = patt.length();

    for (int i = 0; i < n; i++) {

      for (int j = 0; j < m; j++) {

        if (patt.charAt(i) == str.charAt(j) && j < minIndex) {

          minIndex = j;

          break;
        }
      }
    }

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
