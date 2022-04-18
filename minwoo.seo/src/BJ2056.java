import java.util.*;

public class BJ2056 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] times = new int[n + 1];
    ArrayList<Integer>[] adjList = new ArrayList[n + 1];
    for (int i = 0; i < n + 1; i++) {
      adjList[i] = new ArrayList<>();
    }
    int[] counts = new int[n + 1];
    Queue<Integer> queue = new LinkedList<>();

    for (int i = 1; i <= n; i++) {
      times[i] = sc.nextInt();
      int count = sc.nextInt();

      for (int j = 0; j < count; j++) {
        adjList[sc.nextInt()].add(i);
        counts[i]++;
      }
      if (count == 0) {
        queue.add(i);
      }
    }

    int[] result = new int[n + 1];
    System.arraycopy(times, 1, result, 1, n);
    // 배열 초기화. 각 작업 별 걸리는 시간을 기본으로 세팅

    while (!queue.isEmpty()) {
      int cur = queue.poll();

      for (int next : adjList[cur]) {
        counts[next]--;
        result[next] = Math.max(result[next], result[cur] + times[next]);

        if (counts[next] == 0) {
          queue.add(next);
        }
      }
    }
    Arrays.sort(result);

    System.out.println(result[n]);
  }
}
