import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BJ1197 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int v = sc.nextInt();
    int e = sc.nextInt();
    PriorityQueue<Node> pq = new PriorityQueue<>();


    ArrayList<Node>[] arr = new ArrayList[v+1];
    for (int i = 0; i < v+1; i++) {
      arr[i] = new ArrayList<>();
    }

    for (int i = 0; i < e; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int weight = sc.nextInt();
      // 방향이 없는 그래프이기 때문에 양방향으로 입력한다. 방향이 있을 경우 해당 방향으로만 넣자.
      arr[from].add(new Node(to, weight));
      arr[to].add(new Node(from, weight));
    }

    boolean[] visited = new boolean[v+1];
    int answer = 0;
    // prim
    pq.add(new Node(1, 0)); // 시작점은 1번으로 임의로 지정.
    int count = 0;
    while(!pq.isEmpty()) {
      Node cur = pq.poll();
      if(visited[cur.n]) continue; // 방문한 적 있으면 패스
      visited[cur.n] = true; // 현재 노드 방문 체크, 노드를 연결함
      count++; // 방문한 정점 카운트++
      answer += cur.weight; //
      for (Node next : arr[cur.n]) { // 현재 노드와 연결된 노드중에서 방문하지 않은 노드가 있다면 pq에 추가
        if(!visited[next.n]) {
          pq.add(next);
        }
      }
      if(count == v) break; // 모든 정점를 방문했다면
    }
    System.out.println(answer);
  }
  static class Node implements Comparable<Node>{
    int n;
    int weight;

    public Node(int n, int weight) {
      this.n = n;
      this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
      return this.weight - o.weight; //오름차순 정렬
    }
  }

}
