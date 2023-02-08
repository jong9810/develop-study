"""2023.02.08"""
# 어떠한 수 N이 1이 될 때까지 다음의 두 과정 중 하나를 반복적으로 선택하여 수행하려고 한다.
# 단, 두 번째 연산은 N이 K로 나누어떨어질 때만 선택할 수 있다.
# 1. N에서 1을 뺀다.
# 2. N을 K로 나눈다.
# N과 K가 주어질 때, N이 1이 될 때까지 1번 혹은 2번의 과정을 수행해야 하는 최소 횟수를 구하여라.

import time
n, k = map(int, input().split())
start_time = time.time()

### 방법1.
# result = 0
# while(n != 1):
#     if n % k == 0:
#         n /= k
#         result += 1
#     else:
#         n -= 1
#         result += 1
# print(result)

### 방법2.
# result = 0
# while n >= k:
#     while n % k != 0:
#         n -= 1
#         result += 1
#     n //= k
#     result += 1
# while n > 1:
#     n -= 1
#     result += 1
# print(result)

### 방법3. 이게 가장 좋은 방법!
result = 0
while True:
    target = (n // k) * k
    result += (n - target)
    n = target
    if n < k:
        break
    result += 1
    n //= k
result += (n - 1)
print(result)

end_time = time.time()
print('time: ', end_time - start_time)