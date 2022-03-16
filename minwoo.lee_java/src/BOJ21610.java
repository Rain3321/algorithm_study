import java.util.*;
import java.io.*;

public class BOJ21610 {
    static int N, M;
    static int[][] A;
    static int[][] direction = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static Queue<Cloud> clouds;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clouds = new LinkedList<>();
        clouds.add(new Cloud(N - 2, 0));
        clouds.add(new Cloud(N - 2, 1));
        clouds.add(new Cloud(N - 1, 0));
        clouds.add(new Cloud(N - 1, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int queueSize = clouds.size();
            while (queueSize-- > 0) {
                Cloud c = clouds.poll();
                int moveX = step1(c.x, direction[d - 1][0], s);
                int moveY = step1(c.y, direction[d - 1][1], s);
                A[moveX][moveY] += 1;
                clouds.add(new Cloud(moveX, moveY));
            }
            while (!clouds.isEmpty()) {
                Cloud c = clouds.poll();
                step4(c.x, c.y);
            }
            step5();
        }
        System.out.println(getSum());

    }

    private static int step1(int point, int dir, int s) {
        int next = point + s % N * dir;
        return next >= 0 ? next % N : N + next;
    }

    private static void step4(int x, int y) {
        int cnt = 0;
        for (int d = 1; d <= 7; d += 2) {
            int nx = x + direction[d][0];
            int ny = y + direction[d][1];
            if (rangeCheck(nx, ny) && Math.abs(A[nx][ny]) > 0) {
                cnt++;
            }
        }
        A[x][y] = -(A[x][y] + cnt);
    }

    private static void step5() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] >= 2) {
                    A[i][j] -= 2;
                    clouds.add(new Cloud(i, j));
                }
                if (A[i][j] < 0) {
                    A[i][j] = -(A[i][j]);
                }
            }
        }
    }

    private static int getSum() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += A[i][j];
            }
        }
        return sum;
    }

    static boolean rangeCheck(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < N;
    }

    static class Cloud {
        int x, y;

        Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
