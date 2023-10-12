public class GFG {

  static final int M = 26;

  static int getIdx(char ch) {

    return (ch - 'a');
  }

  static boolean allSame(int freq[], int N) {

    int same = 0;

    int i;

    for (i = 0; i < N; i++) {

      if (freq[i] > 0) {

        same = freq[i];

        break;
      }
    }

    for (int j = i + 1; j < N; j++) if (freq[j] > 0 && freq[j] != same) return false;

    return true;
  }

  static boolean possibleSameCharFreqByOneRemoval(String str) {

    int l = str.length();

    int[] freq = new int[M];

    for (int i = 0; i < l; i++) freq[getIdx(str.charAt(i))]++;

    if (allSame(freq, M)) return true;

    for (char c = 'a'; c <= 'z'; c++) {

      int i = getIdx(c);

      if (freq[i] > 0) {

        freq[i]--;

        if (allSame(freq, M)) return true;

        freq[i]++;
      }
    }

    return false;
  }

  public static void main(String args[]) {

    String str = "xyyzz";

    if (possibleSameCharFreqByOneRemoval(str)) System.out.println("Yes");
    else System.out.println("No");
  }
}
