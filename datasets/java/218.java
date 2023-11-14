import java.util.Stack;

public class GFG {

  static Stack<Integer> st = new Stack<>();

  static void push_digits(int number) {

    while (number != 0) {

      st.push(number % 10);

      number = number / 10;
    }
  }

  static int reverse_number(int number) {

    push_digits(number);

    int reverse = 0;

    int i = 1;

    while (!st.isEmpty()) {

      reverse = reverse + (st.peek() * i);

      st.pop();

      i = i * 10;
    }

    return reverse;
  }

  public static void main(String[] args) {

    int number = 39997;

    System.out.println(reverse_number(number));
  }
}
