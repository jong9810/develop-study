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
    word: '제로초1',
    value: '',
    result: '',
  };
  input;

  onSubmit = (e) => {
    e.preventDefault();
    if (this.state.word[this.state.word.length - 1] === this.state.value[0]) {
      this.setState({
        result: '딩동댕',
        word: this.state.value,
        value: '',
      });
    } else {
      this.setState({
        result: '땡',
        value: '',
      });
    }
  };

  onChange = (e) => {
    this.setState({ value: e.target.value });
    console.log(this.state.value);
  };

  inputRef = (c) => {
    this.input = c;
  };

  render() {
    return (
      <div>
        <div>{this.state.word}</div>
        <form onSubmit={this.onSubmit}>
          <input type='text' onChange={this.onChange} ref={this.inputRef} value={this.state.value} />
          <button>입력</button>
        </form>
        <div>{this.state.result}</div>
      </div>
    );
  }
}
//

module.exports = WordRelay;
