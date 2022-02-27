import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2343 {

    private static int n, m;
    private static int[] lectures;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parse(st.nextToken()); // 강의의 수
        m = parse(st.nextToken()); // 블루레이의 개수
        lectures = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            lectures[i] = parse(st.nextToken());
        // end Input

        int l = 1;
        int r = 1000000001; 
        int ans = r;
        while (l <= r) {
            int mid = (l + r) >> 1; // 블루레이의 크기
            if (getCnt(mid) > m) {
                // mid크기로 잘라서 쓰이는 블루레이의 개수가 m개보다 크면
                // 블루레이 크기를 늘려야함
                l = mid + 1;
            } else {
                // 줄여도됨
                r = mid - 1;
                ans = Math.min(ans, mid);
            }
        }
        System.out.println(ans);
    }

    // 블루레이를 해당 크기로 잘랐을 때 사용되는 블루레이의 개수를 반환
    private static int getCnt(int vol) {
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (lectures[i] > vol)
                return Integer.MAX_VALUE;
            sum += lectures[i];
            if (sum > vol) {
                // 만약 초과라면 현재의 강의는 이전의 강의들과 한 블루레이에 담을 수 없음
                cnt++;
                sum = lectures[i];
            }
        }
        return cnt;
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
