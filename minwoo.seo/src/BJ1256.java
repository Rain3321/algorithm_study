import java.util.*;

public class BJ1256 {
  private static int[][] dp;
  private static final int MAX = 1000000000;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int k = sc.nextInt();

    dp = new int[n + 1][m + 1];

    int count = fill(n, m);
    if (count < k) {
      System.out.println(-1);
    } else {
      makeString(n, m, k);
    }
  }

  private static void makeString(int n, int m, int k) {
    StringBuilder sb = new StringBuilder();
    while (n != 0 && m != 0) {
      int num = dp[n -1][m];
      if (num < k) {
        sb.append("z");
        k -= num;
        m -= 1;
      } else {
        sb.append("a");
        n -= 1;
      }
    }

    if (n == 0) {
      while (m-- > 0) {
        sb.append("z");
      }
    } else {
      while (n-- > 0) {
        sb.append("a");
      }
    }

    System.out.println(sb);

  }

  private static int fill(int n, int m) {
    if (n == 0 || m == 0) return dp[n][m] = 1;
    if (dp[n][m] != 0) return dp[n][m];

    return dp[n][m] = Math.min(fill(n - 1, m) + fill(n, m - 1), MAX);
  }
}
