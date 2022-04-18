import java.io.*;
import java.util.*;

public class BOJ2056 {
    static int N;
    static int[] inDegree, jobTime, endTime;
    static ArrayList<Integer>[] nextJob;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        init();
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            jobTime[i] = time;
            int prevCnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < prevCnt; j++) {
                int prev = Integer.parseInt(st.nextToken());
                nextJob[prev].add(i);
                inDegree[i]++;
            }
        }
        topologySort();
        System.out.println(Arrays.stream(endTime).max().getAsInt());

    }

    private static void topologySort() {
        endTime = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                endTime[i] = jobTime[i];
            }
        }

        for (int i = 1; i <= N; i++) {
            int num = queue.poll();
            for (int idx = 0; idx < nextJob[num].size(); idx++) {
                int next = nextJob[num].get(idx);
                endTime[next] = Math.max(endTime[next], endTime[num] + jobTime[next]);
                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
    }

    private static void init() {
        inDegree = new int[N + 1];
        jobTime = new int[N + 1];
        nextJob = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nextJob[i] = new ArrayList<>();
        }
    }
}
