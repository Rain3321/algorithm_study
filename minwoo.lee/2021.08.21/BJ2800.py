import sys


class Solution:
    def remove(self, start, element, answer, cnt, couple):
        if cnt == 0:
            me = math_expression[:]
            for p1, p2 in element:
                me[p1] = '';
                me[p2] = ''
            answer.append(''.join(me))
            return

        for i in range(start, len(couple)):
            element.append(couple[i])
            self.remove(i + 1, element, answer, cnt - 1, couple)
            element.pop()

    def solution(self, math_expression):
        stack = []
        couple = []
        for idx, value in enumerate(math_expression):
            if value == '(':
                stack.append(idx)
            elif value == ')':
                i = stack.pop()
                couple.append((i, idx))
            else:
                pass

        answer = []
        for cnt in range(1, len(couple) + 1):
            self.remove(0, [], answer, cnt, couple)
        answer = list(set(answer))
        answer.sort()

        for a in answer:
            print(a)
        return


if __name__ == "__main__":
    s = Solution()
    math_expression = list(sys.stdin.readline().strip())
    s.solution(math_expression)
