import java.io.*;
import java.util.*;

class GFG {

  static void find_max(int[] A, int N, int K) {

    HashMap<Integer, Integer> Count = new HashMap<>();

    for (int i = 0; i < K - 1; i++)
      if (Count.containsKey(A[i])) Count.put(A[i], 1 + Count.get(A[i]));
      else Count.put(A[i], 1);

    TreeSet<Integer> Myset = new TreeSet<Integer>();

    for (Map.Entry x : Count.entrySet()) {

      if (Integer.parseInt(String.valueOf(x.getValue())) == 1)
        Myset.add(Integer.parseInt(String.valueOf(x.getKey())));
    }

    for (int i = K - 1; i < N; i++) {

      if (Count.containsKey(A[i])) Count.put(A[i], 1 + Count.get(A[i]));
      else Count.put(A[i], 1);

      if (Integer.parseInt(String.valueOf(Count.get(A[i]))) == 1) Myset.add(A[i]);
      else Myset.remove(A[i]);

      if (Myset.size() == 0) System.out.println("Nothing");
      else System.out.println(Myset.last());

      int x = A[i - K + 1];

      Count.put(x, Count.get(x) - 1);

      if (Integer.parseInt(String.valueOf(Count.get(x))) == 1) Myset.add(x);

      if (Integer.parseInt(String.valueOf(Count.get(x))) == 0) Myset.remove(x);
    }
  }

  public static void main(String args[]) {

    int[] a = {1, 2, 2, 3, 3};

    int n = a.length;

    int k = 3;

    find_max(a, n, k);
  }
}
