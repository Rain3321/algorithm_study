import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = parse(st.nextToken()); // 정점의 수
        int m = parse(st.nextToken()); // 간선의 수

        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] pre = new int[n + 1];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());

            int v1 = parse(st.nextToken());
            int v2 = parse(st.nextToken());

            adj[v1].add(v2);
            pre[v2]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (pre[i] == 0) {
                // 맨 앞에 설 수 있는 학생들을 큐에 삽입한다.
                q.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            int currStudent = q.poll();
            sb.append(currStudent + " ");
            for(int nextStudent : adj[currStudent]){
                // 이 학생이 서야지만 설 수 있는 학생들을 나열
                if(--pre[nextStudent] <= 0){
                    q.offer(nextStudent);
                }
            }
        }
        System.out.println(sb);

    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
