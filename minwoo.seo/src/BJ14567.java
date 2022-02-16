import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14567 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = parse(st.nextToken());
    int M = parse(st.nextToken());

    // 인접리스트
    ArrayList<Integer>[] parents = new ArrayList[N + 1];
    for (int i = 1; i < N + 1; i++) {
      parents[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = parse(st.nextToken());
      int end = parse(st.nextToken());

      parents[end].add(start); // start -> end 의 형태에서 end 로 오는 값들을 리스트에 저장
    }

    int[] dp = new int[N + 1];
    Arrays.fill(dp, 1); // 기본 세팅

    for (int i = 1; i < N + 1; i++) {
      for (int k : parents[i]) {
        dp[i] = Math.max(dp[i], dp[k] + 1);
      }
      System.out.print(dp[i] + " ");
    }
  }

  private static int parse(String num) {
    return Integer.parseInt(num);
  }
}
