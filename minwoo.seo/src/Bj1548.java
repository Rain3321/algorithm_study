import java.util.*;

public class Bj1548 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] arr = new int[N];

    for (int i = 0; i < N; i++) {
      arr[i] = sc.nextInt();
    }

    // 오름차순 정렬
    Arrays.sort(arr);

    int max = 1;
    for (int start = 0; start < N - 1; start++) {
      for (int end = N - 1; end >= 0; end--) {
        if (end == start + 1) break;
        if (arr[start] + arr[start+ 1] > arr[end]) {
          max = Math.max(max, end - start + 1);
        }
      }
    }
    if (max == 1 && N >= 2) {
      max = 2;
    }

    System.out.println(max);
    sc.close();
  }
}
