import sys
from copy import deepcopy

input = sys.stdin.readline


def monitor(x, y, direction, temp):
    for d in direction:
        nx, ny = x, y
        while True:
            nx += dx[d]
            ny += dy[d]
            if 0 <= nx < n and 0 <= ny < m:
                if temp[nx][ny] == 6:
                    break
                elif temp[nx][ny] == 0:
                    temp[nx][ny] = '#'
            else:
                break


def solution(cnt, office):
    global result
    if cnt == len(cctv):
        blind_spot = 0
        for i in range(n):
            blind_spot += office[i].count(0)
        result = min(result, blind_spot)
        return

    x, y, cctv_num = cctv[cnt]
    for direction in cctv_direction[cctv_num]:
        temp = deepcopy(office)
        monitor(x, y, direction, temp)
        solution(cnt + 1, temp)


n, m = map(int, input().split())
office = [list(map(int, input().split())) for _ in range(n)]
# 사각지대의 최대값은 사무실 칸 전체 값
result = n * m
cctv = []
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]
# cctv 방향
cctv_direction = [
    [],
    [[0], [1], [2], [3]],
    [[0, 2], [1, 3]],
    [[0, 1], [1, 2], [2, 3], [3, 0]],
    [[0, 1, 2], [1, 2, 3], [2, 3, 0], [3, 0, 1]],
    [[0, 1, 2, 3]]
]
for x in range(n):
    for y in range(m):
        if 0 < office[x][y] < 6:
            cctv.append((x, y, office[x][y]))

solution(0, office)
print(result)
