import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());

            Queue<Integer>[] qarr = new LinkedList[26]; // 짧은 길이를 구하기 위한 큐, 해당 알파벳이 나온 인덱스를 저장
            for (int i = 0; i < 26; i++)
                qarr[i] = new LinkedList<>();
            int min = -1;
            int max = -1;
            boolean flag = false;

            for (int i = 0; i < w.length(); i++) {
                int c = w.charAt(i) - 'a';
                qarr[c].offer(i);

                if (qarr[c].size() >= k) {
                    int len = i - qarr[c].poll() + 1;
                    min = (min == -1) ? len : Math.min(min, len);
                    max = Math.max(max, len);
                    flag = true;
                }
            }
            System.out.println((flag ? (min + " " + max) : -1));
        }
    }
}
