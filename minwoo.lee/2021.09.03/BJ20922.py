import sys
from collections import defaultdict

input = sys.stdin.readline


class Solution:
    def __init__(self):
        self.n, self.k = map(int, input().split())
        self.arr = list(map(int, input().split()))

    def solution(self):
        result = 0
        count = defaultdict(int)
        p1, p2 = 0, 0
        while p2 < self.n:
            if count[self.arr[p2]] + 1 <= self.k:
                count[self.arr[p2]] += 1
                p2 += 1
                result = max(result, p2 - p1)
            else:
                count[self.arr[p1]] -= 1
                p1 += 1
        print(result)


if __name__ == "__main__":
    s = Solution()
    s.solution()
