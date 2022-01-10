import java.util.*;
import java.io.*;

public class BJ16926 {
  private static int[][] arr;
  private static int N, M, R;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = parse(st.nextToken());
    M = parse(st.nextToken());
    R = parse(st.nextToken());

    arr = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        arr[i][j] = parse(st.nextToken());
      }
    }


    for(int i = 0; i < R; i++) {
      int n = 0;
      int m = 0;
      for (int r = 1; r <= Math.min(N / 2, M / 2); r++) {
        turn(n, m, r);
        n++;
        m++;
      }
    }


    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        bw.write(arr[i][j] + " ");
      }
      bw.write("\n");
    }

    bw.flush();
    br.close();
    bw.close();
  }

  private static void turn(int startX, int startY, int r) {
    int temp = arr[startX][startY];

    // ← 방향
    for (int i = startY; i < M - r; i++) {
      arr[startX][i] = arr[startX][i+1];
    }

    // ↑ 방향
    for (int i = startX; i < N - r; i++) {
      arr[i][M - r] = arr[i + 1][M - r];
    }

    // → 방향
    for (int i = M - r; i > startX; i--) {
      arr[N - r][i] = arr[N - r][i - 1];
    }

    // ↓ 방향
    for (int i = N - r; i > startY ; i--) {
      arr[i][startY] = arr[i - 1][startY];
    }

    arr[startX + 1][startY] = temp;
  }

  private static int parse(String number) {
    return Integer.parseInt(number);
  }
}
