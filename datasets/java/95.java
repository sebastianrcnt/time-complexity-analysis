import java.util.*;

class Main {

  static void minAbsSumPair(int arr[], int n) {

    int sum, min_sum = 999999;

    int l = 0, r = n - 1;

    int min_l = l, min_r = n - 1;

    if (n < 2) {

      System.out.println("Invalid Input");

      return;
    }

    sort(arr, l, r);

    while (l < r) {

      sum = arr[l] + arr[r];

      if (Math.abs(sum) < Math.abs(min_sum)) {

        min_sum = sum;

        min_l = l;

        min_r = r;
      }

      if (sum < 0) l++;
      else r--;
    }

    System.out.println(
        " The two elements whose " + "sum is minimum are " + arr[min_l] + " and " + arr[min_r]);
  }

  public static void main(String[] args) {

    int arr[] = {1, 60, -10, 70, -80, 85};

    int n = arr.length;

    minAbsSumPair(arr, n);
  }

  static int partition(int arr[], int low, int high) {

    int pivot = arr[high];

    int i = (low - 1);

    for (int j = low; j < high; j++) {

      if (arr[j] <= pivot) {

        i++;

        int temp = arr[i];

        arr[i] = arr[j];

        arr[j] = temp;
      }
    }

    int temp = arr[i + 1];

    arr[i + 1] = arr[high];

    arr[high] = temp;

    return i + 1;
  }

  static void sort(int arr[], int low, int high) {

    if (low < high) {

      int pi = partition(arr, low, high);

      sort(arr, low, pi - 1);

      sort(arr, pi + 1, high);
    }
  }
}
