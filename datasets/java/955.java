import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.TreeSet;

public class Test {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] line = reader.readLine().split(" ");
    int w = Integer.valueOf(line[0]);
    int h = Integer.valueOf(line[1]);
    int n = Integer.valueOf(line[2]);

    Request[] requests = new Request[n];

    for (int i = 0; i < n; i++) {
      line = reader.readLine().split(" ");
      requests[i] = new Request(line[0], Integer.valueOf(line[1]));
    }

    for (long e : solve(h, w, requests)) System.out.println(e);
  }

  private static Request[] generate(int w, int h, int n) {
    Request[] requests = new Request[n];
    Random rnd = new Random();

    for (int i = 0; i < n; i++) {
      requests[i] =
          rnd.nextBoolean() ? new Request("V", rnd.nextInt(w)) : new Request("H", rnd.nextInt(h));
    }

    return requests;
  }

  private static long[] solve(int h, int w, Request[] requests) {
    TreeSet<Integer> hTree = new TreeSet<>();
    TreeSet<Integer> wTree = new TreeSet<>();

    Queue<CoordinateWithSize> hHeap = new PriorityQueue<>();
    Queue<CoordinateWithSize> wHeap = new PriorityQueue<>();

    hTree.add(0);
    hTree.add(h);
    wTree.add(0);
    wTree.add(w);

    hHeap.add(new CoordinateWithSize(0, h));
    wHeap.add(new CoordinateWithSize(0, w));

    long[] res = new long[requests.length];
    for (int i = 0; i < requests.length; i++) {
      Request request = requests[i];

      switch (request.type) {
        case "H":
          {
            if (!hTree.contains(request.coordinate)) {
              int higher = hTree.higher(request.coordinate);
              int lower = hTree.lower(request.coordinate);

              hHeap.add(new CoordinateWithSize(lower, request.coordinate - lower));
              hHeap.add(new CoordinateWithSize(request.coordinate, higher - request.coordinate));

              hTree.add(request.coordinate);
            }

            break;
          }
        case "V":
          {
            if (!wTree.contains(request.coordinate)) {
              int higher = wTree.higher(request.coordinate);
              int lower = wTree.lower(request.coordinate);

              wHeap.add(new CoordinateWithSize(lower, request.coordinate - lower));
              wHeap.add(new CoordinateWithSize(request.coordinate, higher - request.coordinate));

              wTree.add(request.coordinate);
            }

            break;
          }
        default:
          throw new IllegalStateException("Unknown type [type=" + request.type + "]");
      }

      while (true) {
        CoordinateWithSize c = hHeap.peek();
        if (hTree.higher(c.coordinate) - c.coordinate == c.size) break;
        hHeap.remove();
      }

      while (true) {
        CoordinateWithSize c = wHeap.peek();
        if (wTree.higher(c.coordinate) - c.coordinate == c.size) break;
        wHeap.remove();
      }

      res[i] = 1L * hHeap.peek().size * wHeap.peek().size;
    }

    return res;
  }

  private static class CoordinateWithSize implements Comparable<CoordinateWithSize> {

    private final int coordinate;

    private final int size;

    public CoordinateWithSize(int coordinate, int size) {
      this.coordinate = coordinate;
      this.size = size;
    }

    @Override
    public int compareTo(CoordinateWithSize o) {
      return Integer.compare(o.size, size);
    }
  }

  private static class Request {

    private final String type;

    private final int coordinate;

    public Request(String type, int coordinate) {
      this.type = type;
      this.coordinate = coordinate;
    }
  }
}
