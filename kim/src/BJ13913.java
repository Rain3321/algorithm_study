import java.util.*;
import java.util.LinkedList;

public class BJ13913 {

    private static int[] pre;
    private static Queue<Integer> q;
    private static int k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        k = sc.nextInt();
        sc.close();

        StringBuilder sb = new StringBuilder();
        if (n >= k) {
            System.out.println(n - k);
            for (int i = n; i >= k; i--) {
                sb.append(i + " ");
            }
            System.out.println(sb);
            return;
        }

        q = new LinkedList<>();
        pre = new int[100002];
        Arrays.fill(pre, -1);

        pre[n] = 0;
        q.offer(n);

        while (!q.isEmpty()) {
            int x = q.poll();
            // 뒤로 이동
            move(x, x - 1);
            // 앞으로 이동
            move(x, x + 1);
            // 순간이동
            move(x, 2 * x);
        }

        int cnt = 0;
        while (k != n) {
            cnt++;
            sb.insert(0, k + " ");
            k = pre[k];
        }
        sb.insert(0, n + " ");
        System.out.println(cnt + "\n" + sb);
    }

    private static void move(int x, int nx) {
        if (nx >= 0 && nx < pre.length && pre[nx] == -1) {
            pre[nx] = x;
            q.add(nx);

            if (nx == k) {
                return;
            }
        }
    }
}
