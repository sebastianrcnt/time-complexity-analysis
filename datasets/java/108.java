import java.util.*;

class GfG {

  public static int minMaxProduct(int arr1[], int arr2[], int n1, int n2) {

    Arrays.sort(arr1);

    Arrays.sort(arr2);

    return arr1[n1 - 1] * arr2[0];
  }

  public static void main(String argc[]) {

    int[] arr1 = new int[] {10, 2, 3, 6, 4, 1};

    int[] arr2 = new int[] {5, 1, 4, 2, 6, 9};

    int n1 = 6;

    int n2 = 6;

    System.out.println(minMaxProduct(arr1, arr2, n1, n2));
  }
}
