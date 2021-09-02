import sys

input = sys.stdin.readline


class Solution:
    def __init__(self):
        self.n = int(input().strip())
        self.parent = [i for i in range(self.n)]
        self.rank = [0 for _ in range(self.n)]
        self.arr = [list(map(int, input().split())) for _ in range(self.n)]
        self.graph = []
        for x in range(self.n):
            for y in range(x + 1, self.n):
                self.graph.append((x, y, self.arr[x][y]))

        self.graph.sort(key=lambda x: x[2])

    def find(self, vertex):
        if self.parent[vertex] != vertex:
            self.parent[vertex] = self.find(self.parent[vertex])

        return self.parent[vertex]

    def union(self, p1, p2):
        root1 = self.find(p1)
        root2 = self.find(p2)
        if self.rank[root1] > self.rank[root2]:
            self.parent[root2] = root1
        else:
            self.parent[root1] = root2
            if self.rank[root1] == self.rank[root2]:
                self.rank[root2] += 1

    def solution(self):
        result = 0
        for g in self.graph:
            p1, p2, w = g
            if self.find(p1) != self.find(p2):
                self.union(p1, p2)
                result += w

        print(result)


if __name__ == "__main__":
    s = Solution()
    s.solution()
