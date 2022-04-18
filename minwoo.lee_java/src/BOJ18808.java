import java.io.*;
import java.util.*;

public class BOJ18808 {
    static int N, M, K;
    static int[][] map;
    static int[][][] stickers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = 0;
            }
        }
        K = Integer.parseInt(st.nextToken());
        stickers = new int[K][][];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[R][C];
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            stickers[k] = sticker;
        }
        int idx = 0;
        int rotateCnt = 0;
        while (idx < K) {
            boolean flag = false;
            breakPoint:
            {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (isAttach(idx, i, j)) {
                            attach(idx, i, j);
                            idx++;
                            flag = true;
                            rotateCnt = 0;
                            break breakPoint;
                        }
                    }
                }
            }
            if (!flag) {
                rotate(idx);
                rotateCnt++;
                if (rotateCnt == 4) {
                    rotateCnt = 0;
                    idx++;
                }
            }


        }
        System.out.println(getCnt());
    }

    private static int getCnt() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void attach(int idx, int startX, int startY) {
        int[][] sticker = stickers[idx];
        for (int i = 0; i < sticker.length; i++) {
            int y = startY;
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 1) {
                    map[startX][y] = sticker[i][j];
                }
                y++;
            }
            startX++;
        }
    }


    private static boolean isAttach(int idx, int startX, int startY) {
        int[][] sticker = stickers[idx];
        for (int i = 0; i < sticker.length; i++) {
            int y = startY;
            for (int j = 0; j < sticker[0].length; j++) {
                if (i >= N || j >= M || startX >= N || y >= M || (sticker[i][j] == 1 && map[startX][y] == 1)) {
                    return false;
                }
                y++;
            }
            startX++;
        }
        return true;
    }


    private static void rotate(int idx) {
        int[][] sticker = stickers[idx];
        int row = sticker.length;
        int col = sticker[0].length;
        int[][] rotatedSticker = new int[col][row];
        int x = 0;
        for (int j = 0; j < col; j++) {
            int y = 0;
            for (int i = row - 1; i >= 0; i--) {
                rotatedSticker[x][y] = sticker[i][j];
                y++;
            }
            x++;
        }
        stickers[idx] = rotatedSticker;
    }


    // 스티커 입력이 잘 되었나 확인
    private static void printStickers() {
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < stickers[i].length; j++) {
                for (int l = 0; l < stickers[i][j].length; l++) {
                    System.out.print(stickers[i][j][l] + " ");
                }
                System.out.println();
            }
            System.out.println("-------------");
        }
    }

    private static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
