import sys, heapq

input = sys.stdin.readline


def solution():
    Q = []
    count = 0
    for s, t in time:
        if len(Q) == 0:
            heapq.heappush(Q, t)
            count += 1
        else:
            end_time = heapq.heappop(Q)
            if end_time <= s:
                heapq.heappush(Q, t)
            else:
                heapq.heappush(Q, end_time)
                heapq.heappush(Q, t)
                count += 1
    print(count)


n = int(input().strip())
time = []
for _ in range(n):
    s, t = map(int, input().split())
    time.append((s, t))

time.sort(key=lambda x: x[0])
solution()
