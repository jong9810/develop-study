"""2023.02.08"""
# 숫자 카드 게임은 여러 개의 숫자 카드 중에서 가장 높은 숫자가 쓰인 카드 한 장을 뽑는 게임이다.
# 단, 게임의 룰을 지키며 카드를 뽑아야 하고 룰은 다음과 같다.
# 1. 숫자가 쓰인 카드들이 N * M 형태로 놓여 있다. 이때 N은 행의 개수를 의미하며, M은 열의 개수를 의미한다.
# 2. 먼저 뽑고자 하는 카드가 포함되어 있는 행을 선택한다.
# 3. 그다음 선택된 행에 포함된 카드들 중 가장 숫자가 낮은 카드를 뽑아야 한다.
# 4. 따라서 처음에 카드를 골라낼 행을 선택할 때, 이후에 해당 행에서 가장 숫자가 낮은 카드를 뽑을 것을 고려하여
#    최종적으로 가장 높은 숫자의 카드를 뽑을 수 있도록 전략을 세워야 한다.

import time
N, M = map(int, input().split())

start_time = time.time()

### 방법1.
# cards = []
# for i in range(N):
#         cards.append(list(map(int, input().split())))
# # print(cards)

# maxRow = min(cards[0])
# index = 0
# for i in range(N):
#     if min(cards[i]) >= maxRow:
#         maxRow = min(cards[i])

# print(maxRow)

### 방법2. 
# result = 0
# for i in range(N):
#     data = list(map(int, input().split()))
#     min_value = min(data)
#     result = max(result, min_value)
# print(result)

### 방법3. 이중반복문
result = 0
for i in range(N):
    data = list(map(int, input().split()))
    min_value = 10001
    for j in data:
        min_value = min(min_value, j)
    result = max(result, min_value)
print(result)
end_time = time.time()
print('time: ', end_time - start_time)
