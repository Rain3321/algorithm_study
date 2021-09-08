import java.io.*;
import java.util.*;

public class BJ2224 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        int[][] graph = new int[52][52];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int from = toInt(s.charAt(0));
            int to = toInt(s.charAt(5));
            graph[from][to] = 1;
        }
        
        // 플로이드-와샬
        for (int k = 0; k < 52; k++) { // 경유지
            for (int i = 0; i < 52; i++) { // 출발지
                for (int j = 0; j < 52; j++) { // 도착지
                    if(i == j) continue;
                    if(graph[i][j] == 0 && graph[i][k] != 0 && graph[k][j] != 0) { // k를 경유하여 i에서 j로 갈 수 있다면
                        graph[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                if(i == j) continue;
                if(graph[i][j] != 0) {
                    cnt++;
                }
            }
        }

        bw.write(cnt + "\n");
        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                if(i == j) continue;
                if(graph[i][j] != 0) {
                    bw.write(toString(i) + " => " + toString(j) + "\n");
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    // A ~ Z : 0 ~ 25, a ~ z: 26 ~ 51
    private static int toInt(char token) {
        if(token >= 'A' && token <= 'Z') return token - 'A';
        else
            return token - 'a' + 26;
    }
    private static char toString(int token) {
        if(token >= 0 && token <= 25) return (char)(token + 'A');
        else
            return (char)(token - 26 + 'a');
    }
}
