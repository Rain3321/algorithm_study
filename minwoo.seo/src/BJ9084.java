import java.util.Scanner;

public class BJ9084 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    int[] coins, dp;
    int N, money;
    while (T-- > 0) {
      N = sc.nextInt();
      coins = new int[N];
      for (int i = 0; i < N; i++) {
        coins[i] = sc.nextInt();
      }
      money = sc.nextInt();
      dp = new int[10001];
      dp[0] = 1; // 0원을 만드는 방법은 1가지
      for (int coin: coins) {
        for (int i = 1; i <= money; i++) {
          if(i - coin >= 0) {
            dp[i] = dp[i] + dp[i - coin];
          }
        }
      }
      System.out.println(dp[money]);
    }
  }
}
