import java.util.Scanner;

public class BJ15486 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] dp = new int[N+1];
    int[] T = new int[N+1];
    int[] P = new int[N+1];

    for (int i = 0; i < N; i++) {
      T[i] = sc.nextInt();
      P[i] = sc.nextInt();
    }

    for (int i = 0; i < N; i++) {
      // 현재 날에서 일을 했을 때 다음 일을 할 수 있는 날 얼마를 벌 수 있나
      // T와 P의 인덱스가 0부터 시작하는 이유는 1일차에 일해서 돈을 쌓아야하기 때문
      // -> dp[i]: i번째 날에 벌 수 있는 최대 금액
      if(i + T[i] <= N) {
        dp[i + T[i]] = Math.max(dp[i+T[i]], dp[i] + P[i]);
      }
      dp[i+1] = Math.max(dp[i], dp[i+1]);
    }
    System.out.println(dp[N]);
  }
}
