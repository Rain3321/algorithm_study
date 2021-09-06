import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2580 {
    static int[][] map = new int[10][10];
    static boolean[][] col = new boolean[10][10];
    static boolean[][] row = new boolean[10][10];
    static boolean[][] box = new boolean[9][10];
    static boolean finish = false;
    static ArrayList<int[]> blanks = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                    blanks.add(new int[] {i,j});
                else {
                    row[i][map[i][j]] = true;
                    col[j][map[i][j]] = true;
                    box[i / 3 * 3 + j / 3][map[i][j]] = true;
                }
            }
        }
        dfs(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void dfs(int k) {
        if(k == blanks.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
            return;
        }
        int[] cur = blanks.get(k);
        int x = cur[0];
        int y = cur[1];

        for (int i = 1; i <= 9; i++) {
            if(row[x][i]) continue;
            if(col[y][i]) continue;
            int t = x / 3 * 3 + y / 3;
            if(box[t][i]) continue;

            row[x][i] = true;
            col[y][i] = true;
            box[t][i] = true;
            map[x][y] = i;

            dfs(k + 1);

            if(finish) return;

            row[x][i] = false;
            col[y][i] = false;
            box[t][i] = false;
            map[x][y] = 0;
        }
    }
}
