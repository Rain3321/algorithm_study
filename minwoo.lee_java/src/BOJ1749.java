import java.util.*;
import java.io.*;

public class BOJ1749 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][M + 1];
        int[][] prefixSum = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                prefixSum[i][j] = map[i][j] + prefixSum[i][j - 1] + prefixSum[i - 1][j] - prefixSum[i - 1][j - 1];
            }
        }

        int maxValue = Integer.MIN_VALUE;
        for (int sx = 1; sx <= N; sx++) {
            for (int sy = 1; sy <= M; sy++) {
                for (int ex = sx; ex <= N; ex++) {
                    for (int ey = sy; ey <= M; ey++) {
                        maxValue = Math.max(maxValue, prefixSum[ex][ey] - prefixSum[ex][sy - 1] - prefixSum[sx - 1][ey] + prefixSum[sx - 1][sy - 1]);
                    }
                }
            }
        }
        System.out.println(maxValue);
    }
}
