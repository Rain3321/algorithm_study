import java.util.*;
import java.util.stream.Collectors;

public class BJ17471 {
    static int N, ans=Integer.MAX_VALUE;
    static int[] population;
    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        population = new int[N+1];
        adjList = new ArrayList[N+1];
        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            population[i] = sc.nextInt();
        }
        for (int i = 1; i <= N; i++) {
            int cnt = sc.nextInt();
            for (int j = 0; j < cnt; j++) {
                adjList[i].add(sc.nextInt());
            }
        } // 초기화 끝

        // 완탐을 돌아보자
        // 그렇다면 한 선거구에 들어갈 구역의 개수를 정해서 그 개수만큼 나누면 되지 않을까
        for (int i = 1; i < N / 2 + 1; i++) {
            dfs(i, 1, 0, new int[i]);
        }
        System.out.println(ans == Integer.MAX_VALUE? -1 : ans);
    }

    /**
     * 완탐을 위한 깊이 우선 탐색
     *
     * @param n 한쪽 선거구에 들어갈 구역의 총 개수
     * @param idx 구역을 갖고 있는 리스트의 인덱스
     * @param k 포함된 배열을 관리할 인덱스
     * @param includes 한쪽 선거구에 들어가는 구역의 인덱스 배열
     */
    private static void dfs(int n, int idx, int k, int[] includes) {
        if(k == n) {
            // 먼저 뽑은 구역이 모두 연결되는지 확인하자
            // 연결 되어있지 않으면 패쓰
            if(checkPartition(includes)) return;
            // 뽑지 않은 녀석들을 한개 리스트로 모으자
            var includesList = Arrays.stream(includes).boxed().collect(Collectors.toList());
            ArrayList<Integer> notIncludesList = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if(includesList.contains(i)) continue;
                notIncludesList.add(i);
            }
            // 뽑히지 않은 애들도 연결되지 않았으면 패쓰
            if(checkPartition(notIncludesList.stream().mapToInt(i -> i).toArray())) return;

            // 둘다 통과헀다면 연결된 각 구역이 만들어짐
            // 그렇다면 이제 인구수를 계산하자
            int n1 = 0, n2 = 0;
            for(int i: includesList) {
                n1 += population[i];
            }
            for (int i: notIncludesList) {
                n2 += population[i];
            }
            ans = Math.min(ans, Math.abs(n1 - n2));

            return;


        }

        // idx 구역부터 N 번째 구역까지 한씩 넣어보자
        for (int i = idx; i <= N; i++) {
            includes[k] = i;
            dfs(n, i+1, k+1, includes);
        }
    }
    // 배열을 받아서 배열 인덱스의 0번으로부터 연결된 모든 노드를 잇고
    // 이어진 노드 리스트와 주어진 배열을 비교해서 모든 노드가 연결되어있다면
    // false 리턴
    private static boolean checkPartition(int[] includes) {
        Queue<Integer> queue = new LinkedList<>();
        var includesList = Arrays.stream(includes).boxed().collect(Collectors.toList());
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] v = new boolean[N+1];

        queue.add(includes[0]);
        list.add(includes[0]);
        v[includes[0]] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next: adjList[cur]) {
                if(!v[next] && includesList.contains(next)) {
                    queue.add(next);
                    list.add(next);
                    v[next] = true;
                }
            }
        }
        for(int c : includes) {
            if(!list.contains(c)) return true;
        }
        return false;

    }
}
