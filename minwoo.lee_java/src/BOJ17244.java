import java.io.*;
import java.util.*;

public class BOJ17244 {
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int N, M, sx, sy, ex, ey, cnt, result;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                    map[i][j] = '.';
                }
                if (map[i][j] == 'E') {
                    ex = i;
                    ey = j;
                    map[i][j] = '.';
                }

                if (map[i][j] == 'X') {
                    map[i][j] = (char) cnt++;
                }
            }
        }
        bfs();
        System.out.println(result);
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(sx, sy, 0, 0));
        boolean[][][] discovered = new boolean[M][N][1 << cnt + 1];
        discovered[sx][sy][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = node.x + direction[d][0];
                int ny = node.y + direction[d][1];
                if (!check(nx, ny) || map[nx][ny] == '#') {
                    continue;
                }
                int nextPack = node.pack;
                if (map[nx][ny] != '.') {
                    nextPack |= 1 << (int) map[nx][ny];
                }
                if (discovered[nx][ny][nextPack]) {
                    continue;
                }
                if (nx == ex && ny == ey) {
                    if (nextPack == (1 << cnt) - 1) {
                        result = node.time + 1;
                        return;
                    } else {
                        continue;
                    }
                }
                discovered[nx][ny][nextPack] = true;
                queue.add(new Node(nx, ny, nextPack, node.time + 1));
            }
        }
    }

    private static boolean check(int nx, int ny) {
        return 0 <= nx && nx < M && 0 <= ny && ny < N;
    }


    static class Node {
        int x, y, pack, time;

        Node(int x, int y, int pack, int time) {
            this.x = x;
            this.y = y;
            this.pack = pack;
            this.time = time;
        }

    }
}
