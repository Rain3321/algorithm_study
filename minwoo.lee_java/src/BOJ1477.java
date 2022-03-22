import java.io.*;
import java.util.*;

public class BOJ1477 {
    static int N, M, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int[] restArea = new int[N + 2];
        restArea[0] = 0;
        restArea[N + 1] = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            restArea[i] = Integer.parseInt(st.nextToken());
        }
        sort(restArea);
        int[] gapList = new int[N + 1];
        setGatList(gapList, restArea);
        sort(gapList);

        int left = 1;
        int right = gapList[N];
        int result = 0;
        while (left <= right) {
            // M개의 휴게소를 짓고 난 후에 휴게소가 없는 구간의 최댓값의 최솟값
            int length = (right + left) / 2;
            // 새롭게 지은 휴게소의 개수
            int cnt = newAreaCount(length, gapList);
            if (cnt > M) {
                left = length + 1;
            } else {
                result = length;
                right = length - 1;
            }
        }
        System.out.println(result);
    }

    private static int newAreaCount(int length, int[] gapList) {
        int cnt = 0;
        for (int gap : gapList) {
            // 몫은 간격 내에 세울 수 있는 휴게소의 개수를 뜻한다.
            int share = gap / length;
            int remain = gap % length;
            cnt += share;
            // 나머지가 0인 경우는 이미 휴게소가 있는 곳이다.
            if (remain == 0) {
                cnt -= 1;
            }
        }
        return cnt;
    }

    private static void setGatList(int[] gapList, int[] restArea) {
        for (int i = 0; i < N + 1; i++) {
            gapList[i] = restArea[i + 1] - restArea[i];
        }
    }

    private static void sort(int[] arr) {
        Arrays.sort(arr);
    }
}
