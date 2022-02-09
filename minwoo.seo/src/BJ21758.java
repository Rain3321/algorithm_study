import java.util.*;

public class BJ21758 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] honey = new int[N];
    int sum = 0;
    for (int i = 0; i < N; i++) {
      honey[i] = sc.nextInt();
      sum += honey[i];
    }

    int ans = 0;
    // 1. 벌 벌 꿀통
    // -> 벌1은 왼쪽 끝, 꿀통은 오른쪽 끝에 고정
    int bee = 1;
    int result = sum * 2 - (honey[0] + honey[bee]) * 2;
    int next = result;

    while (++bee < N - 1) {
      next += honey[bee - 1] - honey[bee] * 2;
      result = Math.max(next, result);
    }
    ans = Math.max(ans, result);

    // 2. 꿀통 벌 벌
    // 벌1은 오른쪽 끝, 꿀통은 왼쪽 끝에 고정
    bee = N - 2;
    result = sum * 2 - (honey[N - 1] + honey[bee]) * 2;
    next = result;

    while (--bee > 0) {
      next += honey[bee + 1] - honey[bee] * 2;
      result = Math.max(result, next);
    }
    ans = Math.max(ans, result);

    // 3. 벌 꿀통 벌
    // 두 벌은 양끝 고정, 꿀통은 꿀 값이 가장 높은 위치
    int h = 0;
    for (int i = 1; i < N - 1; i++) {
      h = Math.max(h, honey[i]);
    }
    // 값은 전체 합산 값에서 출발 위치 뺴고 꿀값 2개
    result = sum - (honey[0] + honey[N - 1]) + h;

    ans = Math.max(ans, result);

    System.out.println(ans);
  }
}
