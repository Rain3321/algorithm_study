import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ18427 {

  public static void main(String[] args) throws IOException {
    final int MOD = 10_007;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int H = Integer.parseInt(st.nextToken());
    
    ArrayList<Integer>[] blocks = new ArrayList[N + 1];

    for (int i = 0; i <= N; i++) {
      blocks[i] = new ArrayList<>();
    }
    
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());

      while (st.hasMoreTokens()) {
        blocks[i].add(Integer.parseInt(st.nextToken()));
      }
    } 
    // 초기화 끝

    int[][] dp = new int[N + 1][H + 1];

    // 모든 사람이 각각 쌓지 않는 경우는 1가지
    for (int i = 0; i <= N; i++) {
      dp[i][0] = 1;
    }
    
    // 풀이
    for (int i = 1; i <= N; i++) {
      for (int h = 1; h <= H; h++) {

        for (Integer height : blocks[i]) {
          if (h >= height) {
            dp[i][h] += dp[i - 1][h - height];
            dp[i][h] %= MOD;
          }
        }
        dp[i][h] += dp[i - 1][h];
        dp[i][h] %= MOD;
      }
    }

    System.out.println(dp[N][H]);
  }
}
