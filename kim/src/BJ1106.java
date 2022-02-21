import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1106 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = parse(st.nextToken());
        int n = parse(st.nextToken());

        // n원으로 유치 가능한 최대 고객 수
        int[] dp = new int[100001];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = parse(st.nextToken());
            int customer = parse(st.nextToken());
            for (int j = cost; j < dp.length; j++) {
                dp[j] = Math.max(dp[j], dp[j - cost] + customer);
            }
        }

        for (int i = 1; i < dp.length; i++) {
            if (dp[i] >= c) {
                System.out.println(i);
                return;
            }
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
