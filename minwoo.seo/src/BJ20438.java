import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20438 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int Q = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] students = new int[N + 3];

    // 졸고있는 학생 체크
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {

      students[Integer.parseInt(st.nextToken())] = -1;
    }

    // 졸고 있는 학생: -1, 출첵 안한 학생: 0, 출첵한 학생: 1
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < Q; i++) {
      int checkList = Integer.parseInt(st.nextToken());
      if (students[checkList] == -1) {
        continue;
      }
      for (int idx = checkList; idx < N + 3; idx += checkList) {
        if (students[idx] != -1) {
          students[idx] = 1;
        }
      }
    }
    // 구간별 체크
    StringBuilder sb = new StringBuilder();
    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      int S = Integer.parseInt(st.nextToken());
      int E = Integer.parseInt(st.nextToken());
      int cnt = 0;
      for (int i = S; i < E + 1; i++) {
        if (students[i] != 1) {
          cnt++;
        }
      }
      sb.append(cnt);
      sb.append('\n');
    }
    System.out.println(sb);
  }
}
