import java.util.*;

public class PRO92341 {
    static StringTokenizer st;
    // 1439 는 23:59를 분으로 계산한 수;
    static final int LASTTIME = 1439;

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(Arrays.toString(solution(fees, records)));

    }

    public static int[] solution(int[] fees, String[] records) {
        // 자동차 번호, 총 누적시간
        Map<String, Integer> carTime = new TreeMap<>();
        for (String record : records) {
            st = new StringTokenizer(record);
            String time = st.nextToken();
            String carNum = st.nextToken();
            int status = (st.nextToken().equals("IN") ? -1 : 1);
            int intTime = status * toInt(time);
            carTime.put(carNum, carTime.getOrDefault(carNum, 0) + intTime);
        }

        int[] answer = new int[carTime.size()];
        int idx = 0;
        for (int time : carTime.values()) {
            // 00:00시 입차인 경우도 있음
            if(time <= 0){
                time += LASTTIME;
            }
            answer[idx] = getFee(time, fees);
            idx++;
        }
        return answer;
    }

    private static int toInt(String time) {
        st = new StringTokenizer(time, ":");
        return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }

    private static int getFee(int time, int[] fees) {
        if (time <= fees[0]) {
            return fees[1];
        }
        // 기본 요금으로 시작
        int fee = fees[1];
        // 기본 시간 빼줌
        time -= fees[0];

        fee += time / fees[2] * fees[3];
        if (time % fees[2] != 0) {
            fee += fees[3];
        }
        return fee;
    }
}
