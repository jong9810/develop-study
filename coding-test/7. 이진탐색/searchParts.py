"""2023.02.17"""
# # # 부품 찾기
# 동빈이네 전기 매장에는 부품이 N개 있다. 각 부품은 정수 형태의 고유한 번호가 있다.
# 어느 날 손님이 M개의 종류의 부품을 대량으로 구매하겠다며 당일 날 견적서를 요청했다.
# 동빈이는 때를 놓치지 않고 손님이 문의한 부품 M개의 종류를 모두 확인해서 견적서를 작성해야 한다.
# 이때 가게 안에 부품이 모두 있는지 확인하는 프로그램을 작성하시오.
# 손님이 요청한 부품 번호의 순서대로 부품을 확인해 부품이 있으면 yes를, 없으면 no를 출력한다.

# # # 모듈, 라이브러리 import
import time
import sys

# # # 입력
# n = int(input())
# all_parts = list(map(int, input().split()))
# m = int(input())
# request_parts = list(map(int, input().split()))
n, m = 5, 3
all_parts = [8, 3, 7, 9, 2]
request_parts = [5, 7, 9]

# # # 시작 시간 저장
start_time = time.time()

# # # 프로그램 코드


def sequential_search(array, target):  # 순차 탐색
    for i in range(len(array)):
        if array[i] == target:
            return 'yes'
    return 'no'


def binary_search(array, target, start, end):  # 이진 탐색
    if start > end:
        return 'no'
    mid = (start + end) // 2
    if target == array[start] or target == array[end] or target == array[mid]:
        return 'yes'
    if target < array[mid]:
        return binary_search(array, target, start, mid - 1)
    if target > array[mid]:
        return binary_search(array, target, mid + 1, end)


print('1. 순차 탐색 이용')
for i in request_parts:
    print(sequential_search(all_parts, i), end=' ')

print('\n2. 이진 탐색 이용')
all_parts2 = sorted(all_parts)
for i in request_parts:
    print(binary_search(all_parts2, i, 0, len(all_parts) - 1), end=' ')

print('\n3. 계수 정렬 이용')
parts_list = [0] * 10001
for i in all_parts:
    parts_list[i] += 1
for i in request_parts:
    if parts_list[i] > 0:
        print('yes', end=' ')
    elif parts_list[i] == 0:
        print('no', end=' ')

print('\n4. 집합 자료형 이용')
all_parts = set(all_parts)
for i in request_parts:
    if i in all_parts:
        print('yes', end=' ')
    else:
        print('no', end=' ')


# # # 프로그램 실행 시간 출력
end_time = time.time()
print('\ntime:', end_time - start_time)
