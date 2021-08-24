import sys

input = sys.stdin.readline


class Solution:
    def solution(self, coins, m):
        dp = [0 for _ in range(m + 1)]
        dp[0] = 1
        for coin in coins:
            for price in range(1, m + 1):
                if price >= coin:
                    dp[price] += dp[price - coin]
        print(dp[m])


if __name__ == "__main__":
    t = int(input().strip())
    s = Solution()
    for _ in range(t):
        n = int(input().strip())
        coins = list(map(int, input().split()))
        m = int(input().strip())
        s.solution(coins, m)
