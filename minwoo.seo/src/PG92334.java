import java.util.*;
import java.util.stream.Collectors;

public class PG92334 {
  public int[] solution(String[] id_list, String[] report, int k) {

    Map<String, Integer> count = new HashMap<>();

    List<String> distinctReport = Arrays.stream(report)
      .distinct()
      .collect(Collectors.toList());

    for (String r : distinctReport) {
      String[] s = r.split(" ");
      // 각 유저별로 신고를 얼마나 먹었는지 저장
      count.put(s[1], count.getOrDefault(s[1], 0) + 1);
    }

    return Arrays.stream(id_list)
      .map(id -> {
        List<String> reportList = distinctReport.stream()
          .filter(s -> s.startsWith(id + " "))
          .map(s -> s.split(" ")[1])
          .collect(Collectors.toList());
        return reportList.stream().filter(s -> count.getOrDefault(s, 0) >= k).count();
      })
      .mapToInt(Long::intValue).toArray();
  }
}
