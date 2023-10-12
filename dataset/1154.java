import java.io.*;
import java.util.*;

public class Solution {
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      String str = br.readLine();
      int q = Integer.parseInt(str);

      if (q % 2 == 0 && q != 2) System.out.println("YES");
      else System.out.println("NO");

    } catch (IOException e) {
      System.out.println("Input Error");
    }
  }
}
