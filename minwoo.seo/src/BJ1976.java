import java.util.*;

public class BJ1976 {
  private static int[] parents;
  private static int[][] adj;
  private static int n, m;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();

    adj = new int[n + 1][n + 1];
    parents = new int[n + 1];

    for (int i = 0; i < n + 1; i++) {
      parents[i] = i;
    }

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        adj[i][j] = sc.nextInt();
      }
    }

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        if (adj[i][j] != 0) {
          if (parents[i] != parents[j]) {
            union(i, j);
          }
        }
      }
    }
    int start = sc.nextInt();
    for (int i = 1; i < m; i++) {
      int next = sc.nextInt();
      if (parents[start] != parents[next]) {
        System.out.println("NO");
        return;
      }
      start = next;
    }
    System.out.println("YES");

  }

  private static void union(int x, int y) {
    x = find(x);
    y = find(y);

    if (x < y) {
      parents[y] = x;
    } else {
      parents[x] = y;
    }
  }

  private static int find(int i) {
    if (i == parents[i]) {
      return i;
    }
    return parents[i] = find(parents[i]);
  }
}
