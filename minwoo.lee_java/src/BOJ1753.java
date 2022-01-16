import java.io.*;
import java.util.*;

public class BOJ1753 {
    static int V, E, start, s, e, w;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(bf.readLine());
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        ArrayList<ArrayList<Node>> arr = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            arr.get(s).add(new Node(w, e));
        }

        bf.close();
        boolean[] discovered = new boolean[V + 1];
        int[] distance = new int[V + 1];
        Arrays.fill(distance, 200001);
        distance[start] = 0;
        priorityQueue.add(new Node(0, start));
        while (!priorityQueue.isEmpty()) {
            Node n = priorityQueue.poll();
            if (discovered[n.num]) continue;
            discovered[n.num] = true;
            for (Node next : arr.get(n.num)) {
                if (distance[next.num] > distance[n.num] + next.weight) {
                    distance[next.num] = distance[n.num] + next.weight;
                    priorityQueue.add(new Node(distance[next.num], next.num));

                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (distance[i] == 200001) {
                System.out.println("INF");
            } else
                System.out.println(distance[i]);
        }
    }

    static class Node implements Comparable<Node> {
        int num, weight;

        Node(int weight, int num) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node target) {
            return this.weight >= target.weight ? 1 : -1;
        }
    }
}
