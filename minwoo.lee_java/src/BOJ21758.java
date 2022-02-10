import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ21758 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(bf.readLine());
        int[] honey = new int[N];
        int p1, p2, honeySpot;
        int result = 0;
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
        }

        int[] right = new int[N];
        int[] left = new int[N];


        right[0] = honey[0];
        for (int i = 1; i < N; i++) {
            right[i] = honey[i] + right[i - 1];
        }
        left[N - 1] = honey[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            left[i] = honey[i] + left[i + 1];
        }
        // 1. p1=0, p2=1~N-2, honeySpot = N-1
        p1 = 0;
        p2 = 1;
        honeySpot = N - 1;
        while (p2 < N - 1) {
            result = Math.max(result, 2 * (right[honeySpot] - right[p2]) + right[p2 - 1] - right[p1]);
            p2++;
        }
        // 2. p1= 1~N-2, p2=N-1, honeySpot = 0;
        p1 = 1;
        p2 = N - 1;
        honeySpot = 0;
        while (p1 < N - 1) {
            result = Math.max(result, 2 * (left[honeySpot] - left[p1]) + left[p1 + 1] - left[p2]);
            p1++;
        }
        // 3. p1 =0; p2 = N-1, honeySpot = 1 ~ N-2
        p1 = 0;
        p2 = N - 1;
        honeySpot = 1;
        while (honeySpot < N - 1) {
            result = Math.max(result, right[honeySpot] - right[p1] + left[honeySpot] - left[p2]);
            honeySpot++;
        }
        System.out.println(result);
    }
}
