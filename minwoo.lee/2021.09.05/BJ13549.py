import sys
from collections import deque

input = sys.stdin.readline


def solution():
    Q = deque([n])
    while Q:
        subin = Q.popleft()
        if subin == k:
            return time[subin]
        for next in (subin - 1, subin + 1, subin * 2):
            if 0 <= next < 100001 and not time[next]:
                if next == subin * 2 and subin != 0:
                    time[next] = time[subin]
                    Q.appendleft(next)
                else:
                    time[next] = time[subin] + 1
                    Q.append(next)

n, k = map(int, input().split())
time = [0 for _ in range(100001)]
print(solution())
