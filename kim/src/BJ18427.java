import java.io.*;
import java.util.*;

public class BJ18427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = parse(st.nextToken());
        int m = parse(st.nextToken());
        int h = parse(st.nextToken());
        int[] dp = new int[h + 1];

        dp[0] = 1;

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[m];
            int idx = 0;
            while (st.hasMoreTokens()) {
                arr[idx++] = parse(st.nextToken());
            }

            Arrays.sort(arr);
            for (int i = h; i >= 0; i--) {
                for (int j = m - idx; j < m; j++) {
                    if (i - arr[j] >= 0) {
                        dp[i] = (dp[i] + dp[i - arr[j]]) % 10007;
                    }
                }
            }
        }

        System.out.println(dp[h]);
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
