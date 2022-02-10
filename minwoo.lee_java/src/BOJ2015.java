import java.io.*;
import java.util.*;

public class BOJ2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        HashMap<Integer, Integer> numSums = new HashMap<>();
        long result = 0L;
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] nums = new int[n + 1];
        nums[0] = 0;

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        numSums.put(0, 1);
        for (int i = 1; i <= n; i++) {
            result += (long) numSums.getOrDefault(prefixSum[i] - k, 0);
            int temp = numSums.getOrDefault(prefixSum[i], 0) + 1;
            numSums.put(prefixSum[i], temp);
        }
        bf.close();
        System.out.println(result);
    }
}
