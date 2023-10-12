class GFG {

  public static int N = 1000;

  static int prefixans[] = new int[1000];

  public static void countIndex(int a[], int n) {

    for (int i = 0; i < n; i++) {

      if (i + 1 < n && a[i] == a[i + 1]) prefixans[i] = 1;

      if (i != 0) prefixans[i] += prefixans[i - 1];
    }
  }

  public static int answer_query(int l, int r) {

    if (l == 0) return prefixans[r - 1];
    else return prefixans[r - 1] - prefixans[l - 1];
  }

  public static void main(String args[]) {

    int a[] = {1, 2, 2, 2, 3, 3, 4, 4, 4};

    int n = 9;

    countIndex(a, n);

    int L, R;

    L = 1;

    R = 8;

    System.out.println(answer_query(L, R));

    L = 0;

    R = 4;

    System.out.println(answer_query(L, R));
  }
}
