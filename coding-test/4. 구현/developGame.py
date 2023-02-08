"""2023.02.08"""
# 문제 설명
# 현민이는 게임 캐릭터가 맵 안에서 움직이는 시스템을 개발 중이다.
# 캐릭터가 있는 장소는 1 * 1 크기의 정사각형으로 이뤄진 N * M 크기의 직사각형으로, 각각의 칸은 육지 또는 바다이다.
# 캐릭터는 동서남북 중 한 곳을 바라본다.
# 맵의 각 칸은 (A, B)로 나타낼 수 있고, A는 북쪽으로부터 떨어진 칸의 개수, B는 서쪽으로부터 떨어진 칸의 개수이다.
# 캐릭터는 상하좌우로 움직일 수 있고, 바다로 되어 있는 공간에는 갈 수 없다.
# 캐릭터의 움직임을 설정하기 위해 정해 놓은 메뉴얼은 이러하다.
# 1. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향(반시계 방향으로 90도 회전한 방향)부터 차례대로 갈 곳을 정한다.
# 2. 캐릭터의 바로 왼쪽 방향에 아직 가보지 않은 칸이 존재한다면, 왼쪽 방향으로 회전한 다음 왼쪽으로 한 칸 전진한다.
#    캐릭터의 바로 왼쪽 방향에 가본 칸이 있다면, 왼쪽 방향으로 회전만 수행하고 1단계로 돌아간다.
# 3. 만약 네 방향 모두 이미 가본 칸이거나 바다로 되어 있는 칸인 경우에는, 바라보는 방향을 유지한 채로 한 칸 뒤로 가고 1단계로 돌아간다.
#    단, 이때 뒤쪽 방향이 바다인 칸이라 뒤로  갈 수 없는 경우에는 움직임을 멈춘다.
# 현민이는 위 과정을 반복적으로 수행하면서 캐릭터의 움직임에 이상이 있는지 테스트하려고 한다.
# 메뉴얼에 따라 캐릭터를 이동시킨 뒤에, 캐릭터가 방문한 칸의 수를 출력하는 프로그램을 만드시오.

import time
# 입력
# n, m = map(int, input().split())                      # n : 맵의 세로 크기, m : 맵의 가로 크기
# pos = [0, 0]
# pos[0], pos[1], direction = map(int, input().split()) # direction(0, 1, 2, 3 : 북, 동, 남, 서)
# A, B = pos[1], pos[0]                                 # A : 북쪽으로부터 떨어진 거리, B : 서쪽으로부터 떨어진 거리
# gameBoard = []                                        # 게임판
# for i in range(n):
#     gameBoard.append(list(map(int, input().split())))

n, m = 4, 4                 # n : 맵의 세로 크기, m : 맵의 가로 크기
A, B = 1, 1                 # A : 북쪽으로부터 떨어진 거리, B : 서쪽으로부터 떨어진 거리
direction = 0               # direction(0, 1, 2, 3) : 북, 동, 남, 서
gameBoard = [[1, 1, 1, 1],
             [1, 0, 0, 1],
             [1, 1, 0, 1],
             [1, 1, 1, 1]]  # 게임판(0 : 육지, 1 : 바다)
moves = [[-1, 0], [0, 1], [1, 0], [0, -1]]  # 북, 동, 남, 서 방향 움직임
gameBoard[A][B] = 2         # 캐릭터가 가본 칸 : 2

start_time = time.time()

# 방법1.


def turnLeft(_direction):
    if _direction > 0 and _direction < 4:
        _direction -= 1
    elif _direction == 0:
        _direction = 3
    else:
        _direction = 0
    return _direction


def turnAround(_direction):
    return turnLeft(turnLeft(_direction))


turnLeftCount = 0
count = 1
while True:
    direction = turnLeft(direction)
    for dir, move in enumerate(moves):
        if direction == dir:
            nA = A + move[0]
            nB = B + move[1]
    if turnLeftCount >= 4:
        direction = turnAround(direction)
        for dir, move in enumerate(moves):
            if direction == dir:
                nA = A + move[0]
                nB = B + move[1]
        if gameBoard[nA][nB] == 1 or gameBoard[nA][nB] == 2:
            break
        else:
            direction = turnAround(direction)
            gameBoard[nA][nB] = 2
            A, B = nA, nB
            turnLeftCount = 0
            count += 1
            continue
    if gameBoard[nA][nB] == 1 or gameBoard[nA][nB] == 2:
        turnLeftCount += 1
        continue
    else:
        gameBoard[nA][nB] = 2
        A, B = nA, nB
        turnLeftCount = 0
        count += 1
print(count)

end_time = time.time()
print('time:', end_time - start_time)
