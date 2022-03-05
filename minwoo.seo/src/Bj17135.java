import java.util.*;

public class Bj17135 {
  private static int N, M, D, ans;
  private static int[][] map;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    D = sc.nextInt();
    map = new int[N + 1][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        map[i][j] = sc.nextInt();
      }
    }

    combination(0, 0, new int[3]);
    System.out.println(ans);
  }
  // 1. 조합으로 궁수 3명 위치 경우의 수 판단
  // 2. 각 궁수별로 최소 거리의 적 마킹
  // 3. 마킹된 적 제거
  // 4. 적 한줄 내려옴
  private static void combination(int i, int idx, int[] arrows) {
    if (i == 3) {
      int[][] tempMap = new int[N + 1][M];
      for (int j = 0; j < N + 1; j++) {
        System.arraycopy(map[j], 0, tempMap[j], 0, M);
      }
      int cnt = gameStart(arrows, tempMap);
      ans = Math.max(ans, cnt);
      return;
    }

    for (int j = idx; j < M; j++) {
      arrows[i] = j;
      combination(i + 1, i + 1, arrows);
    }
  }

  private static int gameStart(int[] arrows, int[][] map) {
    int cnt = 0;
    for (int idx = 0; idx < N; idx++) {

      List<int[]> enemies = new ArrayList<>();
      for (int arrow : arrows) {
        int[] enemy = findEnemy(arrow, map);
        if (enemy == null) {
          continue;
        }
        enemies.add(enemy);
      }

      for (int[] enemy : enemies) {
        if (map[enemy[0]][enemy[1]] == 0) { // 이미 0이면 죽은 위치
          continue;
        }
        map[enemy[0]][enemy[1]] = 0;
        cnt++;
      }
      // 한칸씩 내려 주기
      for (int i = N; i >= 0; i--) {
        for (int j = 0; j < M; j++) {
          if (i == 0) {
            map[i][j] = 0;
          } else {
            map[i][j] = map[i-1][j];
          }
        }
      }
    }
    return cnt;
  }

  private static int[] findEnemy(int arrow, int[][] map) {
    int x = -1;
    int y = -1;
    int min = N + M + 2;
    for (int i = N - 1; i >= 0; i--) {
      for (int j = 0; j < M; j++) {
        if(map[i][j] == 1) {
          int dis = Math.abs(N - i) + Math.abs(arrow - j);
          if (dis <= D) {
            if (min > dis) {
              min = dis;
              x = i;
              y = j;
            } else if (min == dis) {
              if (y > j) {
                x = i;
                y = j;
              }
            }
          }
        }
      }
    }

    // 거리 안에 적이 없을 경우
    if (x == -1 && y == -1) {
      return null;
    }

    return new int[] {x, y};
  }

}
