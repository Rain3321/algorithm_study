import java.io.*;
import java.util.StringTokenizer;

public class BOJ14938 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    graph[i][j] = 100000;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            graph[from][to] = value;
            graph[to][from] = value;
        }

        for (int k = 1; k <= n; k++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    graph[from][to] = Math.min(graph[from][to], graph[from][k] + graph[k][to]);
                }
            }
        }
        int item = 0;
        for (int start = 1; start <= n; start++) {
            int temp = arr[start];
            for (int to = 1; to <= n; to++) {
                if (start != to && graph[start][to] <= m) {
                    temp += arr[to];
                }
            }
            item = Math.max(temp, item);
        }
        System.out.println(item);
    }
}
