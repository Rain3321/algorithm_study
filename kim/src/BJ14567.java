import java.io.*;
import java.util.*;

public class BJ14567 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 과목 수
        int m = Integer.parseInt(st.nextToken()); // 조건 수

        int[] semester = new int[n + 1]; // 최대학기를 저장할 배열 (인덱스: 과목번호, 값: 최소학기)
        ArrayList<Integer>[] adj = new ArrayList[n + 1]; // 인접리스트
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
            semester[i] = 1;
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            adj[v2].add(v1);
        } // end Input

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            // 현재 과목의 선수과목들을 인접리스트로 탐색
            for (Integer prerequisite : adj[i])
                semester[i] = Math.max(semester[i], semester[prerequisite] + 1);

            // 선수과목 학기 중 가장 큰 것
            sb.append(semester[i] + " ");
        }
        System.out.println(sb);
    }
}
