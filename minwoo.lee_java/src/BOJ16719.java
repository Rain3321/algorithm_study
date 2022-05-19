import java.io.*;

public class BOJ16719 {
    static char[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        result = new char[str.length()];
        solution(str, 0, str.length());
        System.out.print(sb);
    }

    private static void solution(String str, int from, int to) {
        if (from == str.length() || from == to) {
            return;
        }
        int idx = getMinIdx(str, from, to);
        char minAlpha = str.charAt(idx);
        result[idx] = minAlpha;
        getStr();
        solution(str, idx + 1, to);
        solution(str, from, idx);
    }

    private static int getMinIdx(String str, int from, int to) {
        int idx = from;
        for (int i = idx + 1; i < to; i++) {
            if (str.charAt(idx) > str.charAt(i)) {
                idx = i;
            }
        }
        return idx;
    }

    private static void getStr() {
        for (int i = 0; i < result.length; i++) {
            if (result[i] != '\u0000'){
                sb.append(result[i]);
            }
        }
        sb.append("\n");

    }


}
