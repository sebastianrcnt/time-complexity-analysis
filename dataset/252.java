class GFG {

  static int MAX_CHAR = 26;

  static boolean check(int freq[], int k) {

    for (int i = 0; i < MAX_CHAR; i++) if (freq[i] != 0 && freq[i] != k) return false;

    return true;
  }

  static int substrings(String s, int k) {

    int res = 0;

    for (int i = 0; i < s.length(); i++) {

      int freq[] = new int[MAX_CHAR];

      for (int j = i; j < s.length(); j++) {

        int index = s.charAt(j) - 'a';

        freq[index]++;

        if (freq[index] > k) break;
        else if (freq[index] == k && check(freq, k) == true) res++;
      }
    }

    return res;
  }

  public static void main(String[] args) {

    String s = "aabbcc";

    int k = 2;

    System.out.println(substrings(s, k));

    s = "aabbc";

    k = 2;

    System.out.println(substrings(s, k));
  }
}
