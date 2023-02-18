"""2023.02.18"""
# # # 1로 만들기
# 정수 X가 주어질 때 정수 X에 사용할 수 있는 연산은 다음과 같이 4가지이다.
# 1) X가 5로 나누어떨어지면, 5로 나눈다.
# 2) X가 3으로 나누어떨어지면, 3으로 나눈다.
# 3) X가 2로 나누어떨어지면, 2로 나눈다.
# 4) X에서 1을 뺀다.
# 정수 X가 주어졌을 때, 연산 4개를 적절히 사용해서 1을 만들려고 한다.
# 연산을 사용하는 횟수의 최솟값을 출력하시오.
# 1 <= X <= 30,000

# # # 모듈, 라이브러리 import
# from collections import deque
# import sys
import time

# # # 입력
n = int(input())

# # # 시작 시간 저장
start_time = time.time()

# # # 프로그램 코드


# # # 프로그램 실행 시간 출력
end_time = time.time()
print('\ntime:', end_time - start_time)
