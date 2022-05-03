import java.util.LinkedList;
import java.util.Queue;

public class PG67259 {
    class Solution {
        public int solution(int[][] board) {
            int n = board.length;
            final int[][] move = {{0,1}, {1,0},{0,-1},{-1,0}};
            int[][][] dp = new int[n][n][4];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    for(int k = 0; k < 4; k++){
                        dp[i][j][k] = Integer.MAX_VALUE;
                    }
                }
            }

            Queue<Car> q = new LinkedList<>();
            dp[0][0][0] = 0;
            dp[0][0][1] = 0;
            q.offer(new Car(0, 0, 0));
            q.offer(new Car(0, 0, 1));

            while(!q.isEmpty()){
                Car car = q.poll();

                int[] tmp = {-1, 0, 1};
                for(int d = 0; d < 3; d++){
                    int nd = (car.dir + tmp[d] + 4) % 4;
                    int ni = car.i + move[nd][0];
                    int nj = car.j + move[nd][1];

                    if(ni >= 0 && nj >= 0 && ni < n && nj < n && board[ni][nj] == 0){
                        int cost = (d == 1) ? 100 : 600;
                        if(dp[ni][nj][nd] > dp[car.i][car.j][car.dir] + cost){
                            dp[ni][nj][nd] = dp[car.i][car.j][car.dir] + cost;
                            q.offer(new Car(ni, nj, nd));
                        }
                    }
                }
            }
            int min = dp[n-1][n-1][0];
            for(int i = 1; i < 4; i++){
                min = Math.min(min, dp[n-1][n-1][i]);
            }
            return min;
        }
    }

    class Car{
        int i, j, dir;
        Car(int i, int j, int dir){
            this.i=i;
            this.j=j;
            this.dir= dir;
        }
    }
}
