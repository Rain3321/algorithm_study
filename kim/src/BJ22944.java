import java.io.*;
import java.util.*;

public class BJ22944 {

    private static int n, h, d;
    private static char[][] map;
    private static int[][] health;
    private static Queue<Point> q;
    private static final int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 맵 크기
        h = Integer.parseInt(st.nextToken()); // 체력
        d = Integer.parseInt(st.nextToken()); // 우산 내구도
        map = new char[n][n];
        health = new int[n][n]; // 해당 지점까지 갔을 때의 최대 체력

        q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < n; j++)
                if (map[i][j] == 'S') {
                    q.offer(new Point(i, j, h, 0));
                    health[i][j] = h;
                }
        }// end Input

        System.out.println(bfs());
    }

    private static int bfs() {
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                Point p = q.poll();
                for (int i = 0; i < 4; i++) {
                    int ni = p.i + move[i][0];
                    int nj = p.j + move[i][1];

                    if (ni < 0 || nj < 0 || ni >= n || nj >= n)
                        continue;

                    if (map[ni][nj] == 'E')
                        // 도착지점 도착!!
                        return cnt + 1;

                    int nd = p.d;
                    int nh = p.h;

                    // 우산 칸이면 우산 내구도를 갱신시켜준다.
                    if (map[ni][nj] == 'U')
                        nd = d;

                    if (nd > 0)
                        // 우산이 있는 경우 우산 내구도 감소
                        nd--;
                    else
                        nh--;

                    // 사망
                    if (nd + nh <= 0)
                        continue;

                    if (health[ni][nj] < nh + nd) {
                        // 다음칸으로 가자
                        health[ni][nj] = nh + nd;
                        q.offer(new Point(ni, nj, nh, nd));
                    }
                }
            }
            cnt++;
        }
        return -1;
    } // end bfs()
}

class Point {
    int i, j, h, d;

    Point(int i, int j, int h, int d) {
        this.i = i;
        this.j = j;
        this.h = h;
        this.d = d;
    }
}
