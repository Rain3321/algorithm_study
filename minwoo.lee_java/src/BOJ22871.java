import java.io.*;
import java.util.*;

public class BOJ22871 {
    static long result, left, right, mid;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }
        left = 1;
        right = Long.MAX_VALUE;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (croos(mid)) {
                result = mid;
                right = mid - 1;
            } else left = mid + 1;
        }
        System.out.println(result);
    }

    static boolean croos(long k) {
        Stack<Integer> stack = new Stack<>();
        boolean[] discovered = new boolean[arr.length];
        discovered[0] = true;
        stack.add(0);
        while (!stack.isEmpty()) {
            int location = stack.pop();
            if (location == arr.length - 1) return true;
            for (int next = location + 1; next < arr.length; next++) {
                if ((long) (next - location) * (1 + Math.abs(arr[location] - arr[next])) <= k && !discovered[next]) {
                    stack.push(next);
                    discovered[next] = true;
                }
            }
        }
        return false;
    }
}
