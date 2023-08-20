"""2023.02.21"""
# # # 문제 설명
#

# # # 모듈, 라이브러리 import
# from collections import deque
# import sys
# import math
import time

# # # 입력


# # # 시작 시간 저장
start_time = time.time()

# # # 프로그램 코드
board = []
for i in range(10):
    board.append(list(map(int, input().split())))

r, c = 1, 1
while True:
    if board[r][c] == 2:
        board[r][c] = 9
        break
    board[r][c] = 9
    if board[r][c + 1] != 1:
        c += 1
    elif board[r + 1][c] != 1:
        r += 1
    else:
        break

for row in board:
    for col in row:
        print(col, end=" ")
    print()

# # # 프로그램 실행 시간 출력
end_time = time.time()
print('\ntime:', end_time - start_time)
