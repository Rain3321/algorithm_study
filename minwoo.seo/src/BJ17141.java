import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ17141 {
  private final static int[] dx = {-1, 0, 1, 0};
  private final static int[] dy = {0, 1, 0, -1};

  private static int N, M, ans = Integer.MAX_VALUE;
  private static List<int[]> walls;
  private static List<int[]> canBeVirus;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = parse(st.nextToken());
    M = parse(st.nextToken());
    canBeVirus = new ArrayList<>();
    walls = new ArrayList<>();
    // 초기화
    int temp;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        temp = parse(st.nextToken());
        // 바이러스 자리일때
        if (temp == 2) {
          canBeVirus.add(new int[]{i, j});
        } else if (temp == 1) {
          walls.add(new int[]{i, j});
        }
      }
    }

    combination(0, 0, new Stack<>());
    System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
  }

  // 1. 바이러스 M 개 위치 뽑기 - Combination
  // 2. 뽑은 위치에 바이러스 놓고 BFS 돌리기
  // 3. 값 비교
  private static void combination(int i, int n, Stack<int[]> virus) {
    if (n <= canBeVirus.size()) {
      if (i == M) {
        int time = BFS(virus);
        ans = Math.min(ans, time);
      } else if (n < canBeVirus.size()) {
        virus.push(canBeVirus.get(n));
        combination(i + 1, n + 1, virus);
        virus.pop();
        combination(i, n + 1, virus);
      }
    }
  }

  private static int BFS(Stack<int[]> virus) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[N][N];

    // 초기화
    for (int[] v : virus) {
      queue.add(new int[]{v[0], v[1], 0});
      visited[v[0]][v[1]] = true;
    }

    for (int[] wall : walls) {
      visited[wall[0]][wall[1]] = true;
    }

    // BFS 시작
    int timer = 0;
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      timer = Math.max(timer, cur[2]);

      for (int d = 0; d < 4; d++) {
        int nx = cur[0] + dx[d];
        int ny = cur[1] + dy[d];
        if (check(nx, ny) && !visited[nx][ny]) {
          queue.add(new int[]{nx, ny, cur[2] + 1});
          visited[nx][ny] = true;
        }
      }
    }

    // 모든 점 갔는지 확인
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (!visited[i][j]) {
          return Integer.MAX_VALUE;
        }
      }
    }
    return timer;
  }

  private static boolean check(int x, int y) {
    return x >= 0 && x < N && y >= 0 && y < N;
  }

  private static int parse(String s) {
    return Integer.parseInt(s);
  }
}
