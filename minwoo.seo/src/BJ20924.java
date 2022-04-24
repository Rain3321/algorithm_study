import java.util.*;

public class BJ20924 {
  private static ArrayList<Node>[] adjList;
  private static boolean[] visited;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int R = sc.nextInt();
   adjList = new ArrayList[N + 1];
    for (int i = 0; i < N + 1; i++) {
      adjList[i] = new ArrayList<>();
    }

    for (int i = 0; i < N - 1; i++) {
      int start = sc.nextInt();
      int end = sc.nextInt();
      int cost = sc.nextInt();

      adjList[start].add(new Node(end, cost));
      adjList[end].add(new Node(start, cost));
    }

    Queue<Integer> queue = new LinkedList<>();
    queue.add(R);

    int giga = 0;
    int rootToGiga = 0;
    visited = new boolean[N + 1];
    visited[R] = true;

    // 기가 노드까지 탐색
    while (!queue.isEmpty()) {
      int cur = queue.poll();

      if (count(adjList[cur]) >= 2) {
        giga = cur;
        break;
      }
      for (Node node : adjList[cur]) {
        if (visited[node.vertex]) {
          continue;
        }
        queue.add(node.vertex);
        rootToGiga += node.cost;
        visited[node.vertex] = true;
      }
    }

    // 기가 노드부터 리프 노드까지 탐색
    dfs(adjList[giga], 0);

    System.out.println(rootToGiga + " " + gigaToLeaf);
  }

  /**
   * 이미 방문한 노드를 제외한 노드 개수 반환
   */
  private static int count(ArrayList<Node> nodes) {
    int count = 0;
    for (Node node : nodes) {
      if (!visited[node.vertex]) {
        count++;
      }
    }
    return count;
  }

  private static int gigaToLeaf = 0;

  private static void dfs(ArrayList<Node> nodes, int cost) {
    if (nodes.size() == 1) { // 연결된 노드가 1개 == 리프노드
      gigaToLeaf = Math.max(gigaToLeaf, cost);
      return;
    }

    for (Node node : nodes) {
      if (visited[node.vertex]) {
        continue;
      }
      visited[node.vertex] = true;
      dfs(adjList[node.vertex], cost + node.cost);

    }
  }

  private static class Node {
    int vertex;
    int cost;

    public Node(int vertex, int cost) {
      this.vertex = vertex;
      this.cost = cost;
    }
  }
}
