// Link: http://boj.kr/7ef52273ed4544d0ab51e88facb589b3

import java.util.ArrayList;
import java.util.Scanner;

public class BJ15683 {
    static int N;
    static int M;
    static int ans = Integer.MAX_VALUE;
    static ArrayList<CCTV> cctvList = new ArrayList<>();

    // CCTV 머리의 방향
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
                if(arr[i][j] != 0 && arr[i][j] != 6)
                    cctvList.add(new CCTV(i, j, arr[i][j]));
            }
        }
        dfs(arr, 0);
        System.out.println(ans);
    }

    /**
     * 깊이 우선 탐색을 위한 재귀 메소드.
     * 각 cctv 의 방향에 따른 볼 수 있는 모든 방향을 마킹하여 모든 cctv 를 마킹했을 때
     * 사각지대의 개수(0의 개수)를 세어 최소값을 ans 에 저장함.
     *
     * @param arr 이번 재귀 깊이의 arr
     * @param k 이번에 찾을 cctv 의 인덱스
     */
    private static void dfs(int[][] arr, int k) {
        if(k == cctvList.size()) {
            ans = Math.min(ans, count(arr));
            return;
        }
        for (int d = 0; d < 4; d++) {
            int[][] temp = marking(cctvList.get(k), d, arr);
            dfs(temp, k+1);
        }
    }

    /**
     * cctv가 볼수 있는 방향을 마킹하여 배열을 반환한다.
     * 백트래킹을 위해 새로운 배열을 만들어 마킹한다.
     * 자바의 경우 배열은 힙 영역에 저장되기 때문에 파라미터로 받은 arr를 그대로 색칠하게 되면
     * 이 함수를 호출하는 부분의 배열 역시 변경되기 때문이다.
     *
     * @param cctv 방향을 찾아야 하는 cctv
     * @param d cctv 머리가 바라보는 방향
     * @param arr 기존 배열
     * @return 기존 배열에서 cctv 가 바라보는 방향을 마킹한 새로운 배열
     */
    private static int[][] marking(CCTV cctv, int d, int[][] arr) {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            if (M >= 0) System.arraycopy(arr[i], 0, temp[i], 0, M);
        }

        ArrayList<Integer> directionList = find(cctv.type, d);
        for (int direction : directionList) {
            int nx = cctv.x + dx[direction];
            int ny = cctv.y + dy[direction];
            while (nx >= 0 && nx < N && ny >= 0 && ny < M && temp[nx][ny] != 6) {
                if(temp[nx][ny] != 7) {
                   temp[nx][ny] = 7;
                }
                nx += dx[direction];
                ny += dy[direction];
            }
        }
        return temp;
    }

    /**
     * CCTV의 방향과 타입에 따라 어느 방향을 탐색할 수 있는지 리스트로 담아 반환한다.
     * 방향의 기준은 dx,dy에서 정의한 것과 같이
     * → ↓ ← ↑ 순서이다.
     *
     * @param type CCTV의 타입
     * @param d CCTV 머리의 방향
     * @return CCTV가 볼 수 있는 모든 방향
     */
    private static ArrayList<Integer> find(int type, int d) {
        ArrayList<Integer> list = new ArrayList<>();
        switch (type) {
            case 1:
                list.add(d);
                break;
            case 2:
                list.add(d);
                list.add((d+2)%4);
                break;
            case 3:
                list.add(d);
                list.add((d+3)%4);
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    if(i == (d+1) % 4) continue;
                    list.add(i);
                }
                break;
            case 5:
                for (int i = 0; i < 4; i++) {
                    list.add(i);
                }
                break;
        }
        return list;
    }

    /**
     * 배열에서 0의 개수를 세서 반환한다.
     *
     * @param arr cctv 가 설치된 배열
     * @return cctv 가 탐색하지 못하는(0) 지역의 개수
     */
    private static int count(int[][] arr) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(arr[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static class CCTV {
        int x, y, type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
