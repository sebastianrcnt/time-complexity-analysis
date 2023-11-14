class GFG {

  static void findSmallest(int m, int s) {

    if (s == 0) {

      System.out.print(m == 1 ? "Smallest number is 0" : "Not possible");

      return;
    }

    if (s > 9 * m) {

      System.out.println("Not possible");

      return;
    }

    int[] res = new int[m];

    s -= 1;

    for (int i = m - 1; i > 0; i--) {

      if (s > 9) {

        res[i] = 9;

        s -= 9;

      } else {

        res[i] = s;

        s = 0;
      }
    }

    res[0] = s + 1;

    System.out.print("Smallest number is ");

    for (int i = 0; i < m; i++) System.out.print(res[i]);
  }

  public static void main(String[] args) {

    int s = 9, m = 2;

    findSmallest(m, s);
  }
}
