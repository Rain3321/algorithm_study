import java.util.*;
import java.io.*;

public class BOJ17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][][] dp = new int[N][N][3];
        dp[0][1][0] = 1;
        for (int j = 2; j < N; j++) {
            if (map[0][j] == 0) {
                dp[0][j][0] = dp[0][j - 1][0];
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 2; j < N; j++) {
                // 대각선
                if (map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) {
                    dp[i][j][2] = Math.max(dp[i][j][2], dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2]);
                }
                if (map[i][j] == 0) {
                    // 가로
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i][j - 1][0] + dp[i][j - 1][2]);
                    // 세로
                    dp[i][j][1] = Math.max(dp[i][j][1], dp[i - 1][j][1] + dp[i - 1][j][2]);
                }
            }
        }
        System.out.println((dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]));
    }
}
