import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ22944 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int hp = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());

    int[][] map = new int[n][n];

    Queue<Run> queue = new LinkedList<>();
    int[][] visited = new int[n][n];

    for (int i = 0; i < n; i++) {
      String line = br.readLine();
      for (int j = 0; j < n; j++) {
        switch (line.charAt(j)) {
          case 'S':
            queue.offer(new Run(i, j, hp, 0, 0));
            visited[i][j] = hp;
            break;
          case 'U':
            map[i][j] = 1;
            break;
          case 'E':
            map[i][j] = 2;
            break;
        }
      }
    }
    // 초기화 끝

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    // bfs 시작
    while (!queue.isEmpty()) {
      Run cur = queue.poll();

      for (int d = 0; d < 4; d++) {
        int nx = cur.x + dx[d];
        int ny = cur.y + dy[d];

        if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
          continue;
        }

        if (map[nx][ny] == 2) {
          System.out.println(cur.dis + 1);
          return;
        }

        int u = cur.um;
        int h = cur.hp;

        if (map[nx][ny] == 1) { // 우산이 있다면 우산을 먼저 집는다
          u = D;
        }

        if (u > 0) {
          u--;
        } else {
          h--;
        }

        // 사망
        if (h == 0) {
          continue;
        }

        if (visited[nx][ny] < u + h) {
          queue.offer(new Run(nx,ny, h, u, cur.dis + 1));
          visited[nx][ny] = u + h;
        }
      }
    }

    System.out.println(-1);
  }

  private static class Run {
    int x;
    int y;
    int hp;
    int um;
    int dis;

    public Run(int x, int y, int hp, int um, int dis) {
      this.x = x;
      this.y = y;
      this.hp = hp;
      this.um = um;
      this.dis = dis;
    }
  }
}