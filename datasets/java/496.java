import java.io.*;
import java.util.*;

class GFG {

  static void printVector(Vector<Integer> v) {

    System.out.println(v.size());

    for (int i = 0; i < v.size(); i++) System.out.print(v.get(i) + " ");

    System.out.println();
  }

  static void findTwoGroup(int n) {

    int sum = n * (n + 1) / 2;

    int group1Sum = sum / 2;

    Vector<Integer> group1 = new Vector<Integer>();

    Vector<Integer> group2 = new Vector<Integer>();

    for (int i = n; i > 0; i--) {

      if (group1Sum - i >= 0) {

        group1.add(i);

        group1Sum -= i;

      } else {

        group2.add(i);
      }
    }

    printVector(group1);

    printVector(group2);
  }

  public static void main(String[] args) {

    int n = 5;

    findTwoGroup(n);
  }
}
