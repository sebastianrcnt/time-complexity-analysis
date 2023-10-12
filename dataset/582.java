import java.io.*;
import java.util.*;

public class Solution {
  public static void main(String[] args) throws IOException {
    PrintWriter pw = new PrintWriter(System.out); // use pw.println() not pw.write();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    /*
            inputCopy
    5 3
    xyabd
    outputCopy
    29
    inputCopy
    7 4
    problem
    outputCopy
    34
    inputCopy
    2 2
    ab
    outputCopy
    -1
    inputCopy
    12 1
    abaabbaaabbb
    outputCopy
    1
            */
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    String str = st.nextToken();
    char[] arr = str.toCharArray();
    Arrays.sort(arr);
    int weight = arr[0] - 96;
    char a = arr[0];
    int included = 1;
    for (int i = 1; i < arr.length; ++i) {
      if (included == k) break;
      char c = arr[i];
      if (c - a < 2) continue;

      weight += arr[i] - 96;
      ++included;
      a = arr[i];
    }
    if (included == k) pw.println(weight);
    else pw.println(-1);
    pw.close(); // Do not forget to write it after every program return statement !!
  }
}