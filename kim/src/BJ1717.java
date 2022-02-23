import java.io.*;
import java.util.*;

public class BJ1717 {

    private static int[] p; // 부모 노드 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parse(st.nextToken()); // 집합 수
        int m = parse(st.nextToken()); // 링크 수

        StringBuilder sb = new StringBuilder();

        p = new int[n + 1];
        makeSet(p);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = parse(st.nextToken());
            int a = parse(st.nextToken());
            int b = parse(st.nextToken());

            if (cmd == 0)
                union(a, b);
            else if (find(a) == find(b))
                sb.append("YES\n");
            else
                sb.append("NO\n");

        }
        System.out.println(sb);
    }

    private static void makeSet(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            arr[i] = i;
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb)
            p[pb] = pa;
    }

    private static int find(int v) {
        if (p[v] == v)
            return v;
        else
            return p[v] = find(p[v]);
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
