import java.io.*;
import java.util.*;

public class BJ17135 {

    private static int n, m, d, max;
    private static int[] archers = new int[3];
    private static final int[][] move = {{-1, 0}, {0, 1}, {0, -1}};
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 행
        m = Integer.parseInt(st.nextToken()); // 열
        d = Integer.parseInt(st.nextToken()); // 사거리
        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().replaceAll(" ", "").toCharArray();
        } // end Input

        go(0, 0);
        System.out.println(max);
    }

    private static void go(int idx, int cnt) {
        if (cnt >= 3) {
            // 궁수의 좌표가 뽑혔다.
            // 죽일 수 있는 적의 수의 최댓값을 갱신한다.
            max = Math.max(max, getEnemy(copyMap()));
            return;
        }
        if (idx >= m)
            return;

        archers[cnt] = idx;
        go(idx + 1, cnt + 1);
        go(idx + 1, cnt);
    }

    private static int getEnemy(char[][] tmp) {
        int ans = 0;

        for (int i = n - 1; i >= 0; i--) {
            // 궁수가 있는 행을 i로 친다.
            // 각각의 궁수가 죽이는 적의 좌표를 받아온다.
            Point[] kill = new Point[3];
            for (int j = 0; j < 3; j++) {
                kill[j] = bfs(new Point(i, archers[j]), tmp);
            }

            for (int j = 0; j < 3; j++) {
                // 죽일 수 있는 적이 있다면 그 곳의 좌표를 0으로 바꾼다.
                if (kill[j] != null && tmp[kill[j].i][kill[j].j] == '1') {
                    tmp[kill[j].i][kill[j].j] = '0';
                    ans++;
                }
            }
        }
        return ans;
    }

    private static Point bfs(Point start, char[][] tmp) {
        Queue<Point> q = new LinkedList<>();
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] visit = new boolean[n][m];
        visit[start.i][start.j] = true;
        q.offer(start);
        int time = 0;

        while (!q.isEmpty() && time < d) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point p = q.poll();
                if (tmp[p.i][p.j] == '1') {
                    pq.offer(p);
                }
                for (int j = 0; j < 3; j++) {
                    int ni = p.i + move[j][0];
                    int nj = p.j + move[j][1];
                    if (!checkIdx(ni, nj) || visit[ni][nj])
                        continue;

                    visit[ni][nj] = true;
                    q.offer(new Point(ni, nj));
                }
            }
            if (!pq.isEmpty())
                return pq.poll();
            time++;
        }
        return null;
    }

    private static char[][] copyMap() {
        char[][] tmp = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                tmp[i][j] = map[i][j];
        }
        return tmp;
    }

    private static boolean checkIdx(int i, int j) {
        return !(i < 0 || j < 0 || i >= n || j >= m);
    }

    private static class Point implements Comparable<Point> {
        int i, j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Point p) {
            return j - p.j;
        }
    }
}
