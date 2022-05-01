import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2696 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = parse(br.readLine());

        while (TC-- > 0) {
            int m = parse(br.readLine());
            StringBuilder sb = new StringBuilder();
            sb.append(m / 2 + 1).append("\n");
            PriorityQueue<Integer> large = new PriorityQueue<>(); // 작은 순으로
            PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()); // 큰 순으로

            StringTokenizer st = null;
            for (int i = 1; i <= m; i++) {
                // 열 개씩 입력을 받는다
                if (i % 10 == 1)
                    st = new StringTokenizer(br.readLine());

                int n = parse(st.nextToken());

                if (i % 2 == 0) {
                    // 짝수 번 째에는 크기비교 할 필요 없음> 입력만 받는다.
                    // 일단 small에 넣은 다음에
                    small.offer(n);
                    // peek값을 비교하여 제대로 정렬이 되게끔 정리한 후에
                    while (!small.isEmpty() && !large.isEmpty() && small.peek() > large.peek()) {
                        large.offer(small.poll());
                    }
                    // 두 우선순위 큐의 크기 같아질 때까지 옮겨담는다.
                    while (small.size() != large.size()) {
                        if (small.size() > large.size()) {
                            large.offer(small.poll());
                        } else {
                            small.offer(large.poll());
                        }
                    }
                } else {
                    // 홀수 번 째에는 크기비교가 필요하다.
                    // 값을 비교해서 StringBuilder에 이어붙여준다.
                    if (!small.isEmpty() && n < small.peek()) {
                        // small의 peek값보다 작다 > small의 peek값이 중앙값이 됨
                        sb.append(small.peek()).append(" ");
                    } else if (!large.isEmpty() && n > large.peek()) {
                        // large의 peek값보다 크다 > large의 peek값이 중앙값이 됨
                        sb.append(large.peek()).append(" ");
                    } else {
                        // 두 값 사이에 있다(또는 두 큐가 모두 비어있다) > 현재 값이 중앙값이 됨
                        sb.append(n).append(" ");
                    }
                    // 어차피 다음은 짝수이고 그 때 우선순위 큐를 재정렬해줄것이기 때문에 대충 아무 큐에나 넣는다.
                    small.offer(n);
                }
                // 열 개 단위로 출력해야기 때문에 개행문자를 중간에 넣어준다.
                if (i % 20 == 0)
                    sb.append("\n");
            }
            System.out.println(sb);
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
