import java.io.FileNotFoundException;
import java.util.Scanner;

public class A1180 {

  public static void main(String[] args) throws FileNotFoundException {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    int layers = n - 1;
    int counter = 0;
    for (int i = 1; i < layers + 1; i++) {
      counter += i;
    }

    System.out.println(1 + counter * 4);
  }
}
