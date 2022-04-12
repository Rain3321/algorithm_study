import java.io.*;
import java.util.*;

public class BOJ1976 {
    static int N, M;
    static int[][] map;
    static int[] parent, plan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        M = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        parent = new int[N + 1];
        makeSet();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] != 0) {
                    if (find(i) != find(j)) {
                        union(i, j);
                    }
                }
            }
        }
        plan = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }
        check();
    }

    private static void check() {
        for (int i = 0; i < plan.length - 1; i++) {
            if (find(plan[i]) != find(plan[i + 1])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static void union(int num1, int num2) {
        num1 = find(num1);
        num2 = find(num2);
        if (num1 < num2) {
            parent[num2] = num1;
        } else {
            parent[num1] = num2;
        }
    }

    private static int find(int num) {
        if (parent[num] == num) {
            return num;
        }
        return find(parent[num]);
    }

    private static void makeSet() {
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }
}
