import java.util.*;

public class BJ9046 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    sc.nextLine();
    while (T-- > 0) {
      int[] alphabet = new int[26];
      String s = sc.nextLine().replace(" ","");
      int max = 0, cnt = 0, idx = 0;
      for (int i = 0; i < s.length(); i++) {
        int x = ++alphabet[s.charAt(i)-'a'];
        if(max < x) {
          cnt = 0;
          max = x;
          idx = s.charAt(i)-'a';
        }
        else if(max == x) cnt++;
      }
      if(cnt == 0) System.out.println((char)(idx+'a'));
      else System.out.println('?');
    }
  }
}
