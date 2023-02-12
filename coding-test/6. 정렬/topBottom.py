"""2023.02.11"""
# 위에서 아래로
# 하나의 수열에는 다양한 수가 존재한다. 이러한 수는 크기에 상관없이 나열되어 있다.
# 이 수를 큰 수부터 작은 수의 순서로 정렬해야 한다.
# 수열을 내림차순으로 정렬하는 프로그램을 만드시오.

# 모듈, 라이브러리 import
import time

# 입력
n = int(input())
array = []
for i in range(n):
    array.append(int(input()))

start_time = time.time()

# 방법1. 라이브러리 이용
result = sorted(array, reverse=True)
for i in range(len(result)):
    print(result[i], end=' ')

# 방법2.

end_time = time.time()
print('\ntime:', end_time - start_time)
