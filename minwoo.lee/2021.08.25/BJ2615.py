import sys


class Solution:
    def __init__(self):
        self.board = [list(map(int, sys.stdin.readline().split())) for _ in range(19)]
        self.direction = [(-1, 1), (0, 1), (1, 1), (1, 0)]

    def victory(self, sx, sy, d):
        omok = 1
        for i in range(1, 6):
            nx, ny = sx + d[0] * i, sy + d[1] * i
            if 0 <= nx < 19 and 0 <= ny < 19:
                if self.board[sx][sy] == self.board[nx][ny]:
                    omok += 1
                else:
                    break

        if omok == 5:
            prev_x, prev_y = sx - d[0], sy - d[1]
            if prev_y < 0 or prev_x < 0:
                return True
            if self.board[sx][sy] != self.board[prev_x][prev_y]:
                return True
            else:
                return False
        else:
            return False

    def solution(self):
        for x in range(19):
            for y in range(19):
                if self.board[x][y] == 1 or self.board[x][y] == 2:
                    for d in self.direction:
                        if self.victory(x, y, d):
                            return f"{self.board[x][y]}\n{x + 1} {y + 1}"
        return 0


if __name__ == "__main__":
    s = Solution()
    print(s.solution())
