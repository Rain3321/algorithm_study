import java.io.*;
import java.util.*;

public class BJ20366 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = parse(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = parse(st.nextToken());
        }

        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum1 = arr[i] + arr[j];
                int l = 0;
                int r = n - 1;

                while (l < r) {
                    if (l == i || l == j) {
                        l++;
                        continue;
                    }
                    if (r == i || r == j) {
                        r--;
                        continue;
                    }
                    int sum2 = arr[l] + arr[r];
                    min = Math.min(Math.abs(sum2 - sum1), min);
                    if (sum2 - sum1 > 0) {
                        // sum2가 더 큰 경우
                        // 오른쪽 포인터를 줄여준다
                        r--;
                    } else if (sum2 - sum1 < 0) {
                        l++;
                    } else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(min);
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
