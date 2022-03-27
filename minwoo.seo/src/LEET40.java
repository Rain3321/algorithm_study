import java.util.*;

public class LEET40 {

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> results = new ArrayList<>();
    LinkedList<Integer> combi = new LinkedList<>();

    Arrays.sort(candidates);

    combination(candidates, combi, target, 0, results);

    return results;
  }

  private void combination(int[] candidates, LinkedList<Integer> combi,
                           int target, int cur, List<List<Integer>> results) {
    if (target == 0) {
      results.add(new ArrayList<>(combi));
      return;
    }
    for (int next = cur; next < candidates.length; next++) {
      if (next > cur && candidates[next] == candidates[next - 1]) { // 같은거 나오면 그냥 패스
        // 반례
        // [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
        // 27
        continue;
      }

      int p = candidates[next];
      if (target - p < 0) {
        continue;
      }

      combi.add(p);
      combination(candidates, combi, target - p, next + 1, results);
      combi.pollLast();
    }
  }
}
