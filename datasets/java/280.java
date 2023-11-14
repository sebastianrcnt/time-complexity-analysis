class GFG {

  static final int MAX_CHARS = 26;

  static void longestSubseqWithK(String str, int k) {

    int n = str.length();

    int freq[] = new int[MAX_CHARS];

    for (int i = 0; i < n; i++) {

      freq[str.charAt(i) - 'a']++;
    }

    for (int i = 0; i < n; i++) {

      if (freq[str.charAt(i) - 'a'] >= k) {

        System.out.print(str.charAt(i));
      }
    }
  }

  public static void main(String[] args) {

    String str = "geeksforgeeks";

    int k = 2;

    longestSubseqWithK(str, k);
  }
}
