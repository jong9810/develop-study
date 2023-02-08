"""2023.02.08"""
# 문제 설명
# 여행가 A는 N * N 크기의 정사각형 공간 위에 서 있다. 이 공간은 1 * 1 크기의 정사각형으로 나누어져 있다.
# 가장 왼쪽 위 좌표는 (1, 1)이며, 가장 오른쪽 아래 좌표는 (N, N)에 해당한다.
# 여행가 A는 상, 하, 좌, 우 방향으로 이동할 수 있으며, 시작 좌표는 항상 (1, 1)이다. 
# 우리 앞에는 여행가 A가 이동할 계획이 적힌 계획서가 놓여 있다.
# 계획서에는 하나의 줄에 띄어쓰기를 기준으로 하여 L, R, U, D 중 하나의 문자가 반복적으로 적혀 있다.
# 각 문자는 왼쪽, 오른쪽, 위, 아래 방향으로 한 칸 이동하라는 의미이다.
# 이때 여행가 A가 N * N  크기의 정사각형 공간을 벗어나는 움직임은 무시된다.
# 예를 들어 (1, 1)의 위치에서 L 혹은 U를 만나면 아무런 행동도 하지 않는다.
# 계획서에 따라 여행가 A가 최종적으로 도착할 좌표를 구하여라.
 
import time
# 입력
n = int(input())
plans = input().split()
x, y = 1, 1

start_time = time.time()

### 방법1.
# for plan in plans:
#     if plan == 'L':
#         if y > 1:
#             y -= 1
#     elif plan == 'R':
#         if y < n:
#             y += 1
#     elif plan == 'U':
#         if x > 1:
#             x -= 1
#     elif plan == 'D':
#         if x < n:
#             x += 1
# print(x, y)

### 방법2.
move_types = ['L', 'R', 'U', 'D']
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

for plan in plans:
    for i, direction in enumerate(move_types):
        if plan == direction:
            nx = x + dx[i]
            ny = y + dy[i]
    if nx < 1 or ny < 1 or nx > n or ny > n:
        continue
    x, y = nx, ny
print(x, y)

end_time = time.time()
print('time: ', end_time - start_time)
