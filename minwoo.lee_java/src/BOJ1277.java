import java.io.*;
import java.util.*;

public class BOJ1277 {
    static int N, W;
    static int[][] location;
    static double M;
    static boolean[][] connect;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        M = Double.parseDouble(br.readLine());

        // 각 발전소의 좌표 값 저장
        location = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            location[i] = new int[]{x, y};
        }

        // 전선이 있고 있는 것을 표시
        connect = new boolean[N + 1][N + 1];
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            connect[from][to] = true;
            connect[to][from] = true;
        }
        dijkstra();
    }


    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return (int) (o1.dist * 1000) - (int) (o2.dist * 1000);
        });
        boolean[] discovered = new boolean[N + 1];
        pq.add(new Node(1, location[1][0], location[1][1], W, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.num == N) {
                System.out.println((int) (cur.dist * 1000));
                return;
            }
            discovered[cur.num] = true;
            for (int next = 1; next <= N; next++) {
                if (discovered[next]) {
                    continue;
                }
                if (connect[cur.num][next]) {
                    pq.add(new Node(next, location[next][0], location[next][1], cur.w, cur.dist));
                } else {
                    double distance = getDist(cur.x, cur.y, location[next][0], location[next][1]);
                    if (cur.w > 0 && distance <= M) {
                        pq.add(new Node(next, location[next][0], location[next][1], cur.w - 1, cur.dist + distance));
                    }
                }
            }
        }
    }

    private static double getDist(int sx, int sy, int ex, int ey) {
        return Math.sqrt(Math.pow(sx - ex, 2) + Math.pow(sy - ey, 2));
    }

    static class Node {
        int num, x, y, w;
        double dist;

        Node(int num, int x, int y, int w, double dist) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.w = w;
            this.dist = dist;
        }
    }
}
