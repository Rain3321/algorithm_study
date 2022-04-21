import java.util.*;

public class BJ20366 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] snows = new int[N];
    for (int i = 0; i < N; i++) {
      snows[i] = sc.nextInt();
    }

    Arrays.sort(snows);
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < N - 3; i++) {
      for (int j = i + 3; j < N; j++) {

        int left = i + 1;
        int right = j - 1;

        while (left < right) {
          int anna = snows[i] + snows[j];
          int elza = snows[left] + snows[right];

          ans = Math.min(ans, Math.abs(anna - elza));

          if (anna > elza) { // 엘자의 크기가 더 크다 == 엘자가 더 커져야함 -> 왼쪽을 한칸 옮김
            left += 1;
          } else {
            right -= 1; // 안나가 더 크다 == 엘자가 작아져야함 -> 오른쪽을 한칸 옮김
          }
        }
      }
    }
    System.out.println(ans);
  }
}
