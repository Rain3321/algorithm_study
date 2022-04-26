import java.io.*;
import java.util.*;

public class BJ20924 {
    private static int maxBranch, pole, giga;
    private static boolean[] visit;
    private static ArrayList<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = parse(st.nextToken());
        int r = parse(st.nextToken());

        visit = new boolean[n + 1];
        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parse(st.nextToken());
            int b = parse(st.nextToken());
            int d = parse(st.nextToken());
            adj[a].add(new Node(b, d));
            adj[b].add(new Node(a, d));
        }

        visit[r] = true;
        adj[r].add(new Node(0, 0));

        getGiga(r, 0);
        getBranch(giga, 0);
        System.out.println(pole + " " + maxBranch);
    }

    private static void getGiga(int root, int dist) {
        visit[root] = true;

        if (adj[root].size() != 2 && root != 0) {
            giga = root;
            pole = dist;
            return;
        }
        for (Node node : adj[root]) {
            if (!visit[node.n]) {
                getGiga(node.n, dist + node.d);
            }
        }
    }

    private static void getBranch(int root, int dist) {
        visit[root] = true;

        if (adj[root].size() == 1) {
            maxBranch = Math.max(maxBranch, dist);
            return;
        }

        for (Node node : adj[root]) {
            if (!visit[node.n]) {
                getBranch(node.n, dist + node.d);
            }
        }
    }

    private static class Node {
        int n, d;

        Node(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
