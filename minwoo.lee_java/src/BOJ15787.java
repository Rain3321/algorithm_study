import java.io.*;
import java.util.*;

public class BOJ15787 {
    private static int command, train, seat;
    private static char[][] trains;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        // 왜 HashSet을 썻는가?
        ArrayList<String> arr = new ArrayList<>();
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
            try {
                seat = Integer.parseInt(st.nextToken());
            } catch (NoSuchElementException e) {
            }
            command();

        }
        for (int i = 0; i < N; i++) {
            if(!arr.contains(String.valueOf(trains[i])))
                arr.add(String.valueOf(trains[i]));
        }
        System.out.println(arr.size());
        bf.close();
    }

    public static void command() {
        if (command == 1 && trains[train - 1][seat - 1] == '0') {
            trains[train - 1][seat - 1] = '1';
        } else if (command == 2 && trains[train - 1][seat - 1] == '1') {
            trains[train - 1][seat - 1] = '0';
        } else if (command == 3) {
            for (int s = 19; s > 0; s--) {
                trains[train - 1][s] = trains[train - 1][s - 1];
            }
            trains[train - 1][0] = '0';
        } else {
            if (command == 4) {
                for (int s = 0; s < 19; s++) {
                    trains[train - 1][s] = trains[train - 1][s + 1];
                }
                trains[train - 1][19] = '0';
                return;
            }
        }

    }
}