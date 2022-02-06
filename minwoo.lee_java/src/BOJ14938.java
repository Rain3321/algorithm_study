import java.io.*;
import java.util.StringTokenizer;

public class BOJ14938 {
    static int n, m, r, item;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            graph[from][to] = value;
            graph[to][from] = value;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int from = 1; from <= n; from++) {
            dp[from][from] = 0;
            for (int to = 1; to <= n; to++) {
                if (from != to && graph[from][to] == 0) {
                    dp[from][to] = 100000;
                } else {
                    dp[from][to] = graph[from][to];
                }
            }
        }
        for (int k = 1; k <= n; k++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    dp[from][to] = Math.min(dp[from][to], dp[from][k] + dp[k][to]);
                }
            }
        }
        for (int start = 1; start <= n; start++) {
            int temp = arr[start];
            for (int to = 1; to <= n; to++) {
                if (start != to && dp[start][to] <= m) {
                    temp += arr[to];
                }
            }
            item = Math.max(temp, item);
        }
        System.out.println(item);
    }
}
