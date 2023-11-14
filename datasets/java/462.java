import java.io.*;
import java.util.*;

class GFG {

  static int policeThief(char arr[], int n, int k) {

    int res = 0;

    ArrayList<Integer> thi = new ArrayList<Integer>();

    ArrayList<Integer> pol = new ArrayList<Integer>();

    for (int i = 0; i < n; i++) {

      if (arr[i] == 'P') pol.add(i);
      else if (arr[i] == 'T') thi.add(i);
    }

    int l = 0, r = 0;

    while (l < thi.size() && r < pol.size()) {

      if (Math.abs(thi.get(l) - pol.get(r)) <= k) {

        res++;

        l++;

        r++;

      } else if (thi.get(l) < pol.get(r)) l++;
      else r++;
    }

    return res;
  }

  public static void main(String args[]) {

    int k, n;

    char arr1[] = new char[] {'P', 'T', 'T', 'P', 'T'};

    k = 2;

    n = arr1.length;

    System.out.println("Maximum thieves caught: " + policeThief(arr1, n, k));

    char arr2[] = new char[] {'T', 'T', 'P', 'P', 'T', 'P'};

    k = 2;

    n = arr2.length;

    System.out.println("Maximum thieves caught: " + policeThief(arr2, n, k));

    char arr3[] = new char[] {'P', 'T', 'P', 'T', 'T', 'P'};

    k = 3;

    n = arr3.length;

    System.out.println("Maximum thieves caught: " + policeThief(arr3, n, k));
  }
}
