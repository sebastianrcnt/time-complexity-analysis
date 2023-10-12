import java.io.*;
import java.util.Arrays;

class GFG {

  static int maxLevel(int[] boxes, int n) {

    Arrays.sort(boxes);

    int ans = 1;

    int prev_width = boxes[0];

    int prev_count = 1;

    int curr_count = 0;

    int curr_width = 0;

    for (int i = 1; i < n; i++) {

      curr_width += boxes[i];

      curr_count += 1;

      if (curr_width > prev_width && curr_count > prev_count) {

        prev_width = curr_width;

        prev_count = curr_count;

        curr_count = 0;

        curr_width = 0;

        ans++;
      }
    }

    return ans;
  }

  public static void main(String[] args) {

    int[] boxes = {10, 20, 30, 50, 60, 70};

    int n = boxes.length;

    System.out.println(maxLevel(boxes, n));
  }
}
