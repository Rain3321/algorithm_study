import java.util.*;

public class BJ11286 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    PriorityQueue<Integer> absHeap = new PriorityQueue<>((o1, o2) -> Math.abs(o1) != Math.abs(o2) ?
      Integer.compare(Math.abs(o1), Math.abs(o2)) : Integer.compare(o1, o2));
    for (int i = 0; i < N; i++) {
      int x = sc.nextInt();
      if (x == 0) {
        if (absHeap.isEmpty()) System.out.println(0);
        else System.out.println(absHeap.poll());
      }
      else {
        absHeap.add(x);
      }
    }
  }
}
