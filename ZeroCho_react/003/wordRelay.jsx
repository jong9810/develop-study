const React = require('react');
const { Component } = React;
const { useState, useRef } = React;

/*/ 내가 만든 코드(작동 안됨 ㅜ)
const WordRelay = () => {
  // state 초기화
  const player = 3;
  const [order, setOrder] = useState(1);
  const [word, setWord] = useState('');
  const [turn, setTurn] = useState(1);
  const [value, setValue] = useState('');
  const [result, setResult] = useState('');
  const inputRef = useRef(null);

  // 함수 선언
  const onSubmitForm = (e) => {
    e.preventDefault();
    console.log(word[word.length - 1]);
    // checkValue(); // 단어 올바른지 검사
    if (order === 1) {
      // 첫 번째 차례이면
      setOrder(order + 1);
      setWord(value);
      setValue('');
      setTurn(order);
    } else {
      // 첫 번째 차례가 아니면
      if (word[word.length - 1] === value[0]) {
        // 끝말잇기 단어이면
        setOrder(order + 1);
        if (order > player) {
          setTurn(order % player);
        } else {
          setTurn(order);
        }
        setWord(value);
        setValue('');
      } else {
        // 끝말잇기 단어가 아니면
        setValue('');
        setResult('단어가 올바르지 않습니다.');
      }
    }
    inputRef.current.focus();
  };
  const onChangeInput = (e) => {
    setValue(e.target.value);
  };

  return (
    <div>
      <div id='turn'>{turn}번째 참가자</div>
      <div id='word'>제시어 : {word}</div>
      <form onSubmit={onSubmitForm}>
        <input type='text' value={value} ref={inputRef} onChange={onChangeInput} />
        <button>입력</button>
      </form>
      <div id='result'>{result}</div>
    </div>
  );
};
/*/

// 제로초 강의 코드
class WordRelay extends Component {
  state = {
    text: 'Hello, word',
  };
  render() {
    return <h1>{this.state.text}</h1>;
  }
}
//

module.exports = WordRelay;
