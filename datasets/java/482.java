import java.util.PriorityQueue;

class MinSum {

  public static long solve(int[] a) {

    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

    StringBuilder num1 = new StringBuilder();

    StringBuilder num2 = new StringBuilder();

    for (int x : a) pq.add(x);

    while (!pq.isEmpty()) {

      num1.append(pq.poll() + "");

      if (!pq.isEmpty()) num2.append(pq.poll() + "");
    }

    long sum = Long.parseLong(num1.toString()) + Long.parseLong(num2.toString());

    return sum;
  }

  public static void main(String[] args) {

    int arr[] = {6, 8, 4, 5, 2, 3};

    System.out.println("The required sum is " + solve(arr));
  }
}
