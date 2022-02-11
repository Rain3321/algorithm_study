import java.io.*;
import java.util.TreeMap;

public class BOJ4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, Integer> wordCnt = new TreeMap<>();
        String word;
        int allCnt = 0;
        while ((word = br.readLine()) != null) {
            allCnt++;
            int cnt = wordCnt.getOrDefault(word, 0);
            wordCnt.put(word, cnt + 1);
        }
        for (String key : wordCnt.keySet()) {
            System.out.printf("%s %.4f\n", key, (100.0 * wordCnt.get(key) / allCnt));
        }
    }
}
