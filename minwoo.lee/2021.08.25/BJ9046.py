import sys
from collections import Counter
input = sys.stdin.readline

class Solution:
    def __init__(self):
        self.strs = ''.join(input().split())

    def solution(self):
        try:
            most_alpha = Counter(self.strs).most_common(2)
            return most_alpha[0][0] if most_alpha[0][1] != most_alpha[1][1] else "?"
        except:
            return self.strs[0]



if __name__ == "__main__":
    t = int(input().strip())
    for _ in range(t):
        s = Solution()
        print(s.solution())