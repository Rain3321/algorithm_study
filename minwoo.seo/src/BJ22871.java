import java.util.*;

public class BJ22871 {
  private static int[] arr;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    arr = new int[N + 1];

    for (int i = 1; i < N + 1; i++) {
      arr[i] = sc.nextInt();
    }

    long[] dp = new long[N+1];
    Arrays.fill(dp, Long.MAX_VALUE);
    dp[0] = 0;
    dp[1] = 0;

    for (int start = 1; start < N + 1; start++) {
      for (int end = start + 1; end < N + 1; end++) {
        dp[end] = Math.min(dp[end], Math.max(dp[start], cal(start, end)));
      }
    }
    System.out.println(dp[N]);
  }
  private static long cal(int i, int j) {
    return (long) (j - i) * (1 + Math.abs(arr[i] - arr[j]));
  }
}
