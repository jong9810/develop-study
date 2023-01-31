import React, { useState, useRef, Component } from 'react';

// 클래스 컴포넌트
class NumberBaseball extends Component {
  state = {
    answer: '',
    value: '',
    result: '',
    message: '',
  };
  input;
  tries = [];

  createAnswer = () => {
    const candidate = [];
  };
  checkValue = (value) => {
    const rightValue = true;
    if (value.length !== 4) {
      this.setState({ message: '숫자 4개로 입력해주세요.' });
      rightValue = false;
    } else if (!Number(value)) {
      this.setState({ message: '숫자로 입력해주세요.' });
      rightValue = false;
    } else if (new Set(value) !== 4) {
      this.setState({ message: '중복되지 않게 입력하세요.' });
      rightValue = false;
    } else if (this.state.tries.includes(value)) {
      this.setState({ message: '이미 시도했던 값입니다.' });
    }
    return rightValue;
  };
  checkResult = () => {
    //
  };
  onSubmit = (e) => {
    e.preventDefault();
    if (!checkValue(this.state.value)) {
      return;
    }
    this.tries.push(this.state.value);
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
        <div id='answer'></div>
        <form onSubmit={this.onSubmit}>
          <input type='text' value={this.state.value} onChange={this.onChange} ref={this.inputRef} />
          <button>입력</button>
        </form>
        <div>{this.state.message}</div>
        <div id='result-list'></div>
      </div>
    );
  }
}
//

/*/ 함수 컴포넌트
const NumberBaseball = () => {

  return (
    <div>
      <div></div>
    </div>
  );
};
/*/

export default NumberBaseball;
