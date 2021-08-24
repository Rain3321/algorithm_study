import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BJ18352 {

  static class Node implements Comparable<Node>{
    int vertex;
    int weight;

    public Node(int vertex, int weight) {
      this.vertex = vertex;
      this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
      return Integer.compare(this.weight, o.weight);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = parse(st.nextToken());
    int M = parse(st.nextToken());
    int K = parse(st.nextToken());
    int X = parse(st.nextToken());
    // 인접 리스트
    ArrayList<Node>[] adjList = new ArrayList[N+1];
    for (int i = 0; i < N+1; i++) {
      adjList[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = parse(st.nextToken());
      int end = parse(st.nextToken());
      adjList[start].add(new Node(end, 1));
    }

    int[] dis = new int[N+1];
    Arrays.fill(dis, 1000001);
    dis[X] = 0;
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(X, 0));
    while (!pq.isEmpty()) {
      Node cur = pq.poll();
      int v = cur.vertex;
      int w = cur.weight;

      if(dis[v] < w) continue;

      for (Node next: adjList[v]) {
        int nextV = next.vertex;
        int nextW = next.weight + w;
        if(dis[nextV] > nextW) {
          dis[nextV] = nextW;
          pq.add(new Node(nextV, nextW));
        }
      }
    }

    boolean flag = true;
    for (int i = 1; i < N+1; i++) {
      if(dis[i] == K) {
        System.out.println(i);
        flag = false;
      }
    }
    if(flag) System.out.println(-1);
  }

  private static int parse(String nextToken) {
    return Integer.parseInt(nextToken);
  }
}
