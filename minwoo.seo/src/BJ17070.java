import java.util.*;

public class BJ17070 {
  private static int n;
  private static int[][] arr;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    arr = new int[n + 1][n + 1];

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        arr[i][j] = sc.nextInt();
      }
    }

    solve(new Pipe(1, 2, 0));

    System.out.println(ans);
  }

  private static int ans = 0;
  private final static int[] dx = {0, 1, 1}; // 가로, 대각선, 세로
  private final static int[] dy = {1, 1, 0};
  private static void solve(Pipe pipe) {
    if (pipe.x == n && pipe.y == n) {
      ans++;
      return;
    }

    if (pipe.status == 0) { // 현재 파이프의 상태가 가로일 때
      for (int d = 0; d < 2; d++) {
        int nx = pipe.x + dx[d];
        int ny = pipe.y + dy[d];
        if (safe(nx, ny) && check(d, nx, ny)) {
          solve(new Pipe(nx, ny, d));
        }
      }
    } else if (pipe.status == 2) { // 현재 파이프의 상태가 세로일 때
      for (int d = 1; d < 3; d++) {
        int nx = pipe.x + dx[d];
        int ny = pipe.y + dy[d];
        if (safe(nx, ny) && check(d, nx, ny)) {
          solve(new Pipe(nx, ny, d));
        }
      }
    } else { // 현재 파이프의 상태가 대각선일 때
      for (int d = 0; d < 3; d++) {
        int nx = pipe.x + dx[d];
        int ny = pipe.y + dy[d];
        if (safe(nx, ny) && check(d, nx, ny)) {
          solve(new Pipe(nx, ny, d));
        }
      }
    }
  }

  // 갈 수 있는지 체크
  private static boolean check(int d, int x, int y) {
    if (d == 1) { // 대각선이면 3개 지점이 벽이 아닌지 체크해야함
      return arr[x - 1][y] != 1 && arr[x][y - 1] != 1 && arr[x][y] != 1;
    } else { // 가로 세로는 한개점만 체크하면 됨
      return arr[x][y] != 1;
    }
  }

  // 밖으로 안나가는지 체크
  private static boolean safe(int x, int y) {
    return x > 0 && x <= n && y > 0 && y <= n;

  }

  private static class Pipe {
    int x;
    int y;
    int status; // 0: 가로, 1: 세로, 2: 대각선

    public Pipe(int x, int y, int status) {
      this.x = x;
      this.y = y;
      this.status = status;
    }
  }
}
