import java.util.Scanner;

public class BJ2564 {
    private static int w, h;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        w = sc.nextInt();
        h = sc.nextInt();
        int n = sc.nextInt();
        int[][] arr = new int[n + 1][2];
        int ans = 0;

        for (int i = 0; i <= n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        } // end Input

        for (int i = 0; i < n; i++)
            ans += getDist(arr[n][0], arr[n][1], arr[i][0], arr[i][1]);
        System.out.println(ans);
    }

    private static int getDist(int nowdir, int nowdist, int nextdir, int nextdist) {
        // 같은 열
        if (nowdir == nextdir)
            return Math.abs(nowdist - nextdist);

        switch (nowdir) {
            // 출발 위치
            case 1:
                // 북쪽
                if (nextdir == 2)
                    return h + Math.min(nowdist + nextdist, 2 * w - nowdist - nextdist);
                else if (nextdir == 3)
                    return nowdist + nextdist;
                else
                    return w - nowdist + nextdist;
            case 2:
                // 남쪽
                if (nextdir == 1)
                    return h + Math.min(nowdist + nextdist, 2 * w - nowdist - nextdist);
                else if (nextdir == 3)
                    return nowdist + h - nextdist;
                else
                    return w - nowdist + h - nextdist;
            case 3:
                // 서쪽
                if (nextdir == 1)
                    return nowdist + nextdist;
                else if (nextdir == 2)
                    return h - nowdist + nextdist;
                else
                    return w + Math.min(nowdist + nextdist, 2 * h - nowdist - nextdist);
            default:
                // 동쪽
                if (nextdir == 1)
                    return w - nextdist + nowdist;
                else if (nextdir == 2)
                    return w - nextdist + h - nowdist;
                else
                    return w + Math.min(nowdist + nextdist, 2 * h - nowdist - nextdist);
        }
    }
}
