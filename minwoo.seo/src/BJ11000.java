import java.io.*;
import java.util.*;

public class BJ11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Class[] arr = new Class[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i] = new Class(start, end);
        }
        Arrays.sort(arr);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(arr[0].end);
        for (int i = 1; i < N; i++) {
            if(arr[i].start >= pq.peek()) {
                pq.poll();
            }
            pq.add(arr[i].end);
        }
        System.out.println(pq.size());
    }
    static class Class implements Comparable<Class> {
        int start;
        int end;

        public Class(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Class o) {
            if(this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }
    }
}
