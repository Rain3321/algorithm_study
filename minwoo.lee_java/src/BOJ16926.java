import java.io.*;
import java.util.StringTokenizer;

public class BOJ16926 {
    public static void rotation(int x, int y, int maxX, int maxY, String arr[][], String arrCopy[][]) {
        for (int row = x; row < maxX - 1; row++) {
            arr[row + 1][y] = arrCopy[row][y];
        }

        for (int col = y; col < maxY - 1; col++) {
            arr[maxX - 1][col + 1] = arrCopy[maxX - 1][col];
        }

        for (int row = maxX - 1; row > x; row--) {
            arr[row - 1][maxY - 1] = arrCopy[row][maxY - 1];
        }

        for (int col = maxY - 1; col > y; col--) {
            arr[x][col - 1] = arrCopy[x][col];
        }

    }

    public static void copy(String arr[][], String arrCopy[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arrCopy[i][j] = arr[i][j];
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, m, r;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        String arr[][] = new String[n][m];
        String arrCopy[][] = new String[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = st.nextToken();
            }
        }
        copy(arr, arrCopy);

        int inner = (n > m ? m / 2 : n / 2);
        for (int i = 0; i < r; i++) {
            int start = 0, end = 0;
            for (int j = 0; j < inner; j++) {
                rotation(start, end, n - start, m - end, arr, arrCopy);
                start++;
                end++;
            }
            copy(arr, arrCopy);

        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }
}
