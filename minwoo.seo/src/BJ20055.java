import java.util.LinkedList;
import java.util.Scanner;

public class BJ20055 {
    static int N, K;
    static LinkedList<Place> upperConveyor;
    static LinkedList<Place> lowerConveyor;
    static class Place {
        int w;
        boolean isRobot;

        public Place(int w, boolean isRobot) {
            this.w = w;
            this.isRobot = isRobot;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        upperConveyor = new LinkedList<>();
        lowerConveyor = new LinkedList<>();

        // 초기화
        for (int i = 0; i < N; i++) {
            upperConveyor.add(new Place(sc.nextInt(), false));
        }
        //
        for (int i = N-1; i >= 0; i--) {
            lowerConveyor.addFirst(new Place(sc.nextInt(), false));
        }
        System.out.println(sol());
    }

    private static int sol() {
        int cnt = 0;
        int ans = 0;
        while(cnt < K) {
            ans++;
            // 1. 벨트 회전
            circulate();
            //2. 가장 먼저 올라간 로봇부터 이동
            moveRobot();
            //3. 로봇 올리기
            getOn();
            //4. 내구도 0인칸 검사
            cnt = chekBox();
            if(cnt == K) break;
        }
        return ans;
    }

    private static int chekBox() {
        int cnt = 0;
        for (Place cur : upperConveyor) {
            if(cur.w == 0) {
                cnt++;
            }
        }
        for (Place cur : lowerConveyor) {
            if (cur.w == 0) {
                cnt++;
            }
        }
        return cnt;
    }

    private static void getOn() {
        // 첫번째 칸의 내구도가 1 이상일 때
        if(upperConveyor.get(0).w > 0) {
            upperConveyor.get(0).w--;
            upperConveyor.get(0).isRobot=true;
        }
    }

    private static void moveRobot() {
        // 가장 먼저 올라갔다 == 위의 벨트에서 가장 오른쪽에 있다
        for (int i = N-2; i >= 0; i--) {
            // 로봇이 있을 때
            if(upperConveyor.get(i).isRobot) {
                // 다음 칸에 내구도가 1이상 그리고 로봇이 없다면
                if(upperConveyor.get(i+1).w > 0 && !upperConveyor.get(i+1).isRobot) {
                    upperConveyor.get(i).isRobot = false;
                    upperConveyor.get(i+1).isRobot = true;
                    upperConveyor.get(i+1).w--;
                }
            }
        }
    }

    private static void circulate() {
        takeOffRobot();

        // 위의 마지막이 아래의 마지막으로
        lowerConveyor.add(upperConveyor.pollLast());
        // 아래의 처음이 위의 처음으로
        upperConveyor.addFirst(lowerConveyor.pollFirst());

        takeOffRobot();
    }

    private static void takeOffRobot() {
        // 로봇 하차
        if (upperConveyor.get(N-1).isRobot) {
            upperConveyor.get(N-1).isRobot = false;
        }
    }
}
