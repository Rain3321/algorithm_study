import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BJ18808 {
  private static int[][] map;
  private static int n, m;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    int k = sc.nextInt();
    List<int[][]> stickers = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      int r = sc.nextInt();
      int c = sc.nextInt();
      int[][] arr = new int[r][c];
      for (int j = 0; j < r; j++) {
        for (int l = 0; l < c; l++) {
          arr[j][l] = sc.nextInt();
        }
      }
      stickers.add(arr);
    }

    map = new int[n][m];

    for (int[][] sticker : stickers) {
      for (int degree : new int[]{0, 90, 180, 270}) {
        int[][] s = turn(sticker, degree);
        if (checkAvailablePlace(s)) {
          fill(s);
          break;
        }
      }
    }

    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] == 1) {
          count++;
        }
      }
    }
    System.out.println(count);
  }

  private static void fill(int[][] sticker) {
    int r = sticker.length;
    int c = sticker[0].length;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (sticker[i][j] == 1) {
          map[i + startX][j + startY] = 1;
        }
      }
    }
  }

  private static int[][] turn(int[][] arr, int degree) {
    int[][] rotate = new int[0][];
    int n = arr.length;
    int m = arr[0].length;

    switch (degree) {
      case 90:
      case 270:
        rotate = new int[m][n];
        break;
      case 0:
      case 180:
        rotate = new int[n][m];
        break;
    }

    for (int i = 0; i < rotate.length; i++) {
      for (int j = 0; j < rotate[i].length; j++) {
        switch (degree) {
          case 0:
            rotate[i][j] = arr[i][j];
            break;
          case 90:
            rotate[i][j] = arr[n - 1 - j][i];
            break;
          case 180:
            rotate[i][j] = arr[n - 1 - i][m - 1 - j];
            break;
          case 270:
            rotate[i][j] = arr[j][m - 1 - i];
            break;
        }
      }
    }

    return rotate;
  }

  private static int startX, startY;

  private static boolean checkAvailablePlace(int[][] sticker) {
    int r = sticker.length;
    int c = sticker[0].length;
    for (int i = 0; i < n - r + 1; i++) {
      for (int j = 0; j < m - c + 1; j++) {

        if (canAttach(sticker, i, j)) {
          startX = i;
          startY = j;
          return true;
        }
      }
    }
    return false;
  }

  // 지정된 칸에서 스티커가 붙어질 수 있는지 확인
  private static boolean canAttach(int[][] sticker, int i, int j) {
    int r = sticker.length;
    int c = sticker[0].length;
    for (int x = 0; x < r; x++) {
      for (int y = 0; y < c; y++) {
        if (sticker[x][y] == 1) {
          if (map[x + i][y + j] != 0) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
