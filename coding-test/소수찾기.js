// 1부터 입력받은 숫자 n 사이에 있는 소수의 개수를 반환하는 함수, solution을 만들어 보세요.
// 소수는 1과 자기 자신으로만 나누어지는 수를 의미합니다.(1은 소수가 아닙니다.)
// n은 2이상 1000000이하의 자연수입니다.

function solution(n) {
  let count = 0;
  for (let i = 1; i <= n; i++) {
    for (let j = 1; j <= parseInt(n / 2); j++) {
      if (i % j === 0) {
        if (i !== j) {
          break;
        }
      }
    }
    count += 1;
  }
  return count;
}

solution(2);
