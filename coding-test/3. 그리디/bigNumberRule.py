"""2023.02.08"""
# 큰 수의 법칙은 다양한 수로 이루어진 배열이 있을 때 주어진 수들을 M번 더하여 가장 큰 수를 만드는 법칙이다.
# 단, 배열의 특정한 인덱스에 해당하는 수가 연속해서 K번을 초과해서 더해질 수 없는 것이 이 법칙의 특징이다.
# 단, 서로 다른 인덱스에 해당하는 수가 같은 경우에도 서로 다른 것으로 간주한다.

import time
N, M, K = map(int, input().split(' '))
data = list(map(int, input().split(' ')))

start_time = time.time()

data.sort()
first = data[N-1]
second = data[N-2]
result = 0

### 방법1. 
# add_first = 0
# count = 0
# while(count < M):
#     if add_first == K:
#         result += second
#         add_first = 0
#     else:
#         result += first
#         add_first += 1
#     count += 1

### 방법2.
# while True:
#     for i in range(K):
#         if M == 0:
#             break
#         result += first
#         M -= 1
#     if M == 0:
#         break
#     result += second
#     M -= 1

### 방법3.
count = int(M / (K + 1)) * K
count += M % (K + 1) # 가장 큰 수가 더해지는 횟수

result = 0
result += first * count
result += second * (M - count)

print(result)



end_time = time.time()
print('time: ', end_time - start_time)
