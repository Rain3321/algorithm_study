import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LEET22 {

  public List<String> generateParenthesis(int n) {
    LinkedList<String> ans = new LinkedList<>();
    Parentheses parentheses = new Parentheses(0, 0);
    make(parentheses, ans, n);
    return ans;
  }

  private void make(Parentheses parentheses, LinkedList<String> ans, int n) {
    if (parentheses.open == n && parentheses.close == n) {
      ans.add(parentheses.sb.toString());
      return;
    }

    if (parentheses.open < n) {
      parentheses.addOpen();
      make(parentheses, ans, n);
      parentheses.decreaseOpen();
    }

    if (parentheses.close < n && parentheses.canAdd()) {
      parentheses.addClose();
      make(parentheses, ans, n);
      parentheses.decreaseClose();
    }
  }

  private static class Parentheses {
    int open;
    int close;
    StringBuilder sb = new StringBuilder();

    public Parentheses(int open, int close) {
      this.open = open;
      this.close = close;
    }

    public void addOpen() {
      open++;
      sb.append('(');
    }

    public void decreaseOpen() {
      open--;
      sb.deleteCharAt(sb.length() - 1);
    }

    public void addClose() {
      close++;
      sb.append(')');
    }

    public void decreaseClose() {
      close--;
      sb.deleteCharAt(sb.length() - 1);
    }

    public boolean canAdd() {
      return close < open;
    }
  }
}
