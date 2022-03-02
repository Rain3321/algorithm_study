import java.io.*;
import java.util.ArrayList;

public class BOJ20437 {
    static char[] W;
    static ArrayList<Integer>[] counter;
    static int K, shortLen, longLen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            W = br.readLine().toCharArray();
            K = Integer.parseInt(br.readLine());
            if (K == 1) {
                sb.append("1 1\n");
                continue;
            }
            resetCounter();
            fillCounter();
            shortLen = Integer.MAX_VALUE;
            longLen = 0;
            for (int i = 0; i < 26; i++) {
                if (counter[i].size() >= K) {
                    getLength(i);
                }
            }
            if (shortLen == Integer.MAX_VALUE || longLen == 0) {
                sb.append(-1 + "\n");
            } else {
                sb.append(shortLen + " " + longLen + "\n");
            }
        }
        System.out.print(sb);
    }

    private static void getLength(int i) {
        ArrayList<Integer> alphaIdx = counter[i];
        int p1 = 0;
        int p2 = K - 1;
        while (p2 < alphaIdx.size()) {
            shortLen = Math.min(shortLen, alphaIdx.get(p2) - alphaIdx.get(p1) + 1);
            longLen = Math.max(longLen, alphaIdx.get(p2) - alphaIdx.get(p1) + 1);
            p1++;
            p2++;
        }
    }
    
    private static void resetCounter() {
        counter = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            counter[i] = new ArrayList<>();
        }
    }

    private static void fillCounter() {
        for (int i = 0; i < W.length; i++) {
            counter[W[i] - 'a'].add(i);
        }
    }
}
