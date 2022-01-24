import java.util.*;
import java.io.*;

public class BOJ6443 {
    static char[] word;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        while (N-- > 0) {
            word = bf.readLine().toCharArray();
            Arrays.sort(word);
            System.out.println(word);
            while (next_permutation()) {
                System.out.println(word);
            }
        }
    }

    static boolean next_permutation() {
        int i = word.length - 1;
        int j = word.length - 1;
        // 앞에 있는 알파벳이 뒤에 있는 알파벳보다 사전상 뒤에 오는 경우를 찾는다.
        while (i > 0 && word[i - 1] >= word[i]) {
            i--;
        }
        if (i <= 0) return false;

        // 교차할 알바펫의 위치를 찾기
        while (word[i - 1] >= word[j]) {
            j--;
        }

        // swap
        char temp = word[j];
        word[j] = word[i - 1];
        word[i - 1] = temp;


        // 뒤에 문자들 순서를 정렬해준다.
        j = word.length - 1;
        while (i < j) {
            temp = word[j];
            word[j] = word[i];
            word[i] = temp;
            i++;
            j--;
        }
        return true;
    }
}
