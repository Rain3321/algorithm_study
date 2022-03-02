import java.io.*;
import java.util.*;

public class BOJ9466 {
    static int[] arr;
    static boolean[] discovered;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            result = 0;
            for (int i = 1; i <= n; i++) {
                if (arr[i] != -1) {
                    checkCircuit(i, new LinkedList<Integer>());
                }
            }
            sb.append(result + "\n");
        }
        System.out.print(sb);
    }

    private static void checkCircuit(int cur, Queue<Integer> students) {
        int next = arr[cur];
        arr[cur] = -1;
        students.add(cur);
        if (arr[next] != -1) {
            checkCircuit(next, students);
        } else {
            while (!students.isEmpty()) {
                int student = students.poll();
                if (student != next) {
                    result++;
                } else {
                    break;
                }
            }
        }
    }
}
