import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ13913 {
    private static final int ENDPOINT = 100001;
    private static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        sb = new StringBuilder();
        // 수빈이 위치가 동생보다 큰 경우는 뒤로만 가서 도착할 수 있다.
        if (N >= K) {
            sb.append(N - K).append("\n");
            for (int location = N; location >= K; location--) {
                sb.append(location).append(" ");
            }
        } else {
            bfs(N, K);
        }

        System.out.println(sb.toString());
    }

    private static void bfs(int n, int k) {
        MoveInfo[] discovered = new MoveInfo[ENDPOINT];
        Arrays.fill(discovered, null);
        discovered[k] = new MoveInfo(-1, 0);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == n) {
                printResult(cur, discovered);
                break;
            }
            for (int next : new int[]{cur - 1, cur + 1, (cur % 2 == 0) ? cur / 2 : -1}) {
                if (rangeCheck(next) && discovered[next] == null) {
                    discovered[next] = new MoveInfo(cur, discovered[cur].time + 1);
                    queue.add(next);
                }
            }
        }
    }

    private static void printResult(int cur, MoveInfo[] discovered) {
        sb.append(discovered[cur].time).append("\n");
        while (cur != -1) {
            sb.append(cur + " ");
            cur = discovered[cur].prev;
        }
        sb.setLength(sb.length() - 1);
    }


    private static boolean rangeCheck(int next) {
        return 0 <= next && next < ENDPOINT;
    }

    static class MoveInfo {
        int prev, time;

        MoveInfo(int prev, int time) {
            this.prev = prev;
            this.time = time;
        }
    }
}
