import java.util.*;


public class BJ21610 {
  private static int N;
  private static int M;
  private static int[][] arr;
  private static LinkedList<int[]> clouds = new LinkedList<>();
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    arr = new int[N + 1][N + 1];
    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        arr[i][j] = sc.nextInt();
      }
    }
    // 구름 만들기
    for (int i = 0; i < 2; i++) {
      for (int j = 1; j < 3; j++) {
        clouds.add(new int[] {N - i, j});
      }
    }

    // 무빙 시작
    for (int i = 0; i < M; i++) {
      int d = sc.nextInt();
      int s = sc.nextInt();

      moveClouds(d, s);
      raining();
      copyMagic();
      makeClouds();
    }

    System.out.println(calculate());

  }

  private static int calculate() {
    int cnt = 0;
    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        cnt += arr[i][j];
      }
    }
    return cnt;
  }

  private static void makeClouds() {
    boolean[][] existedClouds = new boolean[N + 1][N + 1];
    while (!clouds.isEmpty()) {
      int[] cloud = clouds.pop();
      existedClouds[cloud[0]][cloud[1]] = true;
    }

    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        if (arr[i][j] > 1 && !existedClouds[i][j]) {
          clouds.add(new int[]{i, j});
          arr[i][j] -= 2;
        }
      }
    }
  }

  // 대각선에 있는 바구니 체크해서 구름이 있는 위치에 물 추가
  private static void copyMagic() {
    for (int[] cloud : clouds) {
      int cnt = 0;
      for (int d = 2; d < 9; d+=2) { // 대각선만 체크
        int nx = cloud[0] + dx[d];
        int ny = cloud[1] + dy[d];
        if (nx > 0 && nx <= N && ny > 0 && ny <= N) {
          if (arr[nx][ny] > 0) {
            cnt++;
          }
        }
      }
      arr[cloud[0]][cloud[1]] += cnt;
    }
  }

  // 아아 비가 오네
  private static void raining() {
    for (int[] cloud : clouds) {
      arr[cloud[0]][cloud[1]]++;
    }
  }

  // 구름을 움직여 봅시다
  private static void moveClouds(int d, int s) {
    int mx = dx[d] * s;
    int my = dy[d] * s;

    int n = clouds.size();
    while (n-- > 0) {
      int[] cloud = clouds.pop();
      int nx = cloud[0] + mx;
      int ny = cloud[1] + my;

      while (nx < 1 || nx > N || ny < 1 || ny > N) {
        if (nx < 1) {
          nx += N;
        } else if (nx > N) {
          nx -= N;
        }
        if (ny < 1) {
          ny += N;
        } else if (ny > N) {
          ny -= N;
        }
      }
      clouds.add(new int[] {nx, ny});
    }
  }

  // 방향, 1부터 시작이라 0은 0,0 으로 제외
  private static final int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
  private static final int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

}
