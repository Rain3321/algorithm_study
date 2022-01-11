import java.util.*;

public class BJ17609 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    for (int i = 0; i < N; i++) {
      String word = sc.next();
      int length = word.length();

      int start = 0;
      int end = length - 1;

      int result = 2;

      Palindrome checkWord = checkWord(word, start, end, length);

      if (checkWord.flag) {
        result = 0;
      } else {
        if (checkWord(word, checkWord.start + 1, checkWord.end, length - 1).flag ||
          checkWord(word, checkWord.start, checkWord.end - 1, length - 1).flag) {
          result = 1;
        }
      }
      System.out.println(result);
    }
  }

  private static Palindrome checkWord(String word, int start, int end, int length) {
    boolean flag = true;

    for (int time = 0; time < length / 2 + 1; time++) {
      if (word.charAt(start) != word.charAt(end)) {
        flag = false;
        break;
      }
      start++;
      end--;

      if(start > end) break;
    }
    return new Palindrome(start, end, flag);
  }

  private static class Palindrome {
    int start;
    int end;
    boolean flag;

    public Palindrome(int start, int end, boolean flag) {
      this.start = start;
      this.end = end;
      this.flag = flag;
    }
  }
}
