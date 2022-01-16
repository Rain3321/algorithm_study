import java.util.*;
import java.io.*;

public class BJ2206 {
  private static int N;
  private static int M;
  private static int[][] arr;
  private static final int[] dx = {-1, 0, 1, 0};
  private static final int[] dy = {0, 1, 0, -1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = parse(st.nextToken());
    M = parse(st.nextToken());
    arr = new int[N][M];
    for (int i = 0; i < N; i++) {
      String s = br.readLine();
      for (int j = 0; j < M; j++) {
        arr[i][j] = parse(s.substring(j, j+1));
      }
    }

    System.out.println(BFS());


    br.close();
  }

  private static int BFS() {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][][] visited = new boolean[N][M][2];
    visited[0][0][0] = true; // 시작점
    queue.add(new int[] {0, 0, 1, 0});

    while (!queue.isEmpty()) {
      int[] now = queue.poll();
      if (now[0] == N-1 && now[1] == M-1) {
        return now[2];
      }
      for (int d = 0; d < 4; d++) {
        int nx = now[0] + dx[d];
        int ny = now[1] + dy[d];
        // 판에서 벗어나지 않고 방문하지 않은 경우
        if (check(nx, ny) && !visited[nx][ny][now[3]]) {
          if (arr[nx][ny] == 0) {
            queue.add(new int[] {nx,ny, now[2] + 1, now[3]});
            visited[nx][ny][now[3]] = true;
          } else {
            if (now[3] == 0) {
              queue.add(new int[] {nx,ny, now[2] + 1, 1});
              visited[nx][ny][1] = true;
            }
          }
        }
      }
    }
    return -1;
  }

  private static int parse(String n) {
    return Integer.parseInt(n);
  }

  private static boolean check(int x, int y) {
    return x >= 0 && x < N && y >= 0 && y < M;
  }
}
