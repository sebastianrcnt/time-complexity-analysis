import java.io.*;
import java.util.*;

public class GFG {

  static int MaxSumDifference(Integer[] a, int n) {

    List<Integer> finalSequence = new ArrayList<Integer>();

    Arrays.sort(a);

    for (int i = 0; i < n / 2; ++i) {

      finalSequence.add(a[i]);

      finalSequence.add(a[n - i - 1]);
    }

    int MaximumSum = 0;

    for (int i = 0; i < n - 1; ++i) {

      MaximumSum = MaximumSum + Math.abs(finalSequence.get(i) - finalSequence.get(i + 1));
    }

    MaximumSum = MaximumSum + Math.abs(finalSequence.get(n - 1) - finalSequence.get(0));

    return MaximumSum;
  }

  public static void main(String args[]) {

    Integer[] a = {1, 2, 4, 8};

    int n = a.length;

    System.out.print(MaxSumDifference(a, n));
  }
}
