import java.util.*;
import java.io.*;

public class BOJ1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] dp = new int[C + 1];
        Arrays.fill(dp, 100_001);
        dp[0] = 0;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int person = Integer.parseInt(st.nextToken());
            for (int p = 1; p < dp.length; p++) {
                if (person > p) {
                    dp[p] = Math.min(dp[p], cost);
                } else {
                    dp[p] = Math.min(dp[p], dp[p - person] + cost);
                }
            }
        }
        System.out.println(dp[C]);
    }
}
