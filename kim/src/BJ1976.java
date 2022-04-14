import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1976 {

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = parse(br.readLine()); // 도시 수
        int m = parse(br.readLine());

        makeSet(n);

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int link = parse(st.nextToken());
                if (link == 1) {
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int now = parse(st.nextToken());

        while (--m > 0) {
            int next = parse(st.nextToken());
            if (find(now) != find(next)) {
                System.out.println("NO");
                return;
            }
            now = next;
        }
        System.out.println("YES");
    }

    private static void makeSet(int n) {
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++)
            parents[i] = i;
    }

    private static int find(int v) {
        if (parents[v] == v)
            return v;
        return parents[v] = find(parents[v]);
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb)
            parents[pb] = pa;
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
