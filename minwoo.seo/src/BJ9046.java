import java.io.*;

public class BJ9046 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    int[] alphabet;
    while (T-- > 0) {
      alphabet = new int[26];
      String s = br.readLine().replace(" ","");
      for (char c : s.toCharArray()) {
        alphabet[c-'a']++;
      }
      int max = 0, cnt = 0;
      int idx = 0;
      for (int i = 0; i < 26; i++) {
        if(max < alphabet[i]) {
          cnt = 0;
          max = alphabet[i];
          idx = i;
        }
        else if(max == alphabet[i]) {
          cnt++;
        }
      }
      if(cnt == 0)
        System.out.println((char)(idx+'a'));
      else
        System.out.println('?');
    }
  }
}
