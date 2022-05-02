import java.util.*;

public class PG67259 {
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int N;

    public static void main(String[] args) {
//      int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
//      int[][] board = {{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}};
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };
        solution(board);


    }

    public static int solution(int[][] board) {
        N = board.length;
        int answer = bfs(board);
        System.out.println(answer);
        return answer;
    }

    private static int bfs(int[][] board) {
        int[][] discovered = new int[N][N];
        init(discovered);
        int result = Integer.MAX_VALUE;
        Queue<Car> queue = new LinkedList<>();
        queue.add(new Car(0, 0, 0, -1));
        while (!queue.isEmpty()) {
            Car car = queue.poll();
            if (car.x == N - 1 && car.y == N - 1) {
                result = Math.min(result, car.cost);
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int nx = car.x + direction[d][0];
                int ny = car.y + direction[d][1];
                if (!check(nx, ny) || board[nx][ny] == 1) {
                    continue;
                }
                int cost = car.cost;
                if (car.dir == -1 || car.dir == d) {
                    cost += 100;
                } else {
                    cost += 600;
                }
                if (discovered[nx][ny] == -1 || discovered[nx][ny] >= cost) {
                    discovered[nx][ny] = cost;
                    queue.add(new Car(nx, ny, cost, d));
                }
            }
        }
        return result;
    }

    private static void init(int[][] discovered) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                discovered[i][j] = -1;
            }
        }
    }


    private static boolean check(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < N;
    }

    static class Car {
        int x, y, cost, dir;

        Car(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }
}

//{
// {0, 0, 0, 0, 0, 0, 0, 0},
//{1, 0, 1, 1, 1, 1, 1, 0},
// {1, 0, 0, 1, 0, 0, 0, 0},
// {1, 1, 0, 0, 0, 1, 1, 1},
// {1, 1, 1, 1, 0, 0, 0, 0},
// {1, 1, 1, 1, 1, 1, 1, 0},
// {1, 1, 1, 1, 1, 1, 1, 0},
// {1, 1, 1, 1, 1, 1, 1, 0}
// }