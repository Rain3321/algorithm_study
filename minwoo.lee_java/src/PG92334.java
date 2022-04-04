import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class PG92334 {
    public static void main(String[] args) {
    }

    public int[] solution(String[] id_list, String[] report, int k) {
        // 신고 결과 key : 신고를 당한사람 value : 신고를 한 사람들(중복X)
        HashMap<String, HashSet<String>> reportResult = new HashMap<>();
        StringTokenizer st;
        for (int i = 0; i < report.length; i++) {
            st = new StringTokenizer(report[i]);
            // 신고를 한 사람
            String from = st.nextToken();
            // 신고를 당한 사람
            String to = st.nextToken();
            // 신고를 당한 사람에 대한 신고 결과 정보를 가져옴.
            HashSet<String> setData = reportResult.getOrDefault(to, new HashSet<>());
            setData.add(from);
            reportResult.put(to, setData);
        }

        // key : 유저  value : 유저가 받을 결과 메일 수
        HashMap<String, Integer> receiveCnt = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            receiveCnt.getOrDefault(id_list[i], 0);
        }
        for (String key : reportResult.keySet()) {
            // 신고를 당한 사람에 대한 신고를 한 사람들 값을 가져옴
            HashSet<String> value = reportResult.get(key);
            // 신고를 한 사람들의 수가 k 이상이면 그 사람들에게 결과 메일이 감
            if (value.size() >= k) {
                Iterator<String> iter = value.iterator();
                while (iter.hasNext()) {
                    String name = iter.next();
                    // 신고한 사람들은 결과 메일을 받으므로 cnt값을 증가시켜준다.
                    int cnt = receiveCnt.getOrDefault(name, 0);
                    cnt += 1;
                    receiveCnt.put(name, cnt);
                }
            }
        }

        int[] result = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            result[i] = receiveCnt.getOrDefault(id_list[i], 0);
        }
        return result;
    }
}
