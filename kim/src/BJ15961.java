import java.util.Scanner;

public class BJ15961 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 회전초밥의 접시 수
        int d = sc.nextInt(); // 초밥의 가짓수
        int k = sc.nextInt(); // 먹는 접시수
        int c = sc.nextInt(); // 쿠폰번호

        int[] belt = new int[n]; // 현재 회전초밥 벨트
        int[] eat = new int[d + 1]; // 먹은 초밥의 개수
        for (int i = 0; i < n; i++) {
            belt[i] = sc.nextInt();
        }

        int curr = 0;
        // 맨 처음에 먹은 초밥
        for (int i = 0; i < k; i++) {
            if (eat[belt[i]]++ == 0) {
                curr++;
            }
        }

        int max = curr + ((eat[c] == 0) ? 1 : 0);

        // 초밥 먹기시작하는 위치
        for (int i = 1; i < n; i++) {
            int last = (i + n - 1) % n; // 직전 회전초밥의 인덱스번호
            // 이전에 먹은 초밥 빼주기
            if (--eat[belt[last]] == 0) {
                curr--;
            }

            // 다음에 먹을 초밥 더해주기
            if (eat[belt[(i + k - 1) % n]]++ == 0) {
                curr++;
            }
            max = Math.max(max, curr + ((eat[c] == 0) ? 1 : 0));
        }
        System.out.println(max);
    }
}
