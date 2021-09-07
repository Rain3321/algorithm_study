import sys
from collections import deque

input = sys.stdin.readline


def solution():
    zero_cnt, step = 0, 0
    while True :
        step += 1

        # 1. 컨베이어 벨트 회전
        tail = conveyor_belt[0].pop()
        conveyor_belt[1].append(tail)
        head = conveyor_belt[1].popleft()
        conveyor_belt[0].appendleft(head)

        # 내리는 위치에 도달하면 로봇 내리기
        if conveyor_belt[0][n - 1][1]:
            conveyor_belt[0][n - 1][1] = False

        # 로봇 이동
        for idx in range(n - 2, -1, -1):
            if conveyor_belt[0][idx][1] and conveyor_belt[0][idx + 1][0] >= 1 and not conveyor_belt[0][idx + 1][1]:
                conveyor_belt[0][idx][1] = False
                conveyor_belt[0][idx + 1][0] -= 1
                conveyor_belt[0][idx + 1][1] = True
                if conveyor_belt[0][idx + 1][0] == 0:
                    zero_cnt += 1
        if zero_cnt >= k:
            break

        # 내리는 위치에 도달하면 로봇 내리기
        if conveyor_belt[0][n - 1][1]:
            conveyor_belt[0][n - 1][1] = False

        # 올리는 위치에 로봇 올리기
        if conveyor_belt[0][0][0] >= 1:
            conveyor_belt[0][0][0] -= 1
            conveyor_belt[0][0][1] = True
            if conveyor_belt[0][0][0] == 0:
                zero_cnt += 1

            if zero_cnt == k:
                break

    return step


n, k = map(int, input().split())
durability = list(map(int, input().split()))
conveyor_belt = [deque([]),deque([])]
for idx, value in enumerate(durability):
    if idx < n:
        conveyor_belt[0].append([value, False])
    else:
        conveyor_belt[1].appendleft([value, False])
print(solution())
