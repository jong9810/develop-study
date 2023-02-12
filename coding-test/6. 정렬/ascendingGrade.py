"""2023.02.12"""
# 성적이 낮은 순서로 학생 출력하기
# N명의 학생 정보가 있다. 학생 정보는 학생의 이름과 학생의 성적으로 구분된다.
# 각 학생의 이름과 성적 정보가 주어졌을 때 성적이 낮은 순서대로 학생의 이름을 출력하는 프로그램을 작성하시오.

# 모듈, 라이브러리 import
import time

# 입력
n = int(input())
name = []
score = []
for i in range(n):
    Input = input().split()
    name.append(Input[0])
    score.append(int(Input[1]))

start_time = time.time()

# 방법1.


end_time = time.time()
print('\ntime:', end_time - start_time)
