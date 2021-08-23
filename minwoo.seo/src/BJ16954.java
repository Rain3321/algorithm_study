import java.io.*;
import java.util.*;

public class BJ16954 {
  static boolean[][][] map;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    map = new boolean[8][8][8];
    // 시작 상태를 0번 인덱스에 저장
    // 벽이 있으면 true, 벽이 없으면 false
    for (int i = 0; i < 8; i++) {
      String s = br.readLine();
      for (int j = 0; j < 8; j++) {
        char c = s.charAt(j);
        if(c == '#') map[0][i][j] = true;
      }
    }
    // 1~7초까지 각 초마다 벽이 어느 위치에 있을지 미리 만들어둠.
    for (int time = 0; time < 8; time++) {
      for (int i = 0; i < 7; i++) {
        for (int j = 0; j < 8; j++) {
          if(map[time][i][j]) {
            map[time + 1][i + 1][j] = true;
          }
        }
      }
    }
    System.out.println(sol());
  }

  static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dy = {0, 0, 1, 1, 1, 0, -1, -1, -1};

  private static int sol() {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[] {7, 0, 0});
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      int time = cur[2];
      // 7초까지 살아있다면 더이상 위에 벽이 없음 -> 무조건 도착할 수 있음.
      if(time+1 == 8) return 1;
      for (int d = 0; d < 9; d++) {
        int nx = cur[0] + dx[d];
        int ny = cur[1] + dy[d];
        // 8 * 8 체스판을 벗어나거나 현재 시간의 위치에 벽이 있거나 다음 시간에 벽이 있다면 다음 체크
        if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8
            || map[time][nx][ny] || map[time+1][nx][ny]) continue;
        // 현재 시간에도 이동할 수 있고 다음 시간에도 벽이 오지 않는다면
        queue.add(new int[]{nx, ny, time+1});
      }
    }
    return 0;
  }
}
