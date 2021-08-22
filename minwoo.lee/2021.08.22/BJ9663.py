import sys

input = sys.stdin.readline().strip


class Solution:
    def dfs(self, queen_location, row, n):
        result = 0
        if row == n:
            return 1

        for col in range(n):
            queen_location[row] = col
            for x in range(row):
                if queen_location[x] == queen_location[row]:
                    break
                if abs(queen_location[row] - queen_location[x]) == row - x:
                    break
            else:
                result += self.dfs(queen_location, row + 1, n)

        return result

    def solution(self, n):
        return self.dfs([0 for _ in range(n)], 0, n)


if __name__ == "__main__":
    n = int(input())
    s = Solution()
    print(s.solution(n))
