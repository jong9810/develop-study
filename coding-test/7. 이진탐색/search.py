"""2023.02.17"""
# 순차 탐색, 이진 탐색 알고리즘

# 모듈, 라이브러리 import
import time


def sequential_search(array, target, length):  # 순차 탐색
    for i in range(length):
        if array[i] == target:
            return i
    return None


def binary_search1(array, target, start, end):  # 이진 탐색(반복문)
    while start <= end:
        mid = (start + end) // 2
        if target == array[start]:
            return start
        if target == array[end]:
            return end
        if target == array[mid]:
            return mid
        if target < array[mid]:
            end = mid - 1
            mid = (start + end) // 2
        elif target > array[mid]:
            start = mid + 1
            mid = (start + end) // 2
    return None


def binary_search2(array, target, start, end):  # 이진 탐색(재귀)
    if start > end:
        return None
    if target == array[start]:
        return start
    if target == array[end]:
        return end
    mid = (start + end) // 2
    if target == array[mid]:
        return mid
    if target < array[mid]:
        return binary_search2(array, target, start, mid - 1)
    if target > array[mid]:
        return binary_search2(array, target, mid + 1, end)

    # 프로그램 코드
start_time = time.time()
array = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print(sequential_search(array, 0, len(array)))
print(binary_search1(array, 5, 0, len(array) - 1))
print(binary_search2(array, 9, 0, len(array) - 1))

# 프로그램 실행 시간 출력
end_time = time.time()
print('\ntime:', end_time - start_time)
