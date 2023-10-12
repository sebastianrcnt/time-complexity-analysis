class GFG {

  public static int sumOfSubstrings(String num) {

    int n = num.length();

    int sumofdigit[] = new int[n];

    sumofdigit[0] = num.charAt(0) - '0';

    int res = sumofdigit[0];

    for (int i = 1; i < n; i++) {

      int numi = num.charAt(i) - '0';

      sumofdigit[i] = (i + 1) * numi + 10 * sumofdigit[i - 1];

      res += sumofdigit[i];
    }

    return res;
  }

  public static void main(String[] args) {

    String num = "1234";

    System.out.println(sumOfSubstrings(num));
  }
}
