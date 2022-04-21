import sun.security.provider.MD2;

import java.util.*;
import java.io.*;

public class BOJ18427 {
    static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][H + 1];

        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int blockHeight = Integer.parseInt(st.nextToken());
                for (int bh = blockHeight; bh <= H; bh++) {
                    dp[i][bh] = (dp[i][bh] + dp[i - 1][bh - blockHeight]) % MOD;
                }
            }
            for (int h = 0; h <= H; h++) {
                dp[i][h] = (dp[i][h] + dp[i - 1][h]) % MOD;
            }
        }
        System.out.println(dp[N][H]);
    }
}
