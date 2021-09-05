import java.util.*;
import java.io.*;

public class BJ16398 {
    static class Edge implements Comparable<Edge> {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] adjList = new ArrayList[N+1];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adjList[i].add(new Edge(j, Integer.parseInt(st.nextToken())));
            }
        }
        int ans = 0, count = 0;

        //prim
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        pq.add(new Edge(0, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(visited[cur.v]) continue;

            visited[cur.v] = true;
            ans += cur.w;

            for (Edge next: adjList[cur.v]) {
                if(!visited[next.v])
                    pq.add(next);
            }
            if(++count == N) break;
        }

        System.out.println(ans);
    }
}
