import java.util.*;

public class BJ15787 {
  private static int[] train;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    train = new int[N + 1];


    for (int i = 0; i < M; i++) {
      int command = sc.nextInt();

      switch (command) {
        case 1:
          getOn(sc.nextInt(), sc.nextInt() - 1);
          break;
        case 2:
          getOff(sc.nextInt(), sc.nextInt() - 1);
          break;
        case 3:
          goBack(sc.nextInt());
          break;
        case 4:
          goStraight(sc.nextInt());
          break;
      }
    }
    int size = (int) (Math.pow(2, 20)  + 1);
    boolean[] visited = new boolean[size];
    int result = 0;
    for (int i = 1; i <= N; i++) {
      if (visited[train[i]]) {
        continue;
      }
      visited[train[i]] = true;
      result++;
    }
    System.out.println(result);
  }

  private static void getOn(int cur, int seat) {
    train[cur] = train[cur] | (1 << seat);
  }

  private static void getOff(int cur, int seat) {
    train[cur] = train[cur] & ~(1 << seat);
  }

  private static void goBack(int cur) {
    train[cur] = (train[cur] << 1) & ~(1 << 20);
  }

  private static void goStraight(int cur) {
    train[cur] = train[cur] >>> 1;
  }
}
