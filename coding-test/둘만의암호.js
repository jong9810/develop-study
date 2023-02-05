// 코딩 테스트 연습용 파일

function solution(s, skip, index) {
  let answer = '';
  let additionalIdx = Array(s.length).fill(0);
  const resultString = [];

  for (let i = 0; i < s.length; i++) {
    const charCode = s.charCodeAt(i);
    for (let j = 1; j <= index; j++) {
      let checkCode = charCode + j;
      if (checkCode > 122) {
        checkCode = 97;
      }
      let checkChar = String.fromCharCode(checkCode);
      if (skip.indexOf(checkChar) !== -1) {
        additionalIdx[i] += 1;
      }
    }
  }
  for (let i = 0; i < s.length; i++) {
    let resultCode = s.charCodeAt(i) + index + additionalIdx[i];
    if (resultCode > 122) {
      resultCode = resultCode - 26;
    }
    const resultChar = String.fromCharCode(resultCode);
    resultString.push(resultChar);
  }
  answer = resultString.join('');

  return answer;
}
