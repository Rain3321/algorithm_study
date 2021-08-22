import java.util.Scanner;

public class BJ9663 {
  static int N,answer;
  static int[] chess;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();

    // chess 배열은 col[행] = 열
    // chess[1] = 1 => 1행 1열에 퀸이 1개 있음
    for (int i = 1; i <= N; i++) {
      chess = new int[N+1];
      chess[1] = i;
      dfs(1);
    }
  System.out.println(answer);
  }
  // 같은 행에는 퀸을 놓지 않은 것이 깔려있음.
  private static void dfs(int row) {
    if(row == N) {
      answer++;
      return;
    }
    for (int i = 1; i <= N; i++) {
      chess[row+1] = i;
      if(isPossible(row+1)) {
        dfs(row+1);
      }else {
        chess[row+1] = 0;
      }
    }

  }

  private static boolean isPossible(int r) {
    for (int i = 1; i < r; i++) {
      if (chess[i] == chess[r]) { // 같은 열에 위치하는 경우
        return false;
      }

      // 대각선 체크 -> 기울기가 1 혹은 -1 이어야함 => 가로의 거리와 세로의 거리가 동일하면 대각선
      if (Math.abs(i - r) == Math.abs(chess[i] - chess[r])) {
        return false;
      }
    }
    return true;
  }
}
