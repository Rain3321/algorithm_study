import java.io.*;
import java.util.*;

public class BOJ2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        HashMap<Integer, Integer> numSums = new HashMap<>();
        int n, k;
        long result;
        int[] nums;
        int[] prefix;
        result = 0L;
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nums = new int[n + 1];
        prefix = new int[n + 1];
        nums[0] = 0;
        prefix[0] = 0;
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        numSums.put(0, 1);
        for (int i = 1; i <= n; i++) {
            result += (long)numSums.getOrDefault(prefix[i] - k, 0);
            int temp = numSums.getOrDefault(prefix[i], 0) + 1;
            numSums.put(prefix[i], temp);
        }
        bf.close();
        System.out.println(result);
    }
}
