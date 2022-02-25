import java.io.*;
import java.util.*;

public class BOJ1717 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        makeSet(n);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (op == 0) {
                if (a != b) {
                    union(a, b);
                }
            } else {
                String result = (a == b) ? "YES" : isSameParent(a, b);
                sb.append(result + "\n");
            }
        }
        System.out.print(sb);
    }

    private static void makeSet(int n) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static int find(int element) {
        if (parent[element] == element) {
            return element;
        }
        return find(parent[element]);
    }

    private static String isSameParent(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return "YES";
        }
        return "NO";
    }
}
