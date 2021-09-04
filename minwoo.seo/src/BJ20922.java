import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        int[] num = new int[100001];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int start = 1, end = 1;
        int maxLength = 0;
        while(start <= end && end <= N) {
            while(end <= N && num[arr[end]] < K) {
                num[arr[end]]++;
                maxLength = Math.max(maxLength, end - start +1);
                end++;
            }
            while(start <  end) {
                if(num[arr[start]] == K) {
                    num[arr[start]]--;
                    start++;
                    break;
                }
                num[arr[start]]--;
                start++;
            }
        }
        System.out.println(maxLength);
    }
}
