import java.io.*;
import java.util.*;

public class BOJ17135 {
    static int N, M, D, defenseCnt;
    static int[][] castle;
    static int[] location;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        castle = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 궁수를 세명 배치할 수 있으므로 크기가 3인 배열을 생성
        location = new int[3];
        setLocation(0, 0, 3);
        System.out.println(defenseCnt);

    }

    // 궁수 세명의 위치를 구하기 위한 재귀 함수
    private static void setLocation(int start, int setCnt, int archerCnt) {
        if (setCnt == archerCnt) {
            // 세 명의 궁수 위치가 정해졌다면 적을 잡는 defense 함수 실행
            defense();
            return;
        }
        for (int l = start; l < M; l++) {
            location[setCnt] = l;
            setLocation(l + 1, setCnt + 1, archerCnt);
        }
    }

    private static void defense() {
        int[][] copyCastle = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(castle[i], 0, copyCastle[i], 0, M);
        }
        int cnt = 0;
        int n = N;
        while (n-- > 0) {
            for (int l = 0; l < 3; l++) {
                // 최소 거리 값
                int minDistance = Integer.MAX_VALUE;
                // 궁수가 잡는 적 위치를 나타내는 R,C 값
                int minR = Integer.MAX_VALUE;
                int minC = Integer.MAX_VALUE;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        // 궁수들은 서로 같은 적을 잡을 수 있으므로 앞에서 잡은 적을 표시하는 -1인 경우도 조건에 포함된다.
                        if (castle[i][j] == 1 || castle[i][j] == -1) {
                            int distance = getDistance(i, j, l);
                            // 잡은 적과 궁수의 거리가 기존의 최소 값보다 작은 경우 또는 거리는 같지만 더 왼쪽에 있는 경우
                            if (minDistance > distance || (minDistance == distance && minC > j)) {
                                // 잡은 적위치를 갱신
                                minR = i;
                                minC = j;
                            }
                            // 거리의 최솟값 계산
                            minDistance = Math.min(minDistance, distance);
                        }
                    }
                }
                if (minDistance <= D) {
                    // 잡은 적을 나타내는 배열을 따로 만들지 않고 -1 값으로 변경
                    castle[minR][minC] = -1;
                }
            }
            cnt += getCount();
            moveEnemy();
        }
        defenseCnt = Math.max(defenseCnt, cnt);
        castle = copyCastle;
    }

    private static void moveEnemy() {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (i == 0) {
                    castle[i][j] = 0;
                } else {
                    castle[i][j] = castle[i - 1][j];
                }
            }
        }
    }

    private static int getCount() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (castle[i][j] == -1) {
                    cnt++;
                    castle[i][j] = 0;
                }
            }
        }
        return cnt;
    }

    private static int getDistance(int row, int col, int l) {
        return (Math.abs(row - N) + Math.abs(col - location[l]));
    }
}
