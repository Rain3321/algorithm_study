import java.util.Scanner;

public class BOJ2564 {
    static int w, h;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        w = sc.nextInt();
        h = sc.nextInt();
        int storeCnt = sc.nextInt();
        int[][] location = new int[storeCnt + 2][2];
        for (int i = 1; i <= storeCnt + 1; i++) {
            int direction = sc.nextInt();
            int distance = sc.nextInt();
            switch (direction) {
                case 1:
                    location[i] = new int[]{0, distance};
                    break;
                case 2:
                    location[i] = new int[]{h, distance};
                    break;
                case 3:
                    location[i] = new int[]{distance, 0};
                    break;
                case 4:
                    location[i] = new int[]{distance, w};
                    break;
            }
        }
        int distanceSum = 0;
        for (int i = 1; i <= storeCnt; i++) {
            distanceSum += getDistance(location[i], location[storeCnt + 1]);
        }
        System.out.println(distanceSum);
    }

    private static int getDistance(int[] store, int[] dongguen) {
        int minDistance = 0;
        int heightGap = Math.abs(store[0] - dongguen[0]);
        int widthGap = Math.abs(store[1] - dongguen[1]);
        // 상점과 동근의 위치가 북,남 또는 남,북 인경우
        if (heightGap == h) {
            // 왼쪽 방향에서 만나는 경우
            int left = store[1] + dongguen[1];
            // 오른쪽 방향에서 만나는 경우
            int right = 2 * w - left;
            minDistance = h + Math.min(left, right);
        }
        // 상점과 동근의 위치가 북,남 또는 남,북 인경우
        else if (widthGap == w) {
            // 위에서 만나는 경우
            int up = store[0] + dongguen[0];
            // 아래에서 만나는 경우
            int down = 2 * h - up;
            minDistance = w + Math.min(up, down);
        }
        // 서로 반대 방향이 아니면 두 좌표사이의 거리를 구하는 공식을 사용한다.
        else {
            minDistance = heightGap + widthGap;
        }
        return minDistance;
    }

}
