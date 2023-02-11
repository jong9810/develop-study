"""2023.02.11"""

# 모듈, 라이브러리 import
import time

# 입력
array = [5, 7, 9, 0, 3, 1, 6, 2, 4, 8]


start_time = time.time()

# 방법1. 선택 정렬
# for i in range(len(array)):
#     for j in range(i + 1, len(array)):
#         if array[i] > array[j]:
#             array[i], array[j] = array[j], array[i]
#             print(array)
# print('선택 정렬 :', array)

# 방법2. 삽입 정렬
# for i in range(1, len(array)):
#     for j in range(i, 0, -1):
#         if array[j] < array[j - 1]:
#             array[j], array[j - 1] = array[j - 1], array[j]
#         else:
#             break
# print(array)


def quick_sort(array, start, end):  # 방법3. 퀵 정렬(호어 분할 방식)
    if start >= end:
        return
    pivot = start       # 피벗은 첫 번째 원소
    left = start + 1    # 왼쪽 -> 오른쪽 인덱스
    right = end         # 오른쪽 -> 왼쪽 인덱스
    while left <= right:
        # 피벗보다 큰 데이터를 찾을 때까지 반복
        while left <= end and array[left] <= array[pivot]:
            left += 1
        # 피벗보다 작은 데이터를 찾을 때까지 반복
        while right > start and array[right] >= array[pivot]:
            right -= 1
        if left > right:  # 엇갈렸다면 작은 데이터와 피벗을 교체
            array[right], array[pivot] = array[pivot], array[right]
        else:  # 엇갈리지 않았다면 작은 데이터와 큰 데이터를 교체
            array[left], array[right] = array[right], array[left]
        print(array)
    # 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬 수행
    quick_sort(array, start, right - 1)
    quick_sort(array, right + 1, end)


quick_sort(array, 0, len(array) - 1)
print(array)


end_time = time.time()
print('\ntime:', end_time - start_time)
