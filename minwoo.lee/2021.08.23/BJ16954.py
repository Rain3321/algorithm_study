import sys
from collections import deque

input = sys.stdin.readline


class Solution:
    def __init__(self):
        self.board = deque([list(input().strip()) for _ in range(8)])
        self.walls = []
        self.direction = [(0, 0), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0), (-1, 1)]
        for x in range(8):
            for y in range(8):
                if self.board[x][y] == '#':
                    self.walls.append((x, y))
        self.sx, self.sy = 7, 0
        self.ex, self.ey = 0, 7

    def solution(self):
        if len(self.walls) == 0:
            return 1

        Q = deque([(self.sx, self.sy)])

        while Q:
            discovered = [[False] * 8 for _ in range(8)]
            for _ in range(len(Q)):
                x, y = Q.popleft()
                if x == 0:
                    return 1
                if self.board[x][y] == '.':
                    for dx, dy in self.direction:
                        nx, ny = x + dx, y + dy
                        if 0 <= nx < 8 and 0 <= ny < 8 and self.board[nx][ny] == '.' and not discovered[nx][ny]:
                            discovered[nx][ny] = True
                            Q.append((nx, ny))
            self.board.pop()
            self.board.appendleft(['.' for _ in range(8)])
        return 0


if __name__ == "__main__":
    s = Solution()
    print(s.solution())
