import sys
from itertools import combinations
from collections import deque

input = sys.stdin.readline


def get_sum(group):
    # 해당 선거구 내의 구역 모두 모두 이어져있다면 어디서 시작하든지 상관없으므로 첫번째 값을 시작 값으로 잡는다.
    start = group[0]
    Q = deque([start])
    discovered = [start]
    sum_population = 0
    while Q:
        area = Q.popleft()
        sum_population += populations[area]
        for next in graph[area]:
            if next not in discovered and next in group:
                Q.append(next)
                discovered.append(next)
    return sum_population, len(discovered)


def solution():
    result = float('inf')
    n_list = [i for i in range(1, n + 1)]

    for i in range(1, n // 2 + 1):
        combi_list = list(combinations(n_list, i))
        for combi in combi_list:
            s1, l1 = get_sum(combi)
            s2, l2 = get_sum([i for i in range(1, n + 1) if i not in combi])
            if l1 + l2 == n:
                result = min(result, abs(s1 - s2))

    return result if result != float('inf') else -1


n = int(input().strip())
populations = dict()
for idx, p in zip([i for i in range(1, n + 1)], list(map(int, input().split()))):
    populations[idx] = p
graph = dict()
for idx in range(1, n + 1):
    cnt, *adj = list(map(int, input().split()))
    graph[idx] = adj
print(solution())
