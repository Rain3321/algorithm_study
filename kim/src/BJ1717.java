import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1717 {

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parse(st.nextToken());
        int m = parse(st.nextToken());
        StringBuilder sb = new StringBuilder();

        // key값 저장
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++)
            parents[i] = i;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = parse(st.nextToken());
            int a = parse(st.nextToken());
            int b = parse(st.nextToken());
            if (op == 0) {
                parents[getParent(b)] = getParent(a);
            } else {
                // 둘의 부모노드가 같은지 비교
                if (getParent(a) == getParent(b))
                    sb.append("YES\n");
                else
                    sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }

    private static int getParent(int v) {
        if (parents[v] == v)
            return v;
        return getParent(parents[v]);
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}

