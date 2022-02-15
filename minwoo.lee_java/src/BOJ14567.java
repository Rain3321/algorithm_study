import java.util.*;
import java.io.*;

public class BOJ14567 {
    static int[] preRequestCnt, result;
    static int[][] graph;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 진입 차수를 뜻하는 배열
        // ex 값이 1 3 / 2 3 존재한다 가정하면
        // 3은 1에서 진입할 수 있고 2에서 진입할 수 있기 때문에 preRequestCnt[3] = 2가된다.
        preRequestCnt = new int[N + 1];
        result = new int[N + 1];
        graph = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            graph[first][next] = 1;
            preRequestCnt[next]++;
        }
        topologicalSort();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i] + " ");
        }
        System.out.println(sb);
    }

    private static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        // 진입 차수가 0인 노드 즉 선수과목이 없는 노드를 큐에 삽입
        for (int i = 1; i <= N; i++) {
            if (preRequestCnt[i] == 0) {
                queue.add(i);
            }
        }
        int cnt = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int subject = queue.poll();
                result[subject] = cnt;
                for (int j = 1; j < graph[subject].length; j++) {
                    if (graph[subject][j] == 1 && --preRequestCnt[j] == 0) {
                        queue.add(j);
                    }
                }
            }
            cnt++;
        }
    }
}
