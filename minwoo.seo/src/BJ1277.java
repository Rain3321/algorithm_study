import java.util.*;

public class BJ1277 {
  private static int N, W;
  private static double M;
  private final static double MAX = 200001;
  private static boolean[][] connected;
  private static Node[] plants;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    W = sc.nextInt();
    M = sc.nextDouble();

    plants = new Node[N + 1];
    connected = new boolean[N + 1][N + 1];
    for (int i = 1; i < N + 1; i++) {
      plants[i] = new Node(sc.nextInt(), sc.nextInt());
    }

    for (int i = 0; i < W; i++) {
      int p1 = sc.nextInt();
      int p2 = sc.nextInt();
      connected[p1][p2] = true;
      connected[p2][p1] = true;
    }

    //dijstra
    double[] distance = new double[N + 1];
    boolean[] visited = new boolean[N + 1];
    Arrays.fill(distance, MAX);
    distance[1] = 0;

    for (int i = 0; i < N; i++) {
      double minDistance = MAX;
      int cur = 0;
      for (int j = 1; j < N + 1; j++) {
        if (!visited[j] && minDistance >= distance[j]) {
          minDistance = distance[j];
          cur = j;
        }
      }
      if (cur == N) break;
      visited[cur] = true;
      for (int j = 1; j < N + 1; j++) {
        if (j == cur) {
          continue;
        }
        double dis = getDistance(cur, j);
        if (dis <= M && distance[j] > distance[cur] + dis) {
          distance[j] = distance[cur] + dis;
        }
      }
    }

    System.out.println((int) (1000 * distance[N]));

  }

  private static double getDistance(int cur, int next) {
    if (connected[cur][next]) {
      return 0.0;
    }

    return Math.sqrt(
      Math.pow(plants[cur].x - plants[next].x, 2) + Math.pow(plants[cur].y - plants[next].y, 2)
    );
  }

  private static class Node {

    int x;
    int y;

    public Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
