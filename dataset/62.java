class GFG {

  static int answer_query(int a[], int n, int l, int r) {

    int count = 0;

    for (int i = l; i < r; i++) if (a[i] == a[i + 1]) count += 1;

    return count;
  }

  public static void main(String[] args) {

    int a[] = {1, 2, 2, 2, 3, 3, 4, 4, 4};

    int n = a.length;

    int L, R;

    L = 1;

    R = 8;

    System.out.println(answer_query(a, n, L, R));

    L = 0;

    R = 4;

    System.out.println(answer_query(a, n, L, R));
  }
}
