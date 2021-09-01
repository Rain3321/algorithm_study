import java.io.*;
import java.util.*;

public class BJ17829 {
    static int[][] arr, next;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        next = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (N != 1) {
            sol(0, 0, N);
            N /= 2;
            for (int i = 0; i < N; i++) {
                System.arraycopy(next[i], 0, arr[i], 0, N);
            }
        }
        System.out.println(arr[0][0]);
    }
    static int[] temp;
    private static void sol(int x, int y, int n) {
        if(n == 2) {
            temp = new int[4];
            for (int i = 0; i < 2; i++) {
                System.arraycopy(arr[x + i], y, temp, i * 2, 2);
            }
            // 정렬
            Arrays.sort(temp);
            // 2번째로 큰 숫자
            next[x/2][y/2] = temp[2];
            return;
        }


        n /= 2;
        sol(x, y, n);
        sol(x+n, y, n);
        sol(x, y+n, n);
        sol(x+n, y+n, n);
    }
}
