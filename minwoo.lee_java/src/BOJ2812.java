import java.util.*;
import java.io.*;

public class BOJ2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int printCnt = N - k;
        char[] nums = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            while (!stack.isEmpty() && k != 0) {
                if (stack.peek() >= nums[i]) {
                    break;
                }
                stack.pop();
                k--;
            }
            stack.push(nums[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < printCnt; i++) {
            sb.append(stack.get(i));
        }
        System.out.println(sb);
    }
}
