import java.util.*;

public class BJ1717 {
  static int[] set;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    set = new int[n + 1];

    for (int i = 1; i <= n; i++) {
      set[i] = i;
    }

    StringBuilder sb = new StringBuilder();
    while(m-- > 0) {
      int function = sc.nextInt();
      int a = sc.nextInt();
      int b = sc.nextInt();
      if (function == 0) {
        union(a, b);
      } else {
        if (check(a, b)) {
          sb.append("YES\n");
        } else {
          sb.append("NO\n");
        }
      }
    }
    System.out.println(sb);
  }

  private static void union(int a, int b) {
    int ac = find(a);
    int bc = find(b);

    set[bc] = ac;
  }

  private static int find(int a) {
    if (set[a] == a) {
      return a;
    }
    return set[a] = find(set[a]);
  }

  private static boolean check(int a, int b) {
    if (find(a) == find(b)) {
      return true;
    } else {
      return false;
    }
  }
}
