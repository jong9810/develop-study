const React = require('react');
const { Component } = React;

/*/ 1. 클래스 컴포넌트
class GuGuDan extends Component {
  state = {
    value: '',
    result: '',
    first: Math.ceil(Math.random() * 9),
    second: Math.ceil(Math.random() * 9),
  };
  input;

  onSubmitForm = (e) => {
    e.preventDefault();
    if (parseInt(this.state.value) === this.state.first * this.state.second) {
      this.setState((prevState) => {
        return {
          value: '',
          result: '정답: ' + prevState.value,
          first: Math.ceil(Math.random() * 9),
          second: Math.ceil(Math.random() * 9),
        };
      });
    } else {
      this.setState({
        value: '',
        result: '오답',
      });
    }
    this.input.focus();
  };
  onChangeInput = (e) => {
    this.setState({ value: e.target.value });
  };
  onInputRef = (c) => {
    this.input = c;
  };

  render() {
    return (
      <div>
        <div>
          {this.state.first}곱하기 {this.state.second}은(는)?
        </div>
        <form onSubmit={this.onSubmitForm}>
          <input type='number' value={this.state.value} ref={this.onInputRef} onChange={this.onChangeInput} />
          <button>입력!</button>
        </form>
        <div id='result'>{this.state.result}</div>
      </div>
    );
  }
}
/*/

// 2. 함수 컴포넌트
const GuGuDan = () => {
  const [value, setValue] = React.useState('');
  const [result, setResult] = React.useState('');
  const [first, setFirst] = React.useState(Math.ceil(Math.random() * 9));
  const [second, setSecond] = React.useState(Math.ceil(Math.random() * 9));
  const inputRef = React.useRef(null);

  const onSubmitForm = (e) => {
    e.preventDefault();
    if (parseInt(value) === first * second) {
      setValue('');
      setResult('정답: ' + value);
      setFirst(Math.ceil(Math.random() * 9));
      setSecond(Math.ceil(Math.random() * 9));
    } else {
      setValue('');
      setResult('오답');
    }
    inputRef.current.focus();
  };
  const onChangeInput = (e) => {
    setValue(e.target.value);
  };

  return (
    <div>
      <div>
        {first}곱하기 {second}은(는)?
      </div>
      <form onSubmit={onSubmitForm}>
        <input type='number' value={value} onChange={onChangeInput} ref={inputRef} />
        <button>입력!</button>
      </form>
      <div id='result'>{result}</div>
    </div>
  );
};
//

module.exports = GuGuDan;
