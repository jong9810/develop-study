import React, { useState, useRef, Component } from 'react';

function getNumbers() {
  const numbers = [];
  const candidate = Array(10)
    .fill(0)
    .map((el, idx) => el + idx);
  for (let i = 0; i < 4; i++) {
    const randIndex = Math.floor(Math.random() * candidate.length);
    numbers.push(candidate.splice(randIndex, 1)[0]);
  }
  console.log(numbers);
  return numbers;
}

// 클래스 컴포넌트
class NumberBaseball extends Component {
  state = {
    value: '',
    result: '',
    answer: getNumbers(),
    tries: [],
  };
  input;

  onSubmit = (e) => {
    e.preventDefault();
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
        <div></div>
        <form onSubmit={this.onSubmit}>
          <input maxLength={4} value={this.state.value} onChange={this.onChange} ref={this.inputRef} />
          <button>입력</button>
        </form>
        <div>시도 : {this.state.tries.length}</div>
        <ul>
          {this.state.tries.map((el) => {
            return (
              <li>
                {el} : {}스트라이크 {}볼
              </li>
            );
          })}
        </ul>
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
