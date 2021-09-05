import sys
from collections import defaultdict, deque

input = sys.stdin.readline


def solution():
    q = int(input().strip())
    for _ in range(q):
        kind, count = set(), 0
        query = input().strip().split('/')[-1]
        Q = deque([query])
        while Q:
            folder = Q.popleft()
            for f, value in tree[folder]:
                if value == '0':
                    count += 1
                    kind.add(f)
                else:
                    Q.append(f)

        print(len(kind), count)


n, m = map(int, input().split())
tree = defaultdict(list)
for _ in range(n + m):
    p, f, c = input().split()
    tree[p].append((f, c))
solution()
