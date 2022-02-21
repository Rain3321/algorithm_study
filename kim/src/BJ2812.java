import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String s = br.readLine();

        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        int cnt = 0;

        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);

            while (!stack.isEmpty() && cnt < k && stack.peek() < c) {
                // 전이 더 작으면 전에거를 제거
                stack.pop();
                cnt++;
            }
            // 아니면 그냥 이어붙여줌
            stack.push(c);
        }

        // 문자열이 끝났는데 아직 지워야 할 수가 남아있으면 끝부터 지워준다.
        while (cnt < k) {
            stack.pop();
            cnt++;
        }

        // 스택을 역순으로 출력해주면 끝
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        System.out.println(sb.reverse());
    }
}
