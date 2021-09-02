import sys


class Solution:
    def __init__(self):
        self.n = int(sys.stdin.readline().strip())
        self.board = []
        for _ in range(self.n):
            self.nums = list(map(int, sys.stdin.readline().split()))
            self.board.append(self.nums)
        self.direction = [(0, 0), (1, 0), (0, 1), (1, 1)]

    def solution(self):
        while self.n != 1:
            new_board = []
            for x in range(0, self.n, 2):
                next = []
                for y in range(0, self.n, 2):
                    second = []
                    for d in self.direction:
                        nx, ny = x+d[0], y+d[1]
                        second.append(self.board[nx][ny])
                    second.sort()
                    next.append(second[-2])
                new_board.append(next)
            self.board = new_board
            self.n = self.n // 2
        print(self.board[-1][-1])


if __name__ == "__main__":
    s = Solution()
    s.solution()
