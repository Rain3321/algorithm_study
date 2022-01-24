import java.util.*;

public class BJ6443 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    while (N-- > 0) {
      char[] words = sc.next().toCharArray();
      int[] alphabet = new int[26];
      for (char word : words) {
        alphabet[word - 'a']++;
      }
      permutation(words.length, new char[words.length], 0, alphabet);
    }
  }

  private static void permutation(int length, char[] arr, int idx, int[] alphabet) {
    if (idx == length) {
        System.out.println(arr);
      return;
    }
    for (int i = 0; i < 26; i++) {
      if (alphabet[i] > 0) {
        arr[idx] = (char) (i + 'a');
        alphabet[i]--;
        permutation(length, arr,idx + 1, alphabet);
        alphabet[i]++;
      }
    }
  }
}
