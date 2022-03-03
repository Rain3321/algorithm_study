import java.io.*;
import java.util.*;

public class BOJ2343 {
    static int N, M;
    static int[] minutes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        minutes = new int[N];
        int right = 0;
        int left = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            minutes[i] = Integer.parseInt(st.nextToken());
            right += minutes[i];
            left = Math.max(left, minutes[i]);
        }

        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cnt = divide(mid);
            if (cnt <= M) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(result);
    }

    private static int divide(int mid) {
        int subSum = 0;
        int cnt = 1;
        for (int minute : minutes) {
            if (subSum + minute > mid) {
                cnt++;
                subSum = minute;
            } else {
                subSum += minute;
            }
        }
        return cnt;
    }
}
