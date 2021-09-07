import sys

input = sys.stdin.readline

n = int(input().strip())
arr = [[0] * 58 for _ in range(58)]
# 명제의 개수
count = 0
for _ in range(n):
    start, direction, end = input().split()
    if start == end:
        continue

    if not arr[ord(start) - 65][ord(end) - 65]:
        arr[ord(start) - 65][ord(end) - 65] = 1
        count += 1

# 시작과 종료 사이에 존재하는 중간
for mid in range(58):
    for start in range(58):
        for end in range(58):
            if start != end and not arr[start][end] and arr[start][mid] and arr[mid][end]:
                arr[start][end] = 1
                count += 1
print(count)
for start in range(58):
    for end in range(58):
        if arr[start][end]:
            print(f"{chr(start+65)} => {chr(end+65)}")
