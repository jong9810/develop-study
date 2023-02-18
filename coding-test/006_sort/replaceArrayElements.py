"""2023.02.12"""
# 두 배열의 원소 교체
# 동빈이는 두 개의 배열 A, B를 가지고 있다. 두 배열은 N개의 원소로 구성되어 있으며, 배열의 원소는 모두 자연수이다.
# 동빈이는 최대 K번의 바꿔치기 연산을 수행할 수 있는데, 바꿔치기 연산이란
# 배열 A에 있는 원소 하나와 배열 B에 있는 원소 하나를 골라서 두 원소를 서로 바꾸는 것을 말한다.
# 동빈이의 최종 목표는 배열 A의 모든 원소의 합이 최대가 되도록 하는 것이며, 여러분은 동빈이를 도와야 한다.

# 모듈, 라이브러리 import
import time

# 입력
# n, k = map(int, input().split())
# A = list(map(int, input().split()))
# B = list(map(int, input().split()))
n, k = 5, 3
A = [1, 2, 5, 4, 3]
B = [5, 5, 6, 6, 5]

# 프로그램 코드
start_time = time.time()

# 방법1.
A.sort()
B.sort(reverse=True)

for i in range(n):
    if A[i] < B[i]:
        A[i], B[i] = B[i], A[i]
    else:
        break
print(sum(A))

# 프로그램 실행 시간 출력
end_time = time.time()
print('\ntime:', end_time - start_time)
