import java.io.*;

class GFG {

  static int zzis(int arr[], int n) {

    int las[][] = new int[n][2];

    for (int i = 0; i < n; i++) las[i][0] = las[i][1] = 1;

    int res = 1;

    for (int i = 1; i < n; i++) {

      for (int j = 0; j < i; j++) {

        if (arr[j] < arr[i] && las[i][0] < las[j][1] + 1) las[i][0] = las[j][1] + 1;

        if (arr[j] > arr[i] && las[i][1] < las[j][0] + 1) las[i][1] = las[j][0] + 1;
      }

      if (res < Math.max(las[i][0], las[i][1])) res = Math.max(las[i][0], las[i][1]);
    }

    return res;
  }

  public static void main(String[] args) {

    int arr[] = {10, 22, 9, 33, 49, 50, 31, 60};

    int n = arr.length;

    System.out.println("Length of Longest " + "alternating subsequence is " + zzis(arr, n));
  }
}
