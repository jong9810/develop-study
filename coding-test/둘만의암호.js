// 프로그래머스 둘만의 암호

function solution(s, skip, index) {
  let answer = '';

  const sCodeArray = [];
  const skipCodeArray = [];
  for (let i = 0; i < s.length; i++) {
    sCodeArray.push(s.charCodeAt(i));
  }
  for (let i = 0; i < skip.length; i++) {
    skipCodeArray.push(skip.charCodeAt(i));
  }

  const resultCodeArray = [];
  sCodeArray.map((sCode) => {
    let checkIdx = 0;
    for (let j = 0; j < index; j++) {
      checkIdx += 1;
      let checkCode = sCode + checkIdx;
      while (checkCode > 122) {
        checkCode = checkCode - 26;
      }
      if (skipCodeArray.includes(checkCode)) {
        j -= 1;
      }
    }
    let resultCode = sCode + checkIdx;
    while (resultCode > 122) {
      resultCode = resultCode - 26;
    }
    resultCodeArray.push(String.fromCharCode(resultCode));
  });

  answer = resultCodeArray.join('');

  return answer;
}

solution('zebraisbeautiful', 'abcdefg', 20);
