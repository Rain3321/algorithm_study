import java.util.*;

public class BJ1194 {
    static int N, M;
    static char[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 세로
        M = sc.nextInt(); // 가로

        map = new char[N][M];
        int[] start = new int[4];
        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == '0'){
                    start = new int[]{i, j, 0, 0}; // 출발 위치, 이동한 거리, 갖고있는 키 개수
                    map[i][j] = '.';
                }
            }
        }
        // 방문 배열이 각 칸마다 64인 이유는 열쇠/문의 개수가 a~f로 6개이기 때문
        boolean[][][] visited = new boolean[N][M][64];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(start);
        visited[start[0]][start[1]][0] = true;
        // bfs
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int dis = cur[2];
            int key = cur[3];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx < 0 || nx >= N || ny < 0 || ny>=M || visited[nx][ny][key] || map[nx][ny] =='#') continue;
                if(map[nx][ny] == '.') {
                    visited[nx][ny][key] = true;
                    queue.add(new int[]{nx, ny, dis+1, key});
                }
                else if(map[nx][ny] >= 'a' && map[nx][ny] <='f') {
                    int newKey = addKey(key, map[nx][ny]);
                    visited[nx][ny][newKey] = true;
                    queue.add(new int[] {nx,ny, dis+1, newKey});
                }
                else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    if(hasKey(key, map[nx][ny])) {
                        visited[nx][ny][key] = true;
                        queue.add(new int[]{nx,ny, dis+1, key});
                    }
                }
                else if(map[nx][ny] == '1') {
                    System.out.println(dis+1);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean hasKey(int key, char cKey) {
        int k = cKey - 'A';
        return (key & (1 << k)) != 0;

    }

    private static int addKey(int key, char cKey) {
        int k = cKey - 'a';
        key = key | (1 << k);
        return key;
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
}
