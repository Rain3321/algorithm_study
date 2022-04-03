import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1256 {
    private static int[][] dp;
    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parse(st.nextToken()); // a개수
        m = parse(st.nextToken()); // z개수
        int k = parse(st.nextToken());

        fillArr();

        if (k > dp[n][m]) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        // k번째 수 구하기
        while (n > 0 || m > 0) {
            int a = (n <= 0) ? 0 : dp[n - 1][m];

            if (k <= a) {
                // a를 이어붙이면 됨
                n--;
                sb.append("a");
            } else {
                m--;
                k -= a;
                sb.append("z");
            }
        }
        System.out.println(sb.toString());
    }

    private static void fillArr() {
        dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + dp[i][j - 1], 1000000001);
            }
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
