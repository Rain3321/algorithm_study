import java.util.Scanner;

public class BJ2564 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt(); // 가로
    int n = sc.nextInt(); // 세로

    int s = sc.nextInt();
    Point[] stores = new Point[s];

    for (int i = 0; i < s; i++) {
      stores[i] = new Point(sc.nextInt(), sc.nextInt(), n, m);
    }
    Point dongeun = new Point(sc.nextInt(), sc.nextInt(), n, m);

    int side = checkSide(dongeun.direction);
    int result = 0;
    for (Point store : stores) {
      if (store.direction == side) {
        if (store.direction == 1 || store.direction == 2) {
          result += Math.min(dongeun.y + n + store.y, (m - dongeun.y) + n + (m - store.y));
        } else {
          result += Math.min(dongeun.x + m + store.x, (n - dongeun.x) + m + (n - store.x));
        }
      } else {
        result += Math.abs(dongeun.x - store.x) + Math.abs(dongeun.y - store.y);
      }
    }
    System.out.println(result);
  }

  private static int checkSide(int me) {
    if (me == 1) {
      return 2;
    } else if (me == 2) {
      return 1;
    } else if (me == 3) {
      return 4;
    } else {
      return 3;
    }
  }

  private static class Point {
    int direction;
    int x;
    int y;

    public Point(int direction, int c, int n, int m) {
      this.direction = direction;
      switch (direction) {
        case 1:
          x = 0;
          y = c;
          break;
        case 2:
          x = n;
          y = c;
          break;
        case 3:
          x = c;
          y = 0;
          break;
        case 4:
          x = c;
          y = m;
      }
    }
  }
}
