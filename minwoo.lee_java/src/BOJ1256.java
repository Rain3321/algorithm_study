import java.util.Scanner;

public class BOJ1256 {
    static int[][] dp;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N, M, K;
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        sb = new StringBuilder();
        dp = new int[N + 1][M + 1];
        if (getOrderCnt(N, M) < K) {
            System.out.println(-1);
        } else {
            getKthStr(N, M, K);
            System.out.println(sb.toString());
        }
    }


    private static void getKthStr(int n, int m, int k) {
        // z가 없으면 a로만 이루어진다.
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                sb.append('a');
            }
            return;
        }
        // a가 없으면 a로만 이루어진다.
        if (n == 0) {
            for (int i = 0; i < m; i++) {
                sb.append('z');
            }
            return;
        }

        // a가 앞에 왔을 때의 순서 개수
        int orderCnt = dp[n - 1][m];
        if (k <= orderCnt) {
            sb.append('a');
            getKthStr(n - 1, m, k);
        } else {
            sb.append('z');
            getKthStr(n, m - 1, k-orderCnt);
        }

    }

    private static int getOrderCnt(int n, int m) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 1;
        }
        if (dp[n][m] != 0) {
            return dp[n][m];
        }
        // 점화식 dp[N][M] = dp[N-1][M] + dp[N][M-1]
        return dp[n][m] = Math.min(getOrderCnt(n - 1, m) + getOrderCnt(n, m - 1), 1_000_000_001);
    }
}
