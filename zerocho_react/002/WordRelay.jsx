const React = require('react');
// const { Component } = React;
const { useState, useRef } = React;

/*/ 클래스 컴포넌트
class WordRelay extends Component {
  state = {
    word: '제로초 컴포넌트',
    value: '',
    result: '',
  };
  input;

  onSubmit = (e) => {
    e.preventDefault();
    if (this.state.word[this.state.word.length - 1] === this.state.value[0]) {
      this.setState({
        word: this.state.value,
        value: '',
        result: '딩동댕',
      });
    } else {
      this.setState({
        value: '',
        result: '땡',
      });
    }
    this.input.focus();
  };
  onChange = (e) => {
    this.setState({ value: e.target.value });
  };
  inputRef = (c) => {
    this.input = c;
  };

  render() {
    return (
      <div>
        <div>{this.state.word}</div>
        <form onSubmit={this.onSubmit}>
          <input type='text' onChange={this.onChange} value={this.state.value} ref={this.inputRef} />
          <button>입력</button>
        </form>
        <div>{this.state.result}</div>
      </div>
    );
  }
}
/*/

// 함수 컴포넌트(Hooks)
const WordRelay = () => {
  const [word, setWord] = useState('제로초 함수');
  const [result, setResult] = useState('');
  const inputRef = useRef(null);

  const onSubmit = (e) => {
    e.preventDefault();
    // console.dir(e.target[0]);
    // console.dir(e.target.children.wordInput);
    if (word[word.length - 1] === e.target.children.wordInput.value[0]) {
      setWord(e.target[0].value);
      e.target[0].value = '';
      setResult('딩동댕');
    } else {
      e.target[0].value = '';
      setResult('땡');
    }
    inputRef.current.focus();
  };

  return (
    <div>
      <div>제시어 : {word}</div>
      <form onSubmit={onSubmit}>
        <label htmlFor='wordLabel'>글자를 입력하세요.</label>
        <br />
        <input id='wordInput' ref={inputRef} />
        <button>입력</button>
      </form>
      <div>{result}</div>
    </div>
  );
};
//

module.exports = WordRelay;
