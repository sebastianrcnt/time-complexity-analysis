import java.io.*;

class GFG {

  static int sequence(int n) {

    if (n == 1 || n == 2) return 1;
    else return sequence(sequence(n - 1)) + sequence(n - sequence(n - 1));
  }

  public static void main(String args[]) {

    int n = 10;

    System.out.println(sequence(n));
  }
}
