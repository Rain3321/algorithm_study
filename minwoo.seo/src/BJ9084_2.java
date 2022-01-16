import java.util.*;

public class BJ9084_2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();

    while (T-- > 0) {
      int N = sc.nextInt();
      int[] coins = new int[N];
      for (int i = 0; i < N; i++) {
        coins[i] = sc.nextInt();
      }
      int target = sc.nextInt();
      int[] dp = new int[target+1];
      dp[0] = 1;
      // 동전 순서대로 해야 중복될 수 있는 경우의 수를 방지함
      for (int coin : coins) {
        for (int i = 1; i <= target; i++) {
          if(i - coin >= 0) {
            dp[i] += dp[i - coin];
          }
        }
      }
      System.out.println(dp[target]);
    }
    sc.close();
  }
}
