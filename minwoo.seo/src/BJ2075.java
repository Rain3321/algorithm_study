import java.util.*;

/**
 * N 번째 큰 수
 * https://www.acmicpc.net/problem/2075
 *
 */
public class BJ2075 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i = 0; i < n * n; i++) {
      pq.offer(sc.nextInt());
      if (pq.size() > n) {
        pq.poll();
      }
    }
    System.out.println(pq.poll());

  }
}
