// 1부터 입력받은 숫자 n 사이에 있는 소수의 개수를 반환하는 함수, solution을 만들어 보세요.
// 소수는 1과 자기 자신으로만 나누어지는 수를 의미합니다.(1은 소수가 아닙니다.)
// n은 2이상 1000000이하의 자연수입니다.

function solution(n) {
  const decimal = [];
  for (let i = 2; i <= n; i++) {
    let isDecimal = true;
    for (let j of decimal) {
      if (i % j === 0) {
        isDecimal = false;
        break;
      }
    }
    if (isDecimal) {
      decimal.push(i);
    }
  }
  // console.log(decimal);
  return decimal.length;
}

solution(2);
