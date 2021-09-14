import sys
from collections import deque

input = sys.stdin.readline


def solution(n, m, arr):
    directions = [(-1, 0), (0, 1), (1, 0), (0, -1)]
    discovered = [[[False] * 64 for _ in range(m)] for _ in range(n)]
    Q = deque()
    for x in range(n):
        for y in range(m):
            if arr[x][y] == '0':
                Q.append((x, y, 0, 0))
                arr[x][y] = '.'
                discovered[x][y][0] = True

    while Q:
        x, y, cnt, key = Q.popleft()
        for direction in directions:
            nx, ny = x + direction[0], y + direction[1]
            if not (0 <= nx < n and 0 <= ny < m) or arr[nx][ny] == '#' or discovered[nx][ny][key]:
                continue

            if arr[nx][ny] == '1':
                return cnt + 1

            if arr[nx][ny] == '.':
                discovered[nx][ny][key] = True
                Q.append((nx, ny, cnt + 1, key))

            else:
                if arr[nx][ny].islower():
                    nkey = key | (1 << abs(ord(arr[nx][ny]) - 97))
                    discovered[nx][ny][nkey] = True
                    Q.append((nx, ny, cnt + 1, nkey))

                elif arr[nx][ny].isupper():
                    if key & (1 << abs(ord(arr[nx][ny].lower()) - 97)):
                        discovered[nx][ny][key] = True
                        Q.append((nx, ny, cnt + 1, key))
    return -1


n, m = map(int, input().split())
arr = [list(input().strip()) for _ in range(n)]
print(solution(n, m, arr))
