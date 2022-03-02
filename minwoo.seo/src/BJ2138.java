import java.util.*;

public class BJ2138 {
  private static boolean[] before, after;
  private static int n;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    before = new boolean[n];
    after = new boolean[n];
    int idx = 0;
    for (char c : sc.next().toCharArray()) {
      before[idx++] = (c != '0'); // 0 이면 false, 1이면 true
    }
    idx = 0;
    for (char c : sc.next().toCharArray()) {
      after[idx++] = (c != '0'); // 0 이면 false, 1이면 true
    }
    boolean[] temp = new boolean[n];
    System.arraycopy(before, 0, temp, 0, n);

    // 0번째 스위치를 누를 경우
    turn(0);
    int ans1 = 1; // 이미 한번 눌렀으니까 1부터 시작
    int ans = -1;
    for (int i = 1; i < n; i++) {
      if (check(i)) { // 이전 위치가 같지 않으면
        turn(i);
        ans1++;
      }
    }
    // 상태 체크
    if (Arrays.equals(before, after)) {
      ans = ans1;
    }

    // 다시 초기화
    System.arraycopy(temp, 0, before, 0, n);
    // 0번째 스위치를 누르지 않을 경우
    int ans2= 0; // 누르지 않았으니까 0부터 시작
    for (int i = 1; i < n; i++) {
      if (check(i)) { // 이전 위치가 같지 않으면
        turn(i);
        ans2++;
      }
    }
    // 상태 체크
    if (Arrays.equals(before, after)) {
      if (ans == -1) {
        ans = ans2;
      }
      else {
        ans = Math.min(ans, ans2);
      }
    }

    System.out.println(ans);
  }
  private static void turn(int i) {
    if (i - 1 >= 0) {
      before[i - 1] = !before[i - 1];
    }
    before[i] = !before[i];

    if (i + 1 < n) {
      before[i + 1] = !before[i + 1];
    }
  }

  // 이전 위치가 다른지 체크
  private static boolean check(int i) {
    return before[i - 1] != after[i - 1];
  }
}
