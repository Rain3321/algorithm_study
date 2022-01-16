import java.util.*;

public class BJ1753 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int V = sc.nextInt();
    int E = sc.nextInt();

    int start = sc.nextInt();

    ArrayList<Edge>[] adjacencyList = new ArrayList[V+1];
    for (int i = 0; i < V + 1; i++) {
      adjacencyList[i] = new ArrayList<>();
    }
    for (int i = 0; i < E; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      int w = sc.nextInt();

      adjacencyList[u].add(new Edge(w, v));
    }

    PriorityQueue<Edge> pq = new PriorityQueue<>();
    int[] distance = new int[V+1];
    Arrays.fill(distance, Integer.MAX_VALUE);
    boolean[] visited = new boolean[V+1];

    distance[start] = 0;
    pq.offer(new Edge(0, start));

    while (!pq.isEmpty()) {
      Edge now = pq.poll();
      int s = now.destination;

      if (visited[s]) {
        continue;
      }
      visited[s] = true;
      for (Edge next : adjacencyList[s]) {
        if (distance[next.destination] > distance[s] + next.weight) {
          distance[next.destination] = distance[s] + next.weight;
          pq.offer(new Edge(distance[next.destination], next.destination));
        }
      }
    }

    for (int i = 1; i < V + 1; i++) {
      if (distance[i] == Integer.MAX_VALUE) {
        System.out.println("INF");
      } else {
        System.out.println(distance[i]);
      }
    }
  }

  private static class Edge implements Comparable<Edge> {
    int weight;
    int destination;

    public Edge(int weight, int destination) {
      this.weight = weight;
      this.destination = destination;
    }

    @Override
    public int compareTo(Edge o) {
      return Integer.compare(this.weight, o.weight);
    }
  }
}
