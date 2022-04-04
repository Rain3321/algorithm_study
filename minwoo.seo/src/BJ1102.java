import java.util.*;

public class BJ1102 {
  private static int[][] dp;
  private static int n, p;
  private static int[][] cost;
  private static final int MAX = 1 << 16;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    cost = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        cost[i][j] = sc.nextInt();
        if (i == j) {
          cost[i][j] = Integer.MAX_VALUE;
        }
      }
    }
    String status = sc.next();
    int start = 0;
    int cnt = 0;
    for (int i = 0; i < status.length(); i++) {
      if (status.charAt(i) == 'Y') {
        start |= 1 << i;
        cnt++;
      }
    }

    p = sc.nextInt();
    dp = new int[n + 1][1 << n];
    for (int i = 0; i < n + 1; i++) {
      Arrays.fill(dp[i], -1);
    }

    int ans = dfs(cnt, start);


    if (ans == MAX) {
      System.out.println(-1);
    } else {
      System.out.println(ans);
    }
  }

  private static int dfs(int cnt, int status) {
    if (cnt >= p) { // 이미 최소 기준을 맞췄다면 0 반환
      return 0;
    }
    if (dp[cnt][status] != -1) {
      return dp[cnt][status];
    }

    dp[cnt][status] = MAX;

    for (int i = 0; i < n; i++) {
      if (getStatus(status, i)) { // i 번째 발전기가 켜져있는 상태에서
        for (int j = 0; j < n; j++) {
          // j 번째 발전기가 이미 켜져있으면 패스
          if (i != j && !getStatus(status, j)) {

            dp[cnt][status] = Math.min(dp[cnt][status], dfs(cnt + 1, turnOn(status, j)) + cost[i][j]);
          }
        }
      }
    }


    return dp[cnt][status];
  }

  //i 번째 발전소 시작
  private static int turnOn(int n, int i) {
    return n | (1 << i);
  }

  // i 번째 발전소가 켜져있는지 확인
  private static boolean getStatus(int n, int i) {
    return (n & (1 << i)) > 0;
  }
}
