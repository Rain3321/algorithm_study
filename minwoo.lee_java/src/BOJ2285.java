import java.util.*;
import java.io.*;

public class BOJ2285 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Info[] infos = new Info[N];
        double sumPeople = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int location = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            infos[i] = new Info(location, people);
            sumPeople += people;
        }
        Arrays.sort(infos, Comparator.comparingInt(o1 -> {
            return o1.location;
        }));
        double prefixSum  = 0;
        for (int i = 0; i < N; i++) {
            prefixSum += infos[i].people;
            if(prefixSum >= sumPeople / 2){
                System.out.println(infos[i].location);
                break;
            }
        }

    }

    static class Info {
        int location, people;

        Info(int location, int people) {
            this.location = location;
            this.people = people;
        }
    }
}
