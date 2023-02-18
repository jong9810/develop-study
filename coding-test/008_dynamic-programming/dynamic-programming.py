"""2023.02.18"""
# # # 다이나믹 프로그래밍

# # # 모듈, 라이브러리 import
import time
import sys
from collections import deque

# # # 입력
d = [0] * 100  # 메모이제이션하기 위한 리스트 초기화

# # # 시작 시간 저장
start_time = time.time()

# # # 프로그램 코드


# def fibo(x):  # 0. 피보나치 수열(다이나믹 프로그래밍 X)
#     if x <= 0:
#         return None
#     if x == 1 or x == 2:
#         return 1
#     else:
#         return fibo(x - 1) + fibo(x - 2)
# # 위와 같은 함수는 x가 커짐에 따라 수행 시간이 기하급수적으로 늘어난다.
# # x가 커질 수록 동일한 연산을 반복하는 횟수가 많아지기 때문에 시간 복잡도는 O(2^N)이 된다.


# print(fibo(4))

# 1. 피보나치 수열(다이나믹 프로그래밍 O, 반복적)
d = [0] * 100
d[1] = 1
d[2] = 1
n = 99
for i in range(3, n+1):
    d[i] = d[i - 1] + d[i - 2]


def pibo(x):  # 2. 피보나치 수열(다이나믹 프로그래밍 O, 재귀적)
    if x <= 0:
        return None
    # print('f(' + str(x) + ')', end=' ')
    if x == 1 or x == 2:
        return 1
    if d[x] != 0:
        return d[x]
    d[x] = pibo(x - 1) + pibo(x - 2)
    return d[x]
# 다이나믹 프로그래밍(메모이제이션)을 사용한 피보나치 수열 알고리즘은 시간 복잡도가 O(N)이다.


print('1. 반복적 다이나믹 프로그래밍')
print(d[n])
d = [0] * 100
print('2. 재귀적 다이나믹 프로그래밍')
print(pibo(99))

# # # 프로그램 실행 시간 출력
end_time = time.time()
print('\ntime:', end_time - start_time)
