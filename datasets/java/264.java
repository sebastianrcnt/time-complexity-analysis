import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class GFG {

  static final int MAX_CHAR = 26;

  static String getKey(String str) {

    boolean[] visited = new boolean[MAX_CHAR];

    Arrays.fill(visited, false);

    for (int j = 0; j < str.length(); j++) visited[str.charAt(j) - 'a'] = true;

    String key = "";

    for (int j = 0; j < MAX_CHAR; j++) if (visited[j]) key = key + (char) ('a' + j);

    return key;
  }

  static void wordsWithSameCharSet(String words[], int n) {

    HashMap<String, ArrayList<Integer>> Hash = new HashMap<>();

    for (int i = 0; i < n; i++) {

      String key = getKey(words[i]);

      if (Hash.containsKey(key)) {

        ArrayList<Integer> get_al = Hash.get(key);

        get_al.add(i);

        Hash.put(key, get_al);

      } else {

        ArrayList<Integer> new_al = new ArrayList<>();

        new_al.add(i);

        Hash.put(key, new_al);
      }
    }

    for (Entry<String, ArrayList<Integer>> it : Hash.entrySet()) {

      ArrayList<Integer> get = it.getValue();

      for (Integer v : get) System.out.print(words[v] + ", ");

      System.out.println();
    }
  }

  public static void main(String args[]) {

    String words[] = {
      "may",
      "student",
      "students",
      "dog",
      "studentssess",
      "god",
      "cat",
      "act",
      "tab",
      "bat",
      "flow",
      "wolf",
      "lambs",
      "amy",
      "yam",
      "balms",
      "looped",
      "poodle"
    };

    int n = words.length;

    wordsWithSameCharSet(words, n);
  }
}
