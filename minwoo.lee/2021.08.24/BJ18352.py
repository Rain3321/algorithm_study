import sys, heapq

input = sys.stdin.readline
from collections import defaultdict


class Solution:
    def __init__(self):
        self.result = []

    def solution(self, graph, k, start, n):
        Q = [(0, start)]
        discovered = [False for _ in range(n + 1)]
        discovered[start] = True
        while Q:
            dist, node = heapq.heappop(Q)
            if dist == k:
                self.result.append(node)
                continue

            for next in graph[node]:
                if not discovered[next] and dist + 1 <= k:
                    discovered[next] = True
                    heapq.heappush(Q, (dist + 1, next))

        if len(self.result) == 0:
            print(-1)
        else:
            self.result.sort()
            for re in self.result:
                print(re)


if __name__ == "__main__":
    n, m, k, x = map(int, input().split())
    graph = defaultdict(list)
    for _ in range(m):
        p1, p2 = map(int, input().split())
        graph[p1].append(p2)
    s = Solution()
    s.solution(graph, k, x, n)
