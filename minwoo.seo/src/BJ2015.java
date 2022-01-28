import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BJ2015 {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int K = sc.nextInt();

    int[] arr = new int[N + 1];
    for (int i = 1; i < N + 1; i++) {
      arr[i] = sc.nextInt();
    }
    int[] sum = new int[N + 1];
    Map<Integer, Long> map = new HashMap<>();

    long ans = 0;
    for (int i = 1; i < N + 1; i++) {
      sum[i] = arr[i] + sum[i - 1];
      if (sum[i] == K) {
        ans++;
      }
      ans += map.getOrDefault(sum[i] - K, 0L);
      map.put(sum[i], map.getOrDefault(sum[i], 0L) + 1);
    }
    System.out.println(ans);
  }
}
