import java.io.*;
import java.util.*;

public class BJ1477 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = parse(st.nextToken()); // 현재 휴게소의 개수
        int m = parse(st.nextToken()); // 더 지으려고 하는 휴게소의 개수
        int l = parse(st.nextToken()); // 고속도로의 길이

        int[] currentArea = new int[n + 2];
        currentArea[0] = 0;
        currentArea[n + 1] = l;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            currentArea[i] = parse(st.nextToken());
        }
        Arrays.sort(currentArea);

        PriorityQueue<RestDist> pq = new PriorityQueue<>();
        for (int i = 1; i <= n + 1; i++) {
            pq.offer(new RestDist(currentArea[i] - currentArea[i - 1], 1));
        }

        while (m-- > 0) {
            RestDist rd = pq.poll();
            rd.n++;
            pq.offer(rd);
        }

        System.out.println(pq.poll().getMaxDist());
    }

    private static class RestDist implements Comparable<RestDist> {
        int dist, n;

        public RestDist(int dist, int n) {
            this.dist = dist;
            this.n = n;
        }

        int getMaxDist() {
            return dist / n + ((dist % n == 0) ? 0 : 1);
        }

        @Override
        public int compareTo(RestDist o) {
            return o.getMaxDist() - getMaxDist();
        }

        @Override
        public String toString() {
            return n + ": " + dist;
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
