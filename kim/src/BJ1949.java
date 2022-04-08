import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1949 {

    private static ArrayList<Integer>[] adj;
    private static int[][] dp;
    private static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = parse(br.readLine()); // 마을 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        adj = new ArrayList[n + 1];
        visit = new boolean[n + 1];
        dp = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = parse(st.nextToken());
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = parse(st.nextToken());
            int v2 = parse(st.nextToken());
            adj[v1].add(v2);
            adj[v2].add(v1);
        }

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int node) {
        visit[node] = true;
        for (int next : adj[node]) {
            if (visit[next])
                continue;
            dfs(next);
            dp[node][0] += Math.max(dp[next][0], dp[next][1]);
            dp[node][1] += dp[next][0];
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
