import java.io.*;
import java.util.*;

public class BOJ2696 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            int m = Integer.parseInt(br.readLine());
            // 중앙값의 개수 저장 m은 홀수
            sb.append(m / 2 + 1).append("\n");

            // StringBuilder에 10개씩 넣기 위해 사용하는 변수
            int sbCnt = 0;

            // 10개씩 입력 받기
            for (int i = 1; i <= m; i++) {
                if (i % 10 == 1) {
                    st = new StringTokenizer(br.readLine());
                }
                int num = Integer.parseInt(st.nextToken());

                if (minHeap.size() == maxHeap.size()) {
                    maxHeap.add(num);
                } else {
                    minHeap.add(num);
                }

                if (!minHeap.isEmpty()) {
                    if (maxHeap.peek() > minHeap.peek()) {
                        int n1 = maxHeap.poll();
                        int n2 = minHeap.poll();
                        maxHeap.add(n2);
                        minHeap.add(n1);
                    }
                }
                if (i % 2 == 1) {
                    if (++sbCnt == 10) {
                        sb.append(maxHeap.peek()).append("\n");
                    } else {
                        sb.append(maxHeap.peek()).append(" ");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
