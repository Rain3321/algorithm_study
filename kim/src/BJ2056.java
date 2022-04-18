import java.io.*;
import java.util.*;

public class BJ2056 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = parse(br.readLine());
        List<Integer>[] adj = new ArrayList[n + 1];
        int[] preCnt = new int[n + 1];
        int[] time = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        Queue<Integer> q = new LinkedList<>();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = parse(st.nextToken());
            int tmp = parse(st.nextToken());
            if (tmp == 0) {
                q.offer(i);
                dp[i] = time[i];
            }
            preCnt[i] = tmp;
            while (tmp-- > 0) {
                adj[parse(st.nextToken())].add(i);
            }
        } // end Input

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : adj[now]) {
                preCnt[next]--;
                if (preCnt[next] == 0) {
                    q.offer(next);
                }
                dp[next] = Math.max(dp[next], dp[now] + time[next]);
            }
        }

        int ans = 0;
        for (int t : dp) {
            ans = Math.max(ans, t);
        }

        System.out.println(ans);

    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
