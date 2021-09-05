import sys
input = sys.stdin.readline


class Solution:
    def solution(self, s, t):
        if s in t:
            return "Yes"
        s_idx = 0
        for t_idx in range(len(t)):
            if t[t_idx] == s[s_idx]:
                s_idx += 1
                if s_idx == len(s):
                    return "Yes"
        return "No"





if __name__ == "__main__":
    sol = Solution()
    while True:
        case = input().strip()
        if case == '':
            break
        s,t = case.split()
        print(sol.solution(s,t))
