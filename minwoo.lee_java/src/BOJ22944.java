import java.io.*;
import java.util.*;

public class BOJ22944 {
    static int N, H, D, endX, endY, result;
    static char[][] map;
    static ArrayList<int[]> umbrella;
    static boolean[] discovered;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        umbrella = new ArrayList();
        int startX = 0, startY = 0;
        for (int i = 0; i < N; i++) {
            int j = 0;
            for (char v : br.readLine().toCharArray()) {
                map[i][j] = v;
                if (v == 'S') {
                    startX = i;
                    startY = j;
                } else if (v == 'E') {
                    endX = i;
                    endY = j;
                } else if (v == 'U') {
                    umbrella.add(new int[]{i, j});
                }
                j++;
            }
        }
        result = Integer.MAX_VALUE;
        discovered = new boolean[umbrella.size()];
        getShortestDistance(startX, startY, H, 0, 0);
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

    }

    private static void getShortestDistance(int x, int y, int hp, int durability, int distance) {

        if (hp + durability >= Math.abs(x - endX) + Math.abs(y - endY)) {
            result = Math.min(result, distance + Math.abs(x - endX) + Math.abs(y - endY));
            return;
        }
        for (int i = 0; i < umbrella.size(); i++) {
            int umbDis = Math.abs(x - umbrella.get(i)[0]) + Math.abs(y - umbrella.get(i)[1]);
            if (discovered[i] || hp + durability <= umbDis - 1) {
                continue;
            }
            if (durability >= umbDis - 1) {
                discovered[i] = true;
                getShortestDistance(umbrella.get(i)[0], umbrella.get(i)[1], hp, D - 1, distance + umbDis);
                discovered[i] = false;
            } else {
                discovered[i] = true;
                getShortestDistance(umbrella.get(i)[0], umbrella.get(i)[1], hp - (umbDis - durability) + 1, D - 1, distance + umbDis);
                discovered[i] = false;
            }
        }
    }
}
