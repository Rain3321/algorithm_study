import java.util.Scanner;

/**
 * 백준 14938 서강그라운드
 * https://www.acmicpc.net/problem/14938
 */
public class BJ14938 {
  private static final int MAX = 1501; // 한 점에서 갈 수 있는 최대 거리, 100 * 15 + 1

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int r = sc.nextInt();

    int[] value = new int[n + 1];
    for (int i = 1; i < n + 1; i++) {
      value[i] = sc.nextInt();
    }

    int[][] adjArr = new int[n + 1][n + 1];

    // 최대값 적용
    for (int i = 0; i < n + 1; i++) {
      for (int j = 0; j < n + 1; j++) {
        adjArr[i][j] = MAX;
      }
    }

    for (int i = 0; i < r; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      int l = sc.nextInt();
      adjArr[a][b] = l;
      adjArr[b][a] = l;
    }
    // 초기화 완료

    // 플로이드 와샬 알고리즘 적용

    for (int k = 1; k < n + 1; k++) {
      for (int i = 1; i < n + 1; i++) {
        for (int j = 1; j < n + 1; j++) {
          if (adjArr[i][k] != MAX && adjArr[k][j] != MAX) {
            adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);
          }
        }
      }
    }

    // 최대값 찾기, 이때 자기 자신에게 돌아오는 수도 포함되어 있으므로 제외
    int ans = 0;
    for (int start = 1; start < n + 1; start++) {
      int temp = value[start];
      for (int end = 1; end < n + 1; end++) {
        if (start != end) {
          if (adjArr[start][end] <= m) {
            temp += value[end];
          }
        }
      }
      ans = Math.max(ans, temp);
    }
    System.out.println(ans);
  }
}
