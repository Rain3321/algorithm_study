import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ17070 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++)
            map[i] = br.readLine().replaceAll(" ", "").toCharArray();

        // 0: 가로, 1: 대각선, 2: 세로
        int[][][] dp = new int[n][n][3];
        dp[0][1][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 가로로 가기
                if (j + 1 < n && map[i][j + 1] != '1')
                    dp[i][j + 1][0] += dp[i][j][0] + dp[i][j][1];
                // 대각선
                if (i + 1 < n && j + 1 < n && map[i + 1][j + 1] != '1' && map[i][j + 1] != '1' && map[i + 1][j] != '1')
                    dp[i + 1][j + 1][1] += dp[i][j][0] + dp[i][j][1] + dp[i][j][2];
                // 세로
                if (i + 1 < n && map[i + 1][j] != '1')
                    dp[i + 1][j][2] += dp[i][j][1] + dp[i][j][2];
            }
        }

        System.out.println(dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);
    }
}
