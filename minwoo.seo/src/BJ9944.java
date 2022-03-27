import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9944 {
  private static int N, M;
  private static char[][] board;
  private static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;
    int idx = 1;
    while ((s = br.readLine()) == null) {
      StringTokenizer st = new StringTokenizer(s);
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      int count = N * M;
      board = new char[N][M];
      visited = new boolean[N][M];
      for (int i = 0; i < N; i++) {
        board[i] = br.readLine().toCharArray();
        for (int j = 0; j < M; j++) {
          if (board[i][j] == '*') {
            count--;
          }
        }
      }
      // 초기화 끝

      int ans = Integer.MAX_VALUE;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (board[i][j] == '*') {
            continue;
          }
          visited[i][j] = true;
          int level = move(i, j, count - 1);
          ans = Math.min(ans, level);
          visited[i][j] = false;
        }
      }
      if (ans == Integer.MAX_VALUE) ans = -1;
      System.out.format("Case %d: %d\n", idx++, ans);
    }
  }

  private static final int[] dx = {-1, 0, 1, 0};
  private static final int[] dy = {0, 1, 0, -1};

  private static int move(int i, int j, int cnt) {
    if (cnt == 0) {
      return 0;
    }

    int now = Integer.MAX_VALUE;

    for (int d = 0; d < 4; d++) {
      int nx = i + dx[d];
      int ny = j + dy[d];

      while (!check(nx, ny) && board[nx][ny] == '.' && !visited[nx][ny]) {
        visited[nx][ny] = true;
        cnt--;
        nx += dx[d];
        ny += dy[d];
      }

      nx -= dx[d];
      ny -= dy[d];

      if (!(nx == i && ny == j)) {
        int next = move(nx, ny, cnt);
        if (next != Integer.MAX_VALUE) {
          now = Math.min(now, next + 1);
        }
      }


      while (!(nx == i && ny == j)) {
        visited[nx][ny] = false;
        cnt++;
        nx -= dx[d];
        ny -= dy[d];
      }
    }

    return now;
  }

  private static boolean check(int x, int y) {
    return x < 0 || x >= N || y < 0 || y >= M;
  }
}

