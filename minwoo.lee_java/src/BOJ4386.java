import java.util.*;
import java.io.*;

public class BOJ4386 {
    static int N;
    static Star[] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        stars = new Star[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            Star star = new Star(x, y);
            stars[i] = star;
        }
        System.out.printf("%.2f", solution());
    }

    private static double solution() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o.length));
        boolean[] discovered = new boolean[N];
        pq.add(new Node(0, 0));
        double minLength = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (discovered[node.num]) {
                continue;
            }
            discovered[node.num] = true;
            minLength += node.length;
            for (int next = 0; next < N; next++) {
                if (!discovered[next]) {
                    double distance = getDistance(node.num, next);
                    pq.add(new Node(next, distance));
                }
            }
            if (++cnt == N) {
                break;
            }
        }
        return minLength;
    }

    private static double getDistance(int n1, int n2) {
        Star star1 = stars[n1];
        Star star2 = stars[n2];
        return Math.sqrt(Math.pow(star1.x - star2.x, 2) + Math.pow(star1.y - star2.y, 2));
    }

    static class Node {
        int num;
        double length;

        Node(int num, double length) {
            this.num = num;
            this.length = length;
        }
    }

    static class Star {
        double x, y;

        Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
