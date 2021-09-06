import java.util.*;

public class BJ13549 {
    static int[] move = {-1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        System.out.println(BFS(N, K));
    }

    private static int BFS(int N, int K) {
        Queue<Integer> queue = new LinkedList<>();
        int[] subin = new int[100001];
        Arrays.fill(subin, -1);

        queue.add(N);
        subin[N] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if(cur == K) return subin[cur];

            // * 2 인 거리 방문 체크
            int tmp = cur * 2;
            while (tmp <= 100000 && subin[tmp] == -1) {
                subin[tmp] = subin[cur];
                queue.add(tmp);
                tmp *= 2;
            }

            for (int d: move) {
                int next = cur + d;
                if(0 <= next && next <= 100000) {
                    if(subin[next] == -1) {
                        queue.add(next);
                        subin[next] = subin[cur] + 1;
                    }
                }
            }
        }
        return 0;
    }
}
