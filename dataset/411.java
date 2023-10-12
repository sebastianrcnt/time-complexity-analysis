import java.util.*;

class GFG {

  static int N = 12, ans;

  static Vector<Vector<Integer>> tree = new Vector<Vector<Integer>>();

  static int dfs(int visit[], int node) {

    int num = 0, temp = 0;

    visit[node] = 1;

    for (int i = 0; i < tree.get(node).size(); i++) {

      if (visit[tree.get(node).get(i)] == 0) {

        temp = dfs(visit, tree.get(node).get(i));

        if (temp % 2 != 0) num += temp;
        else ans++;
      }
    }

    return num + 1;
  }

  static int minEdge(int n) {

    int visit[] = new int[n + 2];

    ans = 0;

    dfs(visit, 1);

    return ans;
  }

  public static void main(String args[]) {

    int n = 10;

    for (int i = 0; i < n + 2; i++) tree.add(new Vector<Integer>());

    tree.get(1).add(3);

    tree.get(3).add(1);

    tree.get(1).add(6);

    tree.get(6).add(1);

    tree.get(1).add(2);

    tree.get(2).add(1);

    tree.get(3).add(4);

    tree.get(4).add(3);

    tree.get(6).add(8);

    tree.get(8).add(6);

    tree.get(2).add(7);

    tree.get(7).add(2);

    tree.get(2).add(5);

    tree.get(5).add(2);

    tree.get(4).add(9);

    tree.get(9).add(4);

    tree.get(4).add(10);

    tree.get(10).add(4);

    System.out.println(minEdge(n));
  }
}
