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

# 방법 3. 퀵 정렬
# def quick_sort1(array, start, end):  # 3-1. 전통 퀵 정렬(호어 분할 방식)
#     if start >= end:
#         return
#     pivot = start       # 피벗은 첫 번째 원소
#     left = start + 1    # 왼쪽 -> 오른쪽 인덱스
#     right = end         # 오른쪽 -> 왼쪽 인덱스
#     while left <= right:
#         # 피벗보다 큰 데이터를 찾을 때까지 반복
#         while left <= end and array[left] <= array[pivot]:
#             left += 1
#         # 피벗보다 작은 데이터를 찾을 때까지 반복
#         while right > start and array[right] >= array[pivot]:
#             right -= 1
#         if left > right:  # 엇갈렸다면 작은 데이터와 피벗을 교체
#             array[right], array[pivot] = array[pivot], array[right]
#         else:  # 엇갈리지 않았다면 작은 데이터와 큰 데이터를 교체
#             array[left], array[right] = array[right], array[left]
#         # print(array)
#     # 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬 수행
#     quick_sort1(array, start, right - 1)
#     quick_sort1(array, right + 1, end)


# def quick_sort2(array):  # 3-2. 파이썬 장점 살린 퀵 정렬(호어 분할 방식)
#     # 리스트가 하나 이하의 원소만을 담고 있다면 종료
#     if len(array) <= 1:
#         return array

#     pivot = array[0]  # 피벗은 첫 번째 원소
#     tail = array[1:]  # 피벗을 제외한 리스트

#     left_side = [x for x in tail if x <= pivot]  # 분할된 왼쪽 부분
#     right_side = [x for x in tail if x > pivot]  # 분할된 오른쪽 부분

#     # 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬을 수행하고, 전체 리스트를 반환
#     # 원본을 수정하지 않고 새로운 리스트 반환함.
#     return quick_sort2(left_side) + [pivot] + quick_sort2(right_side)


# quick_sort1(array, 0, len(array) - 1)
# print(array)
# print(quick_sort2(array))
# print(array)

# 방법4. 계수 정렬
# 모든 원소의 값이 0이나 양의 정수라고 가정
# array = [7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2]
# # 모든 범위를 포함하는 리스트 선언(모든 값은 0으로 초기화)
# count = [0] * (max(array) - min(array) + 1)

# for i in range(len(array)):
#     count[array[i]] += 1  # 각 데이터에 해당하는 인덱스의 값 증가

# for i in range(len(count)):  # 리스트에 기록된 정렬 정보 확인
#     for j in range(count[i]):
#         print(i, end=' ')  # 띄어쓰기를 구분으로 등장한 횟수만큼 인덱스 출력

# 방법5. sorted(), sort()
# result = sorted(array)
# print('array :', array)
# print('result :', result)
# array.sort()
# print('array.sort() :', array)

# def setting1(data):
#     return data[0]


# def setting2(data):
#     return data[1]


# array = [('바나나', 2), ('사과', 5), ('당근', 3)]
# result = sorted(array, key=setting1)
# print('result :', result)
# array.sort(key=setting2)
# print('array.sort() :', array)


end_time = time.time()
print('\ntime:', end_time - start_time)
