import java.util.Arrays;

public class GFG {

  static int minSum(int[] A, int n) {

    int min_val = Arrays.stream(A).min().getAsInt();

    return (min_val * (n - 1));
  }

  public static void main(String[] args) {

    int[] A = {3, 6, 2, 8, 7, 5};

    int n = A.length;

    System.out.println((minSum(A, n)));
  }
}
