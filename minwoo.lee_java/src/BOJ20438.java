import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20438 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N, K, Q, M, s, e, studentNum;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        boolean[] isSleep = new boolean[N + 3];
        int[] prefixSum = new int[N + 3];

        // 자는 학생들 체크 이 인원들은 QR을 받아도 배수인 사람들에게 전달하지 않는다.
        st = new StringTokenizer(bf.readLine());
        while (K-- > 0) {
            isSleep[Integer.parseInt(st.nextToken())] = true;
        }
        st = new StringTokenizer(bf.readLine());

        // QR을 받는 사람 중 자는 사람이면 contiue를 통해 배수 사람에게 전달하는 과정을 진행하지 않음
        while (Q-- > 0) {
            studentNum = Integer.parseInt(st.nextToken());
            if (isSleep[studentNum]) {
                continue;
            }
            // 잠을 자지않은 QR을 받는 인원이 배수로 QR 전달하는데 잠을 자지 않는 인원에게만 QR을 받았다는 뜻인 1부여 
            for (int i = studentNum; i <= N + 2; i += studentNum) {
                if (!isSleep[i]) {
                    prefixSum[i] = 1;
                }
            }
        }
        // 3번 학생부터 12번 학생까지 QR을 받은 학생들에 대한 누적합 구하기
        for (int i = 3; i <= N + 2; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }
        while (M-- > 0) {
            st = new StringTokenizer(bf.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            // 출석이 되지 않은 사람은 s 부터 e 사람 수에서 s부터 e의 누적값을 빼주면된다.
            System.out.println(e - s + 1 - (prefixSum[e] - prefixSum[s - 1]));
        }
    }
}
