import java.util.*;
import java.io.*;

public class BOJ1949 {
    static int N;
    static ArrayList<Integer>[] adjList;
    static int[][] dp;
    static int[] person;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        // dp[i][0] : i번째 마을이 우수마을이 아닌 경우, dp[i][1] : i번째 마을이 우수마을인 경우
        dp = new int[N + 1][2];
        // 마을의 인구정보를 담음
        person = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            person[i] = Integer.parseInt(st.nextToken());
        }

        // 인접리스트 생성 : 마을간의 연결정보를 담음
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int town1 = Integer.parseInt(st.nextToken());
            int town2 = Integer.parseInt(st.nextToken());
            adjList[town1].add(town2);
            adjList[town2].add(town1);
        }
        bestTown(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void bestTown(int townNum, int parent) {
        for (int child : adjList[townNum]) {
            if (parent != child) {
                bestTown(child, townNum);
                dp[townNum][1] += dp[child][0];
                dp[townNum][0] += Math.max(dp[child][0], dp[child][1]);
            }
        }
        dp[townNum][1] += person[townNum];
    }
}
