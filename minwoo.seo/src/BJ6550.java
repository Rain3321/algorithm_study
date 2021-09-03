import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ6550 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;
        String s1, s2;
        while (sc.hasNext()) {
            st = new StringTokenizer(sc.nextLine());
            if(!st.hasMoreTokens()) break;
            s1 = st.nextToken();
            s2 = st.nextToken();
            int idx = 0;
            for (char c : s2.toCharArray()) {
                if(c == s1.charAt(idx)) {
                    idx++;
                }
                if(idx == s1.length()) {
                    break;
                }
            }
            if(idx == s1.length()) System.out.println("Yes");
            else System.out.println("No");
        }

    }
}
