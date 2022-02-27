import java.io.*;
import java.util.Arrays;

public class BOJ2138 {
    static int result = Integer.MAX_VALUE;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        boolean[] start = new boolean[N];
        boolean[] end = new boolean[N];
        makeBulb(start, br.readLine());
        makeBulb(end, br.readLine());
        boolean[] startCopy = Arrays.copyOf(start, start.length);
        // 첫번째 스위치 눌렀을 때
        start[0] = !start[0];
        start[1] = !start[1];
        solution(start, end, 1, 1);
        // 첫번째 스위치를 누르지 않았을 때
        solution(startCopy, end, 0, 1);

        StringBuilder sb = new StringBuilder();
        System.out.println((result == Integer.MAX_VALUE) ? sb.append(-1) : sb.append(result));
    }

    private static void solution(boolean[] start, boolean[] end, int cnt, int idx) {
        for (int i = idx; i < N; i++) {
            if (start[i - 1] != end[i - 1]) {
                onOff(start, i);
                cnt++;
            }
        }
        if (complete(start, end)) {
            result = Math.min(result, cnt);
        }
    }

    private static void onOff(boolean[] bulb, int idx) {
        bulb[idx - 1] = !bulb[idx - 1];
        bulb[idx] = !bulb[idx];
        if (idx + 1 < N) {
            bulb[idx + 1] = !bulb[idx + 1];
        }
    }

    private static void makeBulb(boolean[] bulb, String status) {
        for (int i = 0; i < status.length(); i++) {
            if (status.charAt(i) == '1') {
                bulb[i] = false;
            } else {
                bulb[i] = true;
            }
        }
    }

    private static boolean complete(boolean[] start, boolean[] end) {
        for (int i = 0; i < N; i++) {
            if (start[i] != end[i]) {
                return false;
            }
        }
        return true;
    }
}
