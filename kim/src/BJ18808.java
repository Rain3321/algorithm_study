import java.io.*;
import java.util.*;

public class BJ18808 {

    private static int n, m;
    private static boolean[][] map;
    private static int[][] size;
    private static Queue<Point>[] stickers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parse(st.nextToken()); // 세로
        m = parse(st.nextToken()); // 가로
        int k = parse(st.nextToken()); // 스티커의 개수
        stickers = new LinkedList[k];
        size = new int[k][2];
        for (int i = 0; i < k; i++) {
            stickers[i] = new LinkedList<>();
        }
        for (int idx = 0; idx < k; idx++) {
            st = new StringTokenizer(br.readLine());
            size[idx][0] = parse(st.nextToken()); // 스티커의 세로 크기
            size[idx][1] = parse(st.nextToken()); // 스티커의 가로 크기

            for (int i = 0; i < size[idx][0]; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < size[idx][1]; j++) {
                    if (parse(st.nextToken()) == 1) {
                        stickers[idx].offer(new Point(i, j));
                    }
                }
            }
        } // 스티커 정보를 입력받았다.

        map = new boolean[n][m];
        int ans = 0;

        for (int sticker = 0; sticker < k; sticker++) {
            // 스티커를 붙이기 시작할 점을 탐색한다.
            for (int d = 0; d < 4; d++) {
                if (findStart(sticker)) {
                    ans += stickers[sticker].size();
                    break;
                }
                // 해당 방향에서 못찾았으면 방향 돌리기
                int col = size[sticker][0];
                int row = size[sticker][1];
                turn(col, row, stickers[sticker]);
                size[sticker][0] = row;
                size[sticker][1] = col;
            }
        }
        System.out.println(ans);

    }

    private static boolean findStart(int sticker) {
        // 기준점을 전부 탐색해보도록 한다.
        for (int i = 0; i <= n - size[sticker][0]; i++) {
            for (int j = 0; j <= m - size[sticker][1]; j++) {
                // 스티커를 붙여본다.
                if (stick(i, j, sticker)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean stick(int i, int j, int sticker) {
        for (Point p : stickers[sticker]) {
            if (map[i + p.i][j + p.j]) {
                return false;
            }
        }

        for (Point p : stickers[sticker]) {
            map[i + p.i][j + p.j] = true;
        }
        return true;
    }

    // 90도 회전하는 메서드
    private static void turn(int col, int row, Queue<Point> q) {
        // 90도 회전: 위가 오른쪽으로 간다.
        // 행이 열 역순으로 간다.
        int s = q.size();
        while (s-- > 0) {
            Point p = q.poll();
            q.offer(new Point(p.j, col - p.i - 1));
        }
        // 큐에는 90도 회전된 정보가 저종된다.
    }

    private static class Point {
        int i, j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
