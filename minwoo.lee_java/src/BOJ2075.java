import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2075 {
    private static int N;
    private static int[][] arr;
    private static boolean[][] discovered;
    private static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        discovered = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr[i]);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(N - 1, N - 1));
        discovered[N - 1][N - 1] = true;
        int cnt = 0;
        int nx, ny;
        while (!pq.isEmpty()) {
            cnt++;
            Node cur = pq.poll();
            if (cnt == N) {
                System.out.println(cur.value);
                break;
            }
            for (int d = 0; d < 4; d++) {
                nx = cur.x + direction[d][0];
                ny = cur.y + direction[d][1];
                if (check(nx, ny) && !discovered[nx][ny]) {
                    discovered[nx][ny] = true;
                    pq.add(new Node(nx, ny));
                }
            }
        }
    }

    private static boolean check(int nx, int ny) {
        if (0 <= nx && nx < N && 0 <= ny && ny < N) {
            return true;
        }
        return false;
    }

    static class Node implements Comparable<Node> {
        int x, y, value;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.value = arr[x][y];
        }

        @Override
        public int compareTo(Node o) {
            return o.value - this.value;
        }
    }
}
