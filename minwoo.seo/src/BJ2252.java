import java.util.*;

public class BJ2252 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    // 인접 리스트 선언
    ArrayList<Integer>[] adjList = new ArrayList[n + 1];
    for (int i = 0; i < n + 1; i++) {
      adjList[i] = new ArrayList<>();
    }
    // 본인보다 앞에 몇명있는지 체크
    int[] counts = new int[n + 1];

    while (m-- > 0) {
      int small = sc.nextInt();
      int tall = sc.nextInt();

      adjList[small].add(tall);
      counts[tall]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 1; i < n + 1; i++) {
      if (counts[i] == 0) { // 이 학생보다 앞에 있는 사람이 없을 경우 queue 에 넣기
        queue.add(i);
        counts[i] = -1;
      }
    }
    StringBuilder sb = new StringBuilder();
    while (!queue.isEmpty()) {
      int cur = queue.poll();
      sb.append(cur).append(" ");

      for (int student : adjList[cur]) {
        counts[student]--;
      }

      for (int i = 1; i < n + 1; i++) {
        if (counts[i] == 0) {
          queue.add(i);
          counts[i] = -1;
        }
      }
    }

    System.out.println(sb);

  }
}
