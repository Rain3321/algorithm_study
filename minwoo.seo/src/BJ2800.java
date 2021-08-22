import java.util.*;
import java.util.stream.Collectors;

public class BJ2800 {
  static List<Bracket> bracketList = new ArrayList<>();
  static Set<String> answer = new HashSet<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String input = sc.next();
    char[] inputString = input.toCharArray();
    Stack<Integer> indexStack = new Stack<>();
    for (int i = 0; i < inputString.length; i++) {
      char c = inputString[i];
      if(c == '(') indexStack.push(i);
      if (c == ')') bracketList.add(new Bracket(indexStack.pop(), i));
    }
    sol(0, inputString, new boolean[inputString.length]);

    var removedList = new ArrayList<>(answer);

    removedList.stream()
        .filter(exp -> !exp.equals(input))
        .sorted()
        .collect(Collectors.toList())
        .forEach(System.out::println);

  }

  private static void sol(int i, char[] inputString, boolean[] bs) {
    if(i == bracketList.size()) {
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < bs.length; j++) {
        if(bs[j]) continue;
        sb.append(inputString[j]);
      }
      answer.add(sb.toString());
      return;
    }
    Bracket removed = bracketList.get(i);
    bs[removed.start] = true;
    bs[removed.end] = true;
    sol(i+1, inputString, bs);

    bs[removed.start] = false;
    bs[removed.end] = false;
    sol(i+1, inputString, bs);
  }

  static class Bracket {
    int start;
    int end;
    public Bracket(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
}
