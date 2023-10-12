import java.util.PriorityQueue;

class GFG {

  public static int DecreasingArray(int a[], int n) {

    int sum = 0, dif = 0;

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i = 0; i < n; i++) {

      if (!pq.isEmpty() && pq.element() < a[i]) {

        dif = a[i] - pq.element();

        sum += dif;

        pq.remove();

        pq.add(a[i]);
      }

      pq.add(a[i]);
    }

    return sum;
  }

  public static void main(String[] args) {

    int[] a = {3, 1, 2, 1};

    int n = a.length;

    System.out.println(DecreasingArray(a, n));
  }
}
