import java.io.*;
import java.util.*;

public class BOJ11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int x;
        PriorityQueue<NumAbsolute> pq = new PriorityQueue<>();
        while (N-- > 0) {
            st = new StringTokenizer(bf.readLine());
            x = Integer.parseInt(st.nextToken());
            if (x != 0) {
                pq.add(new NumAbsolute(x));
            } else {
                if (pq.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(pq.poll().num);
                }
            }
        }
    }

    private static class NumAbsolute implements Comparable<NumAbsolute> {
        int num, absolute;

        NumAbsolute(int num) {
            this.num = num;
            absolute = Math.abs(num);
        }

        @Override
        public int compareTo(NumAbsolute other) {
            if (this.absolute != other.absolute)
                return this.absolute - other.absolute;
            else return this.num - other.num;
        }
    }
}
