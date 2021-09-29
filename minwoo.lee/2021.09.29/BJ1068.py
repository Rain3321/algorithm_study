import sys
from collections import defaultdict, deque

input = sys.stdin.readline


def solution(tree, remove_node, relation, root):
    count = 0
    if remove_node == root:
        return count
    else:
        tree[relation[remove_node]].remove(remove_node)
    Q = deque([root])
    while Q:
        node = Q.popleft()
        if len(tree[node]) == 0:
            count += 1
        else:
            for next in tree[node]:
                Q.append(next)

    return count


n = int(input().strip())
tree = defaultdict(list)
relation = dict()
for node, parent in enumerate(list(map(int, input().split()))):
    if parent != -1:
        tree[parent].append(node)
        relation[node] = parent
    else:
        root = node

remove_node = int(input().strip())
print(solution(tree, remove_node, relation,root))
