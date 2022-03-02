import java.util.*;

public class BJ2343 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int m = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    int left = 1;
    int right = 100000 * 10000 + 1;
    int ans = right;
    while (left <= right) {
      int mid = (right + left) / 2;
      int cnt = makeDisk(mid, arr);
      if (cnt > m) { // 현재 용량으로 블루레이의 개수가 더 많으면 용량을 늘려야함
        left = mid + 1;
      } else { // 현재 용량으로 블루레이의 개수가 더 적으면 용량을 줄여야함
        right = mid - 1;
        ans = Math.min(ans, mid);
      }
    }

    System.out.println(ans);

  }

  private static int makeDisk(int mid, int[] arr) {
    int cnt = 1;
    int length = 0;
    for (int j : arr) {
      if (j > mid) {
        return Integer.MAX_VALUE;
      }
      length += j;

      if (length > mid) {
        cnt++;
        length = j;
      }
    }
    return cnt;
  }
}
