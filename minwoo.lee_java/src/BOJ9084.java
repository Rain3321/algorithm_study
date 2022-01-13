import java.util.Scanner;

public class BOJ9084 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int n, m;
        int[] arr;
        while (T > 0) {
            n = sc.nextInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            m = sc.nextInt();
            int[] dp = new int[m + 1];
            dp[0] = 1;
            for (int coin : arr) {
                for (int value = 1; value < m + 1; value++) {
                    if (coin <= value) {
                        dp[value] = dp[value] + dp[value - coin];
                    }
                }
            }
            System.out.println(dp[m]);


            T--;
        }
    }
}
