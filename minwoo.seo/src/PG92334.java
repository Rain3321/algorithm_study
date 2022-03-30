import java.util.*;
import java.util.stream.Collectors;

public class PG92334 {
  public int[] solution(String[] id_list, String[] report, int k) {

    Map<String, Integer> ids = new HashMap<>();
    Map<String, ArrayList<String>> reportIdsMap = new HashMap<>();
    for (String id : id_list) {
      reportIdsMap.put(id, new ArrayList<>());
    }

    List<String> distinctReport = Arrays.stream(report)
      .distinct()
      .collect(Collectors.toList());

    for (String r : distinctReport) {
      String[] s = r.split(" ");
      String reporter = s[0];
      String reported = s[1];
      // 각 유저별로 신고를 얼마나 먹었는지 저장
      ids.put(reported, ids.getOrDefault(reported, 0) + 1);

      // 어떤 유저가 누구를 신고했는지 저장
      ArrayList<String> list = reportIdsMap.getOrDefault(reporter, new ArrayList<>());
      list.add(reported);
      reportIdsMap.put(reporter,list);
    }

    List<Integer> answer = new ArrayList<>();
    for (String id : id_list) {
      int count = 0;
      for (String reportedId : reportIdsMap.get(id)) {
        if (ids.get(reportedId) >= k) {
          count++;
        }
      }
      answer.add(count);
    }


    return answer.stream().mapToInt(i -> i).toArray();
  }
}
