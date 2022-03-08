import java.util.*;

public class BJ20437 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    while (T-- > 0) {
      String s = sc.next();
      int K = sc.nextInt();

      ArrayList<Integer>[] alphabet = new ArrayList[26];
      for (int i = 0; i < 26; i++) {
        alphabet[i] = new ArrayList<>();
      }

      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        alphabet[c - 'a'].add(i);
      }
      int ans = -1;
      int max = 0;
      for (ArrayList<Integer> a : alphabet) {
        if (a.size() < K) {
          continue;
        }
        int min = Integer.MAX_VALUE;
        for (int idx = 0; idx < a.size() - K + 1; idx++) {
          int gap = a.get(idx + K - 1) - a.get(idx) + 1;
          min = Integer.min(min, gap);
          max = Integer.max(max, gap);
        }
        ans = ans == -1 ? min : Integer.min(ans, min);
      }

      System.out.print(ans + " ");
      if (ans != -1) System.out.println(max);
    }
  }
}
