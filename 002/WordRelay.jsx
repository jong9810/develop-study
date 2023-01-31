const React = require('react');
const { Component } = React;
// const { useState, useRef } = React;

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
  const [value, setValue] = useState('');
  const [result, setResult] = useState('');
  const inputRef = useRef(null);

  const onSubmit = (e) => {
    e.preventDefault();
    if (word[word.length - 1] === value[0]) {
      setWord(value);
      setValue('');
      setResult('딩동댕');
    } else {
      setValue('');
      setResult('땡');
    }
    inpurRef.current.focus();
  };

  const onChange = (e) => {
    setValue(e.target.value);
  };

  return (
    <div>
      <div>제시어 : {word}</div>
      <form onSubmit={onSubmit}>
        <input type='text' value={value} onChange={onChange} ref={inputRef} />
        <button>입력</button>
      </form>
      <div>{result}</div>
    </div>
  );
};
//

module.exports = WordRelay;
