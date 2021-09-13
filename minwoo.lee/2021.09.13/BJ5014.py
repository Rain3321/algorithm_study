import sys
from collections import deque

input = sys.stdin.readline


def solution(f, s, g, u, d):
    # 스타트링크는 총 F층으로 이루어진 고층 건물에 사무실이 있고, 스타트링크가 있는 곳의 위치는 G층이다.
    # 강호가 지금 있는 곳은 S층이고, 이제 엘리베이터를 타고 G층으로 이동하려고 한다.
    cur = deque([(s, 0)])
    discovered = [False] * (f + 1)
    discovered[s] = True
    while cur:
        kangho, cnt = cur.popleft()
        if kangho == g:
            return cnt

        for move in [u, -d]:
            next = kangho + move
            if 0 < next <= f and not discovered[next]:
                discovered[next] = True
                cur.append((next, cnt + 1))

    return "use the stairs"


f, s, g, u, d = map(int, input().split())
print(solution(f, s, g, u, d))
