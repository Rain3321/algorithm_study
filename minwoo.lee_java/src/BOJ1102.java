import java.util.*;
import java.io.*;

public class BOJ1102 {
    static int[][] dp, cost;
    static int N, P;
    static final int maxValue = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        // 발전소 재시작 비용 저장
        cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 발전소의 개수는 최대 16개이므로 0번부터 15번까지의 발전소가 있으면 1<<16-1개 이다.
        dp = new int[N + 1][1 << 16];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        String powerInfo = br.readLine();
        P = Integer.parseInt(br.readLine());
        // 현재 발전소의 상태
        int curStatus = 0;
        // 켜져있는 발전소 개수
        int cnt = 0;
        for (int i = 0; i < powerInfo.length(); i++) {
            if (powerInfo.charAt(i) == 'Y') {
                curStatus |= 1 << i;
                cnt++;
            }
        }
        int minCost = solution(cnt, curStatus);
        System.out.println((minCost != maxValue) ? minCost : -1);
    }

    private static int solution(int cnt, int curStatus) {
        if (cnt >= P) {
            return 0;
        }
        if (dp[cnt][curStatus] != -1) {
            return dp[cnt][curStatus];
        }
        dp[cnt][curStatus] = maxValue;
        for (int i = 0; i < N; i++) {
            // i번째 발전소가 켜져있어야 다른 발전소를 킬 수 있다.
            if ((curStatus & (1 << i)) > 0) {
                for (int j = 0; j < N; j++) {
                    // 같은 번호 발전소인 경우와 켜져있는 발전소는 제외
                    if (i == j || (curStatus & (1 << j)) > 0) {
                        continue;
                    }
                    //최소값 구하기
                    dp[cnt][curStatus] = Math.min(dp[cnt][curStatus], solution(cnt + 1, curStatus | (1 << j)) + cost[i][j]);
                }
            }
        }
        return dp[cnt][curStatus];
    }
}
