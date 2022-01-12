import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17609 {
    public static int palindrome(char[] arr, int start, int end, int cnt) {
        if (cnt >= 2) {
            return cnt;
        }
        while (start <= end) {
            if (arr[start] == arr[end]) {
                start++;
                end--;
            } else {
                cnt = Math.min(palindrome(arr, start + 1, end, cnt + 1), palindrome(arr, start, end - 1, cnt + 1));
                return cnt;
            }
        }
        return cnt;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int result = 0;
        for (int i = 0; i < T; i++) {
            char[] arr = bf.readLine().toCharArray();
            result = palindrome(arr, 0, arr.length - 1, 0);
            System.out.println(result);
        }
    }
}
