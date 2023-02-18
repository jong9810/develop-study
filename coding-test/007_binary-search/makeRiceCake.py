"""2023.02.18"""
# # # 떡볶이 떡 만들기
# 동빈이네 떡볶이 떡은 재밌게도 떡볶이 떡의 길이가 일정하지 않다.
# 대신에 한 봉지 안에 들어가는 떡의 총 길이는 절단기로 잘라서 맞춰준다.
# 절단기에 높이 H를 지정하면 줄지어진 떡을 한 번에 절단한다. 높이가 H보다 긴 떡은 H의 위 부분이 잘리고, 낮은 떡은 잘리지 않는다.
# 손님이 왔을 때 요청한 총 길이가 M일 때 적어도 M만큼의 떡을 얻기 위해 절단기에 설정할 수 있는 높이의 최댓값을 구하는 프로그램을 작성하시오.
# 절단기에 잘리는 떡의 개수가 N이고, 각각의 떡의 길이가 다음 줄에 입력된다.
# 떡 길이의 총합은 항상 M 이상으로 주어진다.
# 0 <= 떡 길이 <= 10억 --> 떡 길이(탐색 범위 크기)가 아주 크다. --> 순차 탐색을 하면 시간 초과가 나올 것이다.

# # # 모듈, 라이브러리 import
import time
import sys

# # # 입력
n, m = 4, 6
length = list(map(int, sys.stdin.readline().rstrip().split()))
length2 = sorted(length)

# # # 시작 시간 저장
start_time = time.time()

# # # 프로그램 코드


def binary_search(array, target, start, end):  # 방법1. 재귀 함수
    if start >= end:
        return None
    mid = (start + end) // 2
    sum = 0
    for i in array:
        if i > mid:
            sum += (i - mid)
    if sum == target:
        return mid
    if sum > target:
        return binary_search(array, target, mid + 1, end)
    if sum < target:
        return binary_search(array, target, start, mid - 1)


print('1. 재귀 함수 이용')
print(binary_search(length2, m, 0, max(length2)))

# # # 방법2. 반복문
start = 0
end = max(length2)
result = 0
while start <= end:
    sum = 0
    mid = (start + end) // 2
    for i in length:
        if i > mid:
            sum += (i - mid)
    if sum < m:
        end = mid - 1
    else:
        result = mid
        start = mid + 1

print('2. 반복문 이용')
print(result)

# # # 프로그램 실행 시간 출력
end_time = time.time()
print('\ntime:', end_time - start_time)
