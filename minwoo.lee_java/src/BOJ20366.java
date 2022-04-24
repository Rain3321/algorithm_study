import java.io.*;
import java.util.*;

public class BOJ20366 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] diameter = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            diameter[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(diameter);
        int minGap = Integer.MAX_VALUE;
        for (int i = 0; i < N - 3; i++) {
            for (int j = N - 1; j > i + 2; j--) {
                int left = i + 1;
                int right = j - 1;
                while (left < right) {
                    // 엘자의 눈사람 키 - 안나 눈사람 키
                    int gap = diameter[i] + diameter[j] - diameter[left] - diameter[right];
                    // 키 차이가 작은 경우 갱신
                    minGap = Math.min(minGap, Math.abs(gap));
                    // 엘자의 눈사람 키가 더 작으면 안나의 눈사람의 크기가 작아져야 하므로 right 값을 감소시킨다.
                    if (gap < 0) {
                        right--;
                    } else {
                        // 엘자의 눈사람 키가 더 크면 안나의 눈사람의 크기가 커져야 하므로 left 값을 증가시킨다.
                        left++;
                    }
                }
            }
        }
        System.out.println(minGap);
    }
}
