import sys

input = sys.stdin.readline


def check_row(x, num):
    return True if num not in sudoku[x] else False


def check_col(y, num):
    for x in range(9):
        if num == sudoku[x][y]:
            return False
    return True
    # return True if num not in list(zip(*sudoku))[y] else False


def check_square(x, y, num):
    sx, sy = x // 3 * 3, y // 3 * 3
    for dx in range(3):
        for dy in range(3):
            if num == sudoku[sx + dx][sy + dy]:
                return False
    return True


def solution(idx):
    if idx == len(zeros):
        for row in sudoku:
            print(*row)
        sys.exit(0)
    else:
        for num in range(1, 10):
            x = zeros[idx][0]
            y = zeros[idx][1]
            if check_row(x, num) and check_col(y, num) and check_square(x, y, num):
                sudoku[x][y] = num
                solution(idx + 1)
                sudoku[x][y] = 0


sudoku = [list(map(int, input().split())) for _ in range(9)]
zeros = [(i, j) for i in range(9) for j in range(9) if sudoku[i][j] == 0]
solution(0)
