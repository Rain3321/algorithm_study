import java.io.*;
import java.util.*;

public class BOJ15961 {
    static int N, d, k, c;
    static int[] dish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        dish = new int[N + k - 1];
        for (int i = 0; i < N; i++) {
            dish[i] = Integer.parseInt(br.readLine());
        }
        for (int i = N, j = 0; i < N + k - 1; i++) {
            dish[i] = dish[j++];
        }
        int result = eat();
        System.out.println(result);
    }


    private static int eat() {
        int[] isEat = new int[d + 1];
        // 쿠폰에 대한 카운팅 증가
        isEat[c] = 1;
        int cnt = 1;
        // 첫번째 경우(index : 0 ~ K-1)에 대한 먹은 초밥의 가짓 수 구하기
        for (int i = 0; i < k; i++) {
            if (++isEat[dish[i]] == 1) {
                cnt++;
            }
        }
        int p1 = 0;
        int p2 = k;
        int result = cnt;
        while (p2 < N + k - 1) {
            if (--isEat[dish[p1]] == 0) {
                cnt--;
            }
            if (++isEat[dish[p2]] == 1) {
                cnt++;
            }
            result = Math.max(result, cnt);
            p1++;
            p2++;
        }
        return result;
    }
}
