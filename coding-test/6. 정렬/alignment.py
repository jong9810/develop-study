"""2023.02.11"""

# 모듈, 라이브러리 import
import time

# 입력


start_time = time.time()

# 방법1. 선택 정렬
# array = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]
# for i in range(len(array)):
#     for j in range(i + 1, len(array)):
#         if array[i] > array[j]:
#             array[i], array[j] = array[j], array[i]
#             print(array)
# print('선택 정렬 :', array)

# 방법2. 삽입 정렬


end_time = time.time()
print('\ntime:', end_time - start_time)
