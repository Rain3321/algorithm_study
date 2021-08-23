import sys
from collections import defaultdict


class Solution:
    def solution(self, n, task):
        dp = [0] * (n+2)
        for day in range(1, n + 1):
            time, pay = task[day]
            if day + time - 1 <= n:
                dp[day + time - 1] = max(dp[day + time - 1], dp[day - 1] + pay)

            dp[day + 1] = max(dp[day], dp[day + 1])

        return dp[n]


if __name__ == "__main__":
    n = int(sys.stdin.readline().strip())
    task = defaultdict(list)
    for day in range(1, n + 1):
        task[day] = list(map(int, sys.stdin.readline().split()))
    s = Solution()
    print(s.solution(n, task))


import sys


class Solution:
    def solution(self, n, time, pay):
        dp = [0] * (n + 1)
        for day in range(n):
            t, p = time[day], pay[day]
            if day + t <= n:
                dp[day + t] = max(dp[day + t], dp[day] + p)

            dp[day + 1] = max(dp[day], dp[day + 1])

        return dp[n]


if __name__ == "__main__":
    n = int(sys.stdin.readline().strip())
    time, pay = [], []
    for _ in range(n):
        temp = list(map(int, sys.stdin.readline().split()))
        time.append(temp[0])
        pay.append(temp[1])
    s = Solution()
    print(s.solution(n, time, pay))
