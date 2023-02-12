"""2023.02.12"""
# 성적이 낮은 순서로 학생 출력하기
# N명의 학생 정보가 있다. 학생 정보는 학생의 이름과 학생의 성적으로 구분된다.
# 각 학생의 이름과 성적 정보가 주어졌을 때 성적이 낮은 순서대로 학생의 이름을 출력하는 프로그램을 작성하시오.

# 모듈, 라이브러리 import
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


def quick_sort():
    pass


def quick_sort2():
    pass


# print(quick_sort(info))
# result = quick_sort(info)
# print(result)

# # 방법4. 계수 정렬
count = [0] * 101

sorted_score = []
for i in range(len(info)):
    count[info[i][1]] += 1

for i in range(len(count)):
    if count[i] == 0:
        continue
    else:
        for j in range(count[i]):
            sorted_score.append(i)

for i in range(n):
    if (sorted_score[i] == info[i][1]):
        v = info[i][0]
        print(v, end=' ')


# for i in range(n):
#     print(info[i][0], end=' ')

end_time = time.time()
print('\ntime:', end_time - start_time)
