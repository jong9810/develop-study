"""2023.02.12"""
# 성적이 낮은 순서로 학생 출력하기
# N명의 학생 정보가 있다. 학생 정보는 학생의 이름과 학생의 성적으로 구분된다.
# 각 학생의 이름과 성적 정보가 주어졌을 때 성적이 낮은 순서대로 학생의 이름을 출력하는 프로그램을 작성하시오.

# 모듈, 라이브러리 import
from os import startfile
import time

# 입력
# n = int(input())
# info = []
# for i in range(n):
#     info.append(input().split())
#     info[i][1] = int(info[i][1])

n = 5
info = [
    ['A', 15],
    ['B', 95],
    ['C', 100],
    ['D', 15],
    ['E', 87],
]

# 프로그램 코드
start_time = time.time()

# # 방법1. 선택 정렬
# for i in range(n):
#     for j in range(i + 1, n):
#         if info[i][1] > info[j][1]:
#             info[i], info[j] = info[j], info[i]

# # 방법2. 삽입 정렬
# for i in range(1, n):
#     for j in range(i, 0, -1):
#         if info[j][1] < info[j - 1][1]:
#             info[j], info[j - 1] = info[j - 1], info[j]
#         else:
#             break

# # 방법3. 퀵 정렬


def quick_sort(array, start, end):
    if start >= end:
        return
    pivot = start
    left = start + 1
    right = end
    while left <= right:
        while left <= end and array[left] <= array[pivot]:
            left += 1
        while right > start and array[right] >= array[pivot]:
            right -= 1
        if left > right:
            array[right], array[pivot] = array[pivot], array[right]
        else:
            array[left], array[right] = array[right], array[left]
    quick_sort(array, start, right - 1)
    quick_sort(array, right + 1, end)


def quick_sort2(array):
    if len(array) <= 1:
        return array

    pivot = array[0]
    tail = array[1:]

    left_side = [x for x in tail if x <= pivot]
    print(left_side)
    right_side = [x for x in tail if x > pivot]
    print(right_side)

    return quick_sort2(left_side) + [pivot] + quick_sort2(right_side)


# # quick_sort
# info = [63, 15, 85, 63, 100]
# quick_sort(info, 0, len(info) - 1)
# print(info)


# # quick_sort2
# info = [63, 15, 85, 63, 100]
# info = quick_sort2(info)
# print(info)

# # 방법4. 계수 정렬


# # 방법5. 라이브러리 사용
# info = sorted(info, key=lambda student: student[1])
# for i in range(n):
#     print(info[i][0], end=' ')
end_time = time.time()
print('\ntime:', end_time - start_time)
