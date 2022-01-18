import java.io.*;
import java.util.*;

public class BOJ15787 {
    private static int command, train, seat;
    private static char[][] trains;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        HashSet<String> set = new HashSet<>();
        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trains = new char[N][20];
        for (int i = 0; i < N; i++) {
            Arrays.fill(trains[i], '0');
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(bf.readLine());
            command = Integer.parseInt(st.nextToken());
            train = Integer.parseInt(st.nextToken());
            // 수정 -> command 값이 1과 2이면 값을 받고 3,4이면 값을 받지 않으면 된다.
            if (command == 1 || command == 2) seat = Integer.parseInt(st.nextToken());
            command();

        }
        for (int i = 0; i < N; i++) {
            set.add(String.valueOf(trains[i]));
        }
        System.out.println(set.size());
        bf.close();
    }

    public static void command() {
        // 자리가 비어져 있든 채워져 있든 1을 넣는 것은 문제가 없어서 불필요한 조건을 없앨 수 있다라고 생각
        if (command == 1)
            trains[train - 1][seat - 1] = '1';
            // 하차도 역시 자리가 채워져 있든 비어있든 하차를 표현하는 0을 넣는 것은 문제가 없어 불필요한 조건을 없앨 수 있다고 생각
        else if (command == 2)
            trains[train - 1][seat - 1] = '0';
        else if (command == 3) {
            for (int s = 19; s > 0; s--) {
                trains[train - 1][s] = trains[train - 1][s - 1];
            }
            trains[train - 1][0] = '0';
        } else {
            for (int s = 0; s < 19; s++) {
                trains[train - 1][s] = trains[train - 1][s + 1];
            }
            trains[train - 1][19] = '0';
        }
    }
}
