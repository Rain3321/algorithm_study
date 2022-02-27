import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2138 {
    private static char[] arr1, arr2;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        arr1 = s.toCharArray();
        arr2 = br.readLine().toCharArray();

        // 첫 번째 스위치 안누르고 해보기
        int cnt = go(0);

        int min = -1;
        if (check())
            min = cnt;

        arr1 = s.toCharArray();
        // 첫 번째 스위치 누르기
        on(0);
        cnt = go(1);
        if (check())
            min = (min == -1) ? cnt : Math.min(cnt, min);

        System.out.println(min);
    }

    private static void on(int i) {
        if (i - 1 >= 0)
            arr1[i - 1] = (arr1[i - 1] == '1') ? '0' : '1';
        arr1[i] = (arr1[i] == '1') ? '0' : '1';
        if (i + 1 < n)
            arr1[i + 1] = (arr1[i + 1] == '1') ? '0' : '1';
    }

    private static boolean check() {
        for (int i = 0; i < n; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    private static int go(int cnt) {
        for (int i = 1; i < n; i++) {
            if (arr1[i - 1] != arr2[i - 1]) {
                // 스위치 누르기
                on(i);
                cnt++;
            }
        }
        return cnt;
    }
}
