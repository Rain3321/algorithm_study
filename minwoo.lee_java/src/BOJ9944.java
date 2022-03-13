import java.util.*;
import java.io.*;

public class BOJ9944 {
    static int N, M, result, boardCnt;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static char[][] map;
    static boolean[][] discovered;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String s;
        int tc = 1;
        while ((s = br.readLine()) != null) {
            st = new StringTokenizer(s);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            boardCnt = 0;
            for (int i = 0; i < N; i++) {
                String temp = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = temp.charAt(j);
                    if (map[i][j] == '.') {
                        boardCnt++;
                    }
                }
            }
            discovered = new boolean[N][M];
            result = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '.') {
                        discovered[i][j] = true;
                        start(i, j, 1, 0);
                        discovered[i][j] = false;
                    }
                }
            }
            if (result == Integer.MAX_VALUE) {
                result = -1;
            }
            sb.append("Case " + tc++ + ": " + result + "\n");
        }
        System.out.print(sb);
    }

    private static void start(int startX, int startY, int step, int moveCnt) {
        if (step == boardCnt) {
            result = Math.min(result, moveCnt);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int cnt = 0;
            int x = startX;
            int y = startY;
            int nx, ny;
            while (true) {
                nx = x + direction[d][0];
                ny = y + direction[d][1];
                if (!check(nx, ny) || map[nx][ny] == '*' || discovered[nx][ny]) {
                    break;
                }
                discovered[nx][ny] = true;
                cnt++;
                x = nx;
                y = ny;
            }
            if (x == startX && y == startY) {
                continue;
            }
            start(x, y, step + cnt, moveCnt + 1);
            back(x, y, cnt, d);

        }
    }
    private static void back(int x, int y, int cnt, int d) {
        for (int i = 0; i < cnt; i++) {
            discovered[x][y] = false;
            x -= direction[d][0];
            y -= direction[d][1];
        }
    }

    private static boolean check(int nx, int ny) {
        return (0 <= nx && nx < N && 0 <= ny && ny < M) ? true : false;
    }
}
