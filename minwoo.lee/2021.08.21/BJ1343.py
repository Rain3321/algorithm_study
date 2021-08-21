import sys


class Solution:

    def solution(self, board):
        board = board.replace('XXXX', 'AAAA')
        board = board.replace('XX', 'BB')
        if 'X' in board:
            print(-1)
        else:
            print(board)


if __name__ == "__main__":
    s = Solution()
    board = sys.stdin.readline().strip()
    s.solution(board)
