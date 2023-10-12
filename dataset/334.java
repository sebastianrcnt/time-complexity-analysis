import java.io.*;
import java.util.*;

class GFG {

  public static int findLength(String str, int n) {

    int current_sum = 0;

    int max_sum = 0;

    for (int i = 0; i < n; i++) {

      current_sum += (str.charAt(i) == '0' ? 1 : -1);

      if (current_sum < 0) current_sum = 0;

      max_sum = Math.max(current_sum, max_sum);
    }

    return max_sum == 0 ? -1 : max_sum;
  }

  public static void main(String[] args) {

    String str = "11000010001";

    int n = str.length();

    System.out.println(findLength(str, n));
  }
}
