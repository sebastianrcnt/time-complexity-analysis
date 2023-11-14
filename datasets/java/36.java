public class Main {

  public static void rearrange(int arr[], int n) {

    int max_ele = arr[n - 1];

    int min_ele = arr[0];

    for (int i = 0; i < n; i++) {

      if (i % 2 == 0) {

        arr[i] = max_ele;

        max_ele -= 1;

      } else {

        arr[i] = min_ele;

        min_ele += 1;
      }
    }
  }

  public static void main(String args[]) {

    int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    int n = arr.length;

    System.out.println("Original Array");

    for (int i = 0; i < n; i++) System.out.print(arr[i] + " ");

    rearrange(arr, n);

    System.out.print("\nModified Array\n");

    for (int i = 0; i < n; i++) System.out.print(arr[i] + " ");
  }
}
