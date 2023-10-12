import java.io.*;

class GFG {

  static void digitsNum(int N) {

    if (N == 0) System.out.println("0");

    if (N % 9 != 0) System.out.print((N % 9));

    for (int i = 1; i <= (N / 9); ++i) System.out.print("9");

    for (int i = 1; i <= N; ++i) System.out.print("0");

    System.out.print("");
  }

  public static void main(String[] args) {

    int N = 5;

    System.out.print("The number is : ");

    digitsNum(N);
  }
}
