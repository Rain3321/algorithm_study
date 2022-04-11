import java.util.*;
import java.util.stream.Collectors;

public class BJ13813 {
  private static final int MAX = 100000;
  private static final int INIT = -777;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();

    StringBuilder sb = new StringBuilder();
    if (n >= k) {
      sb.append(n - k).append("\n");
      for (int i = n; i >= k; i--) {
       sb.append(i).append(" ");
      }
    } else {
      int[] visited = new int[MAX + 1];
      Arrays.fill(visited, INIT);
      Queue<int[]> queue = new LinkedList<>();

      visited[n] = -1;
      queue.add(new int[]{n, 0});

      while (!queue.isEmpty()) {
        int[] cur = queue.poll();
        if (cur[0] == k) {
          sb.append(cur[1]).append("\n");
          sb.append(makeRoute(visited, cur[0]));
          break;
        }
        for (int next : new int[]{cur[0] - 1, cur[0] + 1, cur[0] * 2}) {
          if (next < 0 || next > MAX || visited[next] != INIT) { // 방문배열이 체크된 건 이미 앞에서 지나갔으니까 갈필요 없음
            continue;
          }
          queue.add(new int[]{next, cur[1] + 1});
          visited[next] = cur[0];
        }
      }
    }

    System.out.println(sb);
  }

  private static String makeRoute(int[] visited, int k) {
    int cur = k;
    List<Integer> route = new ArrayList<>();
    while (cur != -1) {
      route.add(cur);
      cur = visited[cur];
    }
    Collections.reverse(route);

    return route.stream().map(String::valueOf).collect(Collectors.joining(" "));
  }
}
