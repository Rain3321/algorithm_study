import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BJ4358 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Map<String, Integer> tree = new HashMap<>();
    int total = 0;
    String nextTree;

    while ((nextTree = br.readLine()) != null) {
      total++;
      if (tree.containsKey(nextTree)) {
        tree.put(nextTree, tree.get(nextTree) + 1);
      } else {
        tree.put(nextTree, 1);
      }
    }

    var sorted = tree.entrySet().stream()
      .sorted(Map.Entry.comparingByKey())
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
        (o, n) -> o, LinkedHashMap::new));

    int finalTotal = total;
    sorted.forEach((k, v) -> System.out.format("%s %,.4f\n", k, 100.0 * v / finalTotal));


  }
}
