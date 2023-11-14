class Test {

  static int countPairs(String str) {

    int result = 0;

    int n = str.length();

    for (int i = 0; i < n; i++)
      for (int j = i + 1; j < n; j++)
        if (Math.abs(str.charAt(i) - str.charAt(j)) == Math.abs(i - j)) result++;

    return result;
  }

  public static void main(String args[]) {

    String str = "geeksforgeeks";

    System.out.println(countPairs(str));
  }
}
