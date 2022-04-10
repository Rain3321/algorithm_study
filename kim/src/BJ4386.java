import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ4386 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parse(br.readLine());// 별 수
        double[][] stars = new double[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        double[][] adj = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                adj[i][j] = getDist(stars[i][0], stars[i][1], stars[j][0], stars[j][1]);
        } // 별끼리의 거리를 인접행렬에 저장

        boolean[] visit = new boolean[n];
        PriorityQueue<Star> pq = new PriorityQueue<>();
        double ans = 0; // MST 비용
        pq.offer(new Star(0, 0)); // 시작점: 0

        while (!pq.isEmpty()) {
            Star star = pq.poll();

            if (visit[star.n])
                continue;

            visit[star.n] = true;
            ans += star.dist;

            for (int i = 0; i < n; i++) {
                if (adj[star.n][i] != 0 && !visit[i]) {
                    pq.offer(new Star(adj[star.n][i], i));
                }
            }
        }
        System.out.printf("%.2f", ans);

    }

    private static double getDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }

    static class Star implements Comparable<Star> {
        double dist;
        int n;

        Star(double dist, int n) {
            this.dist = dist;
            this.n = n;
        }

        @Override
        public int compareTo(Star o) {
            return (dist - o.dist > 0) ? 1 : -1;
        }
    }
}
