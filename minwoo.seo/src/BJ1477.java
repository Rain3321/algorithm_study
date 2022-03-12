import java.util.*;

public class BJ1477 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int l = sc.nextInt();
    int[] arr = new int[n + 2];
    for (int i = 1; i < n + 1; i++) {
      arr[i] = sc.nextInt();
    }
    arr[0] = 0;
    arr[n + 1] = l;

    // 오름차순 정렬
    Arrays.sort(arr);

    // M 개의 휴게소를 세웠을 때 모든 거리를 그 최소값이 mid 라 가정
    // 이때 개수가 M개보다 크다면
    int left = 1;
    int right = l;
    int ans = 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      int count = 0;
      for (int i = 1; i < n + 2; i++) {

        int distance = arr[i] - arr[i - 1];
        count += distance / mid; // 휴게소 간격 / 거리
        if (distance % mid == 0) { // 나머지가 0 == 이미 휴게소가 존재한다
          count--;
        }
      }

      if (count > m) { // 개수가 더 많이 나온다 == 최소 거리를 더 늘려야한다.
        left = mid + 1;
      } else { // 개수가 작거나 같다 == 최소 거리가 더 작아도 된다.
        ans = mid;
        right = mid - 1;
      }

    }
    System.out.println(ans);

  }
}
