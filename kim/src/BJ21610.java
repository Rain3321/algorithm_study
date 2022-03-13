import java.io.*;
import java.util.*;

public class BJ21610 {

    private static final int[][] move = {{}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0},
            {1, -1}};
    private static int n;
    private static int[][] water;
    private static boolean[][] check;
    private static Queue<Point> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        water = new int[n + 1][n + 1];
        check = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 구름을 저장할 큐
        queue = new LinkedList<>();
        // 처음 구름
        queue.add(new Point(1, n));
        queue.add(new Point(2, n));
        queue.add(new Point(1, n - 1));
        queue.add(new Point(2, n - 1));

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            // 1. 구름 이동, 비내리기
            move_cloud(d, s);
            // 2. 물복사
            waterCopy();
            // 3. 구름 재생성
            createCloud();
        }
        System.out.println(waterSum());
    }

    // 구름 이동
    private static void move_cloud(int dir, int s) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Point p = queue.poll();
            int nx = setPoint(p.x + s * move[dir][1]);
            int ny = setPoint(p.y + s * move[dir][0]);

            water[ny][nx]++;
            check[ny][nx] = true;
            queue.add(new Point(nx, ny));
        }
    }

    private static int setPoint(int p) {
        while (p > n) {
            p -= n;
        }

        while (p <= 0) {
            p += n;
        }

        return p;
    }

    // 물복사
    private static void waterCopy() {
        int[][] m = {{-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + m[i][0];
                int ny = p.y + m[i][1];
                // 좌표값이 유효할 경우에만
                if (nx > 0 && nx <= n && ny > 0 && ny <= n) {
                    if (water[ny][nx] > 0) {
                        water[p.y][p.x]++;
                    }
                }
            }
        }
    }

    // 구름 생성
    private static void createCloud() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!check[i][j] && water[i][j] >= 2) {
                    water[i][j] -= 2;
                    queue.add(new Point(j, i));
                } else {
                    check[i][j] = false;
                }
            }
        }
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 물의 합 구하기
    private static int waterSum() {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sum += water[i][j];
            }
        }
        return sum;
    }
}
