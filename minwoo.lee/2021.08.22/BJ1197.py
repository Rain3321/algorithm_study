import sys


class solution:

    # 정점을 독립적인 집합으로 만든다.
    def make_set(self, vertex):
        parent[vertex] = vertex
        rank[vertex] = 0

    # 여러 정점이 존재할 때, 두 정점을 선택해 두 정점이 서로 같은 그래프에 속하는지 판별하기 위해
    # 해당 정점의 최상위 정점을 찾는다.
    def find(self, vertex):
        if parent[vertex] != vertex:
            parent[vertex] = self.find(parent[vertex])

        return parent[vertex]

    # 두 정점을 연결한다.
    # union시 두 트리의 높이(rank)가 다르면, 높이가 작은 트리를 높이가 큰 트리에 붙인다.
    # 높이가 큰 트리의 루트 노드가 합친 집합의 루트 노드가 되게 한다.
    def union(self, p1, p2):
        root1 = self.find(p1)
        root2 = self.find(p2)
        if rank[root1] > rank[root2]:
            parent[root2] = root1
        else:
            parent[root1] = root2
            if rank[root1] == rank[root2]:
                rank[root2] += 1

    def solution(self, vertices, graph):
        for vertex in vertices:
            self.make_set(vertex)

        # 간선의 가중치가 작은 것부터 오름차순으로 정렬
        graph.sort(key=lambda x: x[2])

        for g in graph:
            p1, p2, w = g
            # 두 정점이 서로 같은 그래프에 속하지 않으면 두 정점을 합쳐준다. -> 트리를 합친다고 생각하면 된다.
            if self.find(p1) != self.find(p2):
                self.union(p1, p2)
                mst.append(g)


if __name__ == "__main__":
    s = solution()
    v, e = map(int, sys.stdin.readline().split())
    # 정점들의 집합
    vertices = set()
    # 정점들간의 간선정보
    graph = []
    parent, rank = dict(), dict()
    mst = []
    for _ in range(e):
        p1, p2, w = map(int, sys.stdin.readline().split())
        vertices.add(p1)
        vertices.add(p2)
        graph.append((p1, p2, w))
    vertices = list(vertices)
    s.solution(vertices, graph)

    result = 0
    for p1, p2, w in mst:
        result += w
    print(result)
