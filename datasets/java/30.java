import java.io.*;

class GFG {

  static void printArray(int A[], int size) {

    for (int i = 0; i < size; i++) System.out.print(A[i] + " ");

    System.out.println();
  }

  static void merge(int arr[], int l, int m, int r) {

    int i, j, k;

    int n1 = m - l + 1;

    int n2 = r - m;

    int L[] = new int[n1];

    int R[] = new int[n2];

    for (i = 0; i < n1; i++) L[i] = arr[l + i];

    for (j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

    i = 0;

    j = 0;

    k = l;

    while (i < n1 && L[i] < 0) arr[k++] = L[i++];

    while (j < n2 && R[j] < 0) arr[k++] = R[j++];

    while (i < n1) arr[k++] = L[i++];

    while (j < n2) arr[k++] = R[j++];
  }

  static void RearrangePosNeg(int arr[], int l, int r) {

    if (l < r) {

      int m = l + (r - l) / 2;

      RearrangePosNeg(arr, l, m);

      RearrangePosNeg(arr, m + 1, r);

      merge(arr, l, m, r);
    }
  }

  public static void main(String[] args) {

    int arr[] = {-12, 11, -13, -5, 6, -7, 5, -3, -6};

    int arr_size = arr.length;

    RearrangePosNeg(arr, 0, arr_size - 1);

    printArray(arr, arr_size);
  }
}
