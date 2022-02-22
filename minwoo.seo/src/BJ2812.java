import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2812 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    List<Integer> arr = new ArrayList<>();
    for (char c : br.readLine().toCharArray()) {
      arr.add(c - '0');
    }

    LinkedList<Integer> ans = new LinkedList<>();
    for (int c : arr) {
      while (k > 0 && !ans.isEmpty() && c > ans.peekLast()) {
        ans.pollLast();
        k--;
      }
      ans.add(c);
    }

    while (k > 0) {
      ans.pollLast();
      k--;
    }

    ans.forEach(System.out::print);
  }
}