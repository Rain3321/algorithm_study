import sys

input = sys.stdin.readline

# m : 상어 수 / k : 상어가 냄새를 뿌린 뒤 사라지는 시간
n, m, k = map(int, input().split())

# 처음 주어지는 상어의 위치
arr = [list(map(int, input().split())) for _ in range(n)]

# 상어의 방향
shark_directions = dict()
for shark_num, direction in zip([i for i in range(1, m + 1)], list(map(int, input().split()))):
    shark_directions[shark_num] = direction

# 상어마다 가지고 있는 방향별 우선순위 정보
shark_direction_priority = dict()
for i in range(1, m + 1):
    temp = []
    for _ in range(4):
        temp.append(list(map(int, input().split())))
    shark_direction_priority[i] = temp

# 방향 상 하 좌 우
direction = dict()
for idx, dir in zip([1,2,3,4],[(-1, 0), (1, 0), (0, -1), (0, 1)]):
    direction[idx] = dir


# 상어가 뿌린 냄새 정보 (상어번호, 냄새가 머무는 시간)
smell = [[[0, 0]] * n for _ in range(n)]

def update_smell(arr, smell):
    for x in range(n):
        for y in range(n):
            # 냄새가 남아있는 경우
            if smell[x][y][1] > 0:
                smell[x][y][1] -= 1
            # 현재 상어가 있는 위치면 냄새를 뿌린다.
            if arr[x][y] != 0:
                smell[x][y] = [arr[x][y], k]


def shark_move(arr, smell, shark_directions):
    next_arr = [[0] * n for _ in range(n)]
    for x in range(n):
        for y in range(n):
            if arr[x][y] != 0:
                # 해당 상어가 현재 바라보고 있는 방향
                cur_shark_direction = shark_directions[arr[x][y]]
                # 상어가 현재 바라보고 있는 방향에 대한 우선순위 순으로 진행
                for idx in shark_direction_priority[arr[x][y]][cur_shark_direction - 1]:
                    nx, ny = x + direction[idx][0], y + direction[idx][1]
                    if 0 <= nx < n and 0 <= ny < n:
                        # 냄새가 나지 않는 곳인 경우
                        if smell[nx][ny][1] == 0:
                            # 상어의 방향 변경
                            shark_directions[arr[x][y]] = idx
                            # 상어 이동
                            if next_arr[nx][ny] == 0:
                                next_arr[nx][ny] = arr[x][y]
                            # 이동하려는 칸에 다른 상어가 존재하는 경우 상어의 번호가 더 작은 상어가 남음
                            else:
                                next_arr[nx][ny] = min(arr[x][y], next_arr[nx][ny])
                            break

                # 상어 주위에 모두 냄새가 있는 경우 break문이 일어나지 않으므로 for-else를 사용하여 주변에 자신의 냄새가 있는 곳으로 이동
                else:
                    for idx in shark_direction_priority[arr[x][y]][cur_shark_direction - 1]:
                        nx, ny = x + direction[idx][0], y + direction[idx][1]
                        if 0 <= nx < n and 0 <= ny < n:
                            # 자신의 냄새가 있는 곳
                            if smell[nx][ny][0] == arr[x][y]:
                                # 상어의 방향 변경
                                shark_directions[arr[x][y]] = idx
                                # 상어 이동
                                next_arr[nx][ny] = arr[x][y]
                                break
    return next_arr


def solution(arr, smell):
    time = 0
    while True:
        update_smell(arr, smell)
        next_arr = shark_move(arr, smell, shark_directions)
        time += 1
        arr = next_arr

        # 상어가 1번 상어만 있는지 확인하기
        another = False
        for x in range(n):
            for y in range(n):
                # 상어의 번호가 1이 아닌 수가 존재하는 경우
                if arr[x][y] > 1:
                    another = True
        # 1번 상어만 존재하는 경우
        if not another:
            print(time)
            break

        if time >= 1000:
            print(-1)
            break

solution(arr, smell)
