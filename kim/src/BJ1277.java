import java.io.*;
import java.util.*;

public class BJ1277 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = parse(st.nextToken()); // 발전소의 수
        int w = parse(st.nextToken()); // 현재 남아있는 전선의 수
        double m = Double.parseDouble(br.readLine()); // 제한 길이

        Point[] powerPlant = new Point[n + 1];

        for (int i = 1; i <= n; i++) {
            // 발전소의 좌표 등록하기
            st = new StringTokenizer(br.readLine());
            powerPlant[i] = new Point(parse(st.nextToken()), parse(st.nextToken()));
        }

        double[][] adj = new double[n + 1][n + 1]; // 발전소 간의 거리를 저장해줄 인접행렬
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                adj[i][j] = getDist(powerPlant[i], powerPlant[j]);
            }
        }

        for (int j = 0; j < w; j++) {
            st = new StringTokenizer(br.readLine());
            int from = parse(st.nextToken());
            int to = parse(st.nextToken());

            adj[from][to] = 0;
            adj[to][from] = 0;
        }

        // 다익스트라 알고리즘을 통한 최단거리 찾기
        double[] dist = new double[n + 1]; // 최소 비용을 저장할 배열
        boolean[] visit = new boolean[n + 1]; // 방문제크
        Arrays.fill(dist, Double.MAX_VALUE);
        // 시작노드 설정
        dist[1] = 0;

        for (int i = 1; i <= n; i++) {
            double min = Double.MAX_VALUE;
            int minIdx = i;
            for (int j = 1; j <= n; j++) {
                if (!visit[j] && dist[j] < min) {
                    min = dist[j];
                    minIdx = j;
                }
            }

            visit[minIdx] = true;
            if (minIdx == n)
                break;

            for (int j = 1; j <= n; j++) {
                if (!visit[j] && adj[minIdx][j] <= m && min + adj[minIdx][j] < dist[j]) {
                    dist[j] = min + adj[minIdx][j];
                }
            }
        }
        System.out.println((int) (dist[n] * 1000));
    }

    private static double getDist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(Math.abs(p1.x - p2.x), 2) + Math.pow(Math.abs(p1.y - p2.y), 2));
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
