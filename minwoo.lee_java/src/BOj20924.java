import java.io.*;
import java.util.*;

public class BOj20924 {
    static int treePillar, longestBranch;
    static ArrayList<Child>[] tree;
    static boolean[] discovered;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            tree[from].add(new Child(to, edge));
            tree[to].add(new Child(from, edge));
        }

        discovered = new boolean[N + 1];
        discovered[root] = true;
        solution(root, 0, 0, false);
        System.out.println(treePillar + " " + longestBranch);
    }

    private static void solution(int cur, int pillar, int branchLength, boolean findGigaNode) {
        int cnt = childCnt(cur);
        // 리프노드에 도달했을 때
        if (cnt == 0) {
            // 기가 노드를 발견하지 못하고 리프노드에 도달했을 때
            if (!findGigaNode) {
                // 기가 노드부터 리프까지의 거리는 0이된다.
                longestBranch = 0;
                // 트리의 기둥은 루트부터 현재 리프까지의 거리
                treePillar = branchLength;
                return;
            }
            // 기가 노드를 발견하고 리프노드에 도달했을 때
            // 기가 노드부터 리프까지의 거리는 루트~리프까지의 길이에서 트리의 기둥을 뺀 값이다.
            longestBranch = Math.max(longestBranch, branchLength - treePillar);
            return;
        }
        // 자식노드의 개수가 두 개고 아직 기가 노드를 발견하지 못한거라면
        // 처음으로 자식노드의 개수가 두 개인 노드가 등장했다는 뜻이다.
        if (cnt >= 2 && !findGigaNode) {
            // 루트부터 기가 노드까지의 길이는 현재까지 구한 기둥의 길이가 된다.
            treePillar = pillar;
            findGigaNode = true;
        }
        for (Child node : tree[cur]) {
            if (discovered[node.num]) {
                continue;
            }
            // 아직 기가 노드를 발견하지 못한거라면 기둥의 길이를 더해준다.
            if (!findGigaNode) {
                pillar += node.edge;
            }
            discovered[node.num] = true;
            solution(node.num, pillar, branchLength + node.edge, findGigaNode);
        }
    }

    // 자식의 개수를 구하는 함수
    private static int childCnt(int cur) {
        int cnt = 0;
        for (Child node : tree[cur]) {
            // 발견된 노드들은 조상노드들이다 이므로 자식 노드가 아니다.
            if (!discovered[node.num]) {
                cnt++;
            }
        }
        return cnt;
    }

    static class Child {
        int num, edge;

        Child(int num, int edge) {
            this.num = num;
            this.edge = edge;
        }
    }
}
