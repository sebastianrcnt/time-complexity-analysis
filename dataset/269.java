class GFG {

  static int MAX_CHAR = 26;

  static void findAndPrintUncommonChars(String str1, String str2) {

    int present[] = new int[MAX_CHAR];

    for (int i = 0; i < MAX_CHAR; i++) {

      present[i] = 0;
    }

    int l1 = str1.length();

    int l2 = str2.length();

    for (int i = 0; i < l1; i++) {

      present[str1.charAt(i) - 'a'] = 1;
    }

    for (int i = 0; i < l2; i++) {

      if (present[str2.charAt(i) - 'a'] == 1 || present[str2.charAt(i) - 'a'] == -1) {

        present[str2.charAt(i) - 'a'] = -1;

      } else {

        present[str2.charAt(i) - 'a'] = 2;
      }
    }

    for (int i = 0; i < MAX_CHAR; i++) {

      if (present[i] == 1 || present[i] == 2) {

        System.out.print((char) (i + 'a') + " ");
      }
    }
  }

  public static void main(String[] args) {

    String str1 = "characters";

    String str2 = "alphabets";

    findAndPrintUncommonChars(str1, str2);
  }
}
