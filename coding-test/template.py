"""2023.02.12"""
# 문제 설명
#

# 모듈, 라이브러리 import
import time

# 입력


# 프로그램 코드
start_time = time.time()

# 방법1.
array = [
    ['홍길동', 100],
    ['김종인', 85]
]
print(array[0].index(100))


# 프로그램 실행 시간 출력
end_time = time.time()
print('\ntime:', end_time - start_time)
