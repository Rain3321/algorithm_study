import java.util.*;
import java.io.*;

public class BOJ17141 {
    static int N, M, zeroCnt, result;
    static int[][] arr;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static ArrayList<Location> locations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        result = Integer.MAX_VALUE;
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    locations.add(new Location(i, j, 0));
                    arr[i][j] = 0;
                    zeroCnt++;
                    continue;
                }
                if (arr[i][j] == 0)
                    zeroCnt++;
            }
        }
        getCombine(0, 0, new Stack<Integer>());
        if (result != Integer.MAX_VALUE)
            System.out.println(result);
        else
            System.out.println(-1);
    }

    static void getCombine(int start, int cnt, Stack candi) {
        if (cnt == M) {
            BFS(candi);
            return;
        }
        for (int idx = start; idx < locations.size(); idx++) {
            candi.push(idx);
            getCombine(idx + 1, cnt + 1, candi);
            candi.pop();
        }
    }

    static void BFS(Stack candidate) {
        boolean[][] discovered = new boolean[N][N];
        Queue<Location> queue = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            Location virus = locations.get((Integer)candidate.get(i));
            discovered[virus.x][virus.y] = true;
            queue.add(virus);
        }

        int value = 0, virusCnt = M;

        LinkedList<Location> next = new LinkedList<>();
        int nx, ny;

        while (!queue.isEmpty()) {
            Location virus = queue.poll();
            if (virus.time > result) return;
            for (int d = 0; d < 4; d++) {
                nx = virus.x + direction[d][0];
                ny = virus.y + direction[d][1];
                if (0 <= nx && nx < N && 0 <= ny && ny < N && !discovered[nx][ny]) {
                    if (arr[nx][ny] == 0) {
                        discovered[nx][ny] = true;
                        virusCnt++;
                        value = virus.time + 1;
                        next.add(new Location(nx, ny, virus.time + 1));

                    }
                }
            }
            if (queue.isEmpty()) {
                queue = next;
                next = new LinkedList<>();
            }

        }
        if (virusCnt == zeroCnt)
            result = Math.min(result, value);
    }

    static class Location implements Cloneable {
        int x, y, time;

        Location(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
