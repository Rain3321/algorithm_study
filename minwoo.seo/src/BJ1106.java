import java.util.*;

public class BJ1106 {
  private static final int MAX = 100001; // 최대 1000명을 모아야 하고 1명당 비용이 100일 때 100000 + 1
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int goal = sc.nextInt();
    int N = sc.nextInt();

    // dp[i] = 사람을 최소 i 명 이상 모집할 때 드는 최소비용
    int[] dp = new int[goal + 1];

    Arrays.fill(dp, MAX);
    dp[0] = 0;
    
    for (int i = 0; i < N; i++) {
      int cost = sc.nextInt();
      int customer = sc.nextInt();

      for (int p = 1; p < goal + 1; p++) {
        for (int j = 1; j < customer + 1 && j <= p; j++) { // 기대고객수보다 많으면 i 명 이상이 안됨
          dp[p] = Math.min(dp[p], dp[p - j] + cost);
        }
      }
    }
    System.out.println(dp[goal]);
  }
}
