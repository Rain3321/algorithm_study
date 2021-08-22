import java.util.Scanner;

/**
 * 사전순이면 무조건 A가 올수있으면 무조건 A부터 넣자
 *
 */
public class BJ1343 {
  static final String[] BOARD = {"XXXX", "XX"};
  static final String[] POLIO = {"AAAA", "BB"};
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String input = sc.next();
    int N = input.length();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      char c = input.charAt(i);
      if(c == '.') sb.append(c);
      else {
        if(i+4 <= N) {
          if(input.substring(i, i+4).equals(BOARD[0])) {
            sb.append(POLIO[0]);
            i += 3;
          }
          else if (input.substring(i, i+2).equals(BOARD[1])) {
            sb.append(POLIO[1]);
            i++;
          }
          else {
            System.out.println(-1);
            return;
          }
        }
        else if(i+2 <= N) {
          if (input.substring(i, i+2).equals(BOARD[1])) {
            sb.append(POLIO[1]);
            i++;
          }
          else {
            System.out.println(-1);
            return;
          }
        }
        else {
          System.out.println(-1);
          return;
        }
      }
    }
    System.out.println(sb);
  }
}
