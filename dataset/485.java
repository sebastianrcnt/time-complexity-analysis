import java.util.*;

class GFG {

  static boolean ifPossible(int arr[], int n) {

    int copy[] = Arrays.copyOf(arr, arr.length);

    Arrays.sort(copy);

    for (int i = 0; i < n; i++) {

      if (!(arr[i] == copy[i]) && !(arr[n - 1 - i] == copy[i])) return false;
    }

    return true;
  }

  public static void main(String[] args) {

    int arr[] = {1, 7, 6, 4, 5, 3, 2, 8};

    int n = arr.length;

    if (ifPossible(arr, n)) System.out.println("Yes");
    else System.out.println("No");
  }
}
