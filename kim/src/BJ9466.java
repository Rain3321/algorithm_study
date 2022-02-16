import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9466 {

    private static int[] students, currVisit;
    private static boolean[] visit;
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            students = new int[n + 1];
            currVisit = new int[n + 1];
            visit = new boolean[n + 1];
            for (int i = 1; i <= n; i++)
                students[i] = Integer.parseInt(st.nextToken());

            ans = 0;
            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    go(i, 1);
                }
            }
            System.out.println(ans);
        } // end TC
    }

    private static void go(int now, int cnt) {
        if (currVisit[now] != 0) {
            // 이번 사이클에서 방문했던 곳을 만났다면 사이클 종료
            ans += currVisit[now] - 1;
            return;
        }
        if (visit[now]) {
            // 이전 사이클 포함 이미 방문했었던 칸을 만난다.
            ans += cnt - 1;
            return;
        }
        if (students[now] == now) {
            // 혼자 할꺼면 이전것들은 나가리
            ans += cnt - 1;
            return;
        }
        visit[now] = true;
        currVisit[now] = cnt;
        go(students[now], cnt + 1);
        currVisit[now] = 0;
    }
}
