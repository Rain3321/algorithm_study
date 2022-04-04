import java.io.*;
import java.util.*;

public class BOJ2252 {
    static int N, M;
    // 진입 차수를 관리하는 배열
    static int[] inDegree;
    static ArrayList<Integer>[] enterInfo;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        inDegree = new int[N + 1];
        enterInfo = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            enterInfo[i] = new ArrayList();
        }
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            inDegree[to]++;
            enterInfo[from].add(to);
        }
        sb = new StringBuilder();
        topologicalSort();
        System.out.println(sb.toString());
    }

    private static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=N; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        for(int i=1; i<=N; i++){
            if(queue.isEmpty()){
                return;
            }
            int num = queue.poll();
            sb.append(num + " ");
            for(int idx=0; idx< enterInfo[num].size(); idx++){
                int next = enterInfo[num].get(idx);
                if(--inDegree[next] == 0){
                    queue.add(next);
                }
            }
        }
    }
}
