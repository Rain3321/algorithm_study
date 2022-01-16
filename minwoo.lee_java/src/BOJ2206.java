import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2206 {
    private static int n, m, nx, ny;
    private static char[][] arr;
    private static boolean[][][] discovered;

    private static int[][] direction = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = bf.readLine().toCharArray();
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        discovered = new boolean[n][m][2];
        ArrayDeque<Location> deque = new ArrayDeque<>();
        deque.add(new Location(0, 0, 1, 1));
        while (!deque.isEmpty()) {
            Location l = deque.poll();
            if (l.x == n - 1 && l.y == m - 1) {
                return l.route;
            }
            for (int d = 0; d < 4; d++) {
                nx = l.x + direction[d][0];
                ny = l.y + direction[d][1];
                // 다음 이동 칸이 범위를 벗어났는가
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    // 벗어 나지 않았다면 그 칸이 이동할 수 있는 칸이고 방문하지 않았던 장소인가
                    if (arr[nx][ny] == '0' && !discovered[nx][ny][l.bc]) {
                        discovered[nx][ny][l.bc] = true;
                        deque.add(new Location(nx, ny, l.bc, l.route + 1));
                        // 이동할 수 없는 장소이지만 벽을 부술 수 있는 횟수가 있고 방문 하지 않았는가
                    } else if (arr[nx][ny] == '1' && l.bc == 1 && !discovered[nx][ny][l.bc - 1]) {
                        discovered[nx][ny][l.bc - 1] = true;
                        deque.add(new Location(nx, ny, l.bc - 1, l.route + 1));
                    }
                }
            }
        }
        return -1;
    }

    static class Location {
        int x, y, bc, route;

        Location(int x, int y, int bc, int route) {
            this.x = x;
            this.y = y;
            this.bc = bc;
            this.route = route;
        }
    }
}
