import java.util.*;

public class BJ4386 {
  private static Star[] stars;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    stars = new Star[n];

    for (int i = 0; i < n; i++) {
      stars[i] = new Star(sc.nextDouble(), sc.nextDouble());
    }

    PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(i -> i[1]));
    pq.add(new double[]{0, 0});
    boolean[] visited = new boolean[n];
    int cnt = 0;
    double ans = 0.0;
    while (!pq.isEmpty()) {
      double[] s = pq.poll();
      int cur = (int) s[0];
      double length = s[1];

      if (visited[cur]) {
        continue;
      }

      if (cnt == n) {
        break;
      }

      visited[cur] = true;
      ans += length;

      for (int next = 0; next < n; next++) {
        if (!visited[next]) {
          pq.add(new double[]{next, getDistance(cur, next)});
        }
      }
      
      cnt++;
    }
    System.out.format("%.2f", ans);
  }

  private static double getDistance(int cur, int next) {
    Star s1 = stars[cur];
    Star s2 = stars[next];
    return Math.sqrt(Math.pow(s1.x - s2.x, 2) + Math.pow(s1.y - s2.y, 2));
  }

  private static class Star {
    double x;
    double y;

    public Star(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }
}
