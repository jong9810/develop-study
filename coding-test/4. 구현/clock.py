"""2023.02.08"""
# 정수 N이 입력되면 00시 00분 00초부터 N시 59분 59초까지 의 모든 시각 중에서 
# 3이 하나라도 포함된 모든 경우의 수를 구하는 프로그램을 작성하시오.

import time
# 입력
n = int(input())

start_time = time.time()

### 방법1. 완전탐색(전체 데이터 개수가 100만 개 이하일 때 적절)
result = 0
for i in range(n + 1):
    for j in range(60):
        for k in range(60):
            if '3' in str(i) + str(j) + str(k):
                result += 1
print(result)

end_time = time.time()
print('time: ', end_time - start_time)
