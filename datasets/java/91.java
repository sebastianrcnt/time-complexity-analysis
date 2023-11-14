import java.util.Arrays;

class GFG {

  static boolean sortedAfterSwap(int A[], boolean B[], int n) {

    int i, j;

    for (i = 0; i < n - 1; i++) {

      if (B[i]) {

        j = i;

        while (B[j]) {

          j++;
        }

        Arrays.sort(A, i, 1 + j);

        i = j;
      }
    }

    for (i = 0; i < n; i++) {

      if (A[i] != i + 1) {

        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {

    int A[] = {1, 2, 5, 3, 4, 6};

    boolean B[] = {false, true, true, true, false};

    int n = A.length;

    if (sortedAfterSwap(A, B, n)) {

      System.out.println("A can be sorted");

    } else {

      System.out.println("A can not be sorted");
    }
  }
}
