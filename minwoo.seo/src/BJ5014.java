// link: http://boj.kr/048d78a4d7374c908daedbf0b360f14d

import java.util.*;

public class BJ5014 {
    static int F, S, G, U, D, ans = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        F = sc.nextInt(); // 건물 전체 높이
        S = sc.nextInt(); // 강호의 위치
        G = sc.nextInt(); // 목표 위치
        U = sc.nextInt(); // +
        D = sc.nextInt(); // -
        visited = new boolean[F+1];
        sol();
        if(ans == -1) System.out.println("use the stairs");
        else
            System.out.println(ans);
    }
    static boolean[] visited;
    private static void sol() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {S, 0});
        boolean[] visited = new boolean[F+1];
        visited[S] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int floor = cur[0];
            int cnt = cur[1];
            if(floor == G) {
                ans = cnt;
                return;
            }
            if (floor + U <= F && !visited[floor+U]) {
                visited[floor+U] = true;
                queue.add(new int[]{floor+U, cnt+1});
            }
            if (floor - D >= 1 && !visited[floor - D]) {
                visited[floor - D] = true;
                queue.add(new int[]{floor - D, cnt+1});
            }
        }
        ans = -1;
    }
}
