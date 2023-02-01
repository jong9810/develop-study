import React, { useState, useRef, Component } from 'react';
import Try from './Try';

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
function checkValue(value) {
  const valueArray = value.split('');
  let rightValue = true;
  if (new Set(valueArray).size !== 4) {
    rightValue = false;
  } else if (!Number(value)) {
    rightValue = false;
  }
  return rightValue;
}

// 클래스 컴포넌트
class NumberBaseball extends Component {
  /*/ 함수 선언문 사용하는 코드
  // 클래스 컴포넌트에 함수 선언문을 사용하려면 생성자 함수를 사용해야 한다.
  constructor(props) {
    super(props);
    this.state = {
      value: '',
      result: '',
      answer: getNumbers(),
      tries: [],
    };
    // 아래 코드를 작성해야지 함수 선언문으로 선언한 함수에서 this를 사용할 수 있다.
    this.onSubmit = this.onSubmit.bind(this);
    this.onChange = this.onChange.bind(this);
    this.inputRef = this.inputRef.bind(this);
  }
  input;

  onSubmit(e) {
    e.preventDefault();
  }
  onChange(e) {
    this.setState({ value: e.target.value });
  }
  inputRef(c) {
    this.input = c;
  }
  /*/

  // 화살표 함수 사용하는 코드
  state = {
    value: '',
    result: '',
    answer: getNumbers(),
    tries: [],
  };
  input;

  onSubmit = (e) => {
    e.preventDefault();
    if (!checkValue(this.state.value)) {
      this.setState({
        value: '',
        result: '잘못된 값입니다. 다시 입력해주세요.',
      });
      return;
    }
    if (this.state.value === this.state.answer.join('')) {
      // 정답을 맞췄다면
      this.setState({
        tries: [...this.state.tries, { try: this.state.value, result: '홈런!' }],
        result: '홈런!',
        value: '',
      });
    } else {
      // 정답을 못 맞췄다면
      let strike = 0;
      let ball = 0;
      if (this.state.tries.length >= 9) {
        // 시도한 횟수가 10번 째인 경우
        this.setState({
          result: `10번 넘게 틀려서 실패.. 답은 ${this.state.answer.join('')}였습니다.`,
        });
        alert('게임을 다시 시작합니다.');
        this.setState({
          value: '',
          answer: getNumbers(),
          tries: [],
        });
      } else {
        // 시도한 횟수가 10째보다 적은 경우
        this.state.answer.forEach((v, i) => {
          const index = this.state.value.indexOf(v);
          if (index !== -1) {
            if (index === i) {
              strike += 1;
            } else {
              ball += 1;
            }
          }
        });
        this.setState({
          tries: [...this.state.tries, { try: this.state.value, result: `${strike}S ${ball}B` }],
          value: '',
          result: `${strike}스트라이크 ${ball}볼입니다~`,
        });
      }
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
        <div>{this.state.result}</div>
        <form onSubmit={this.onSubmit}>
          <input maxLength={4} value={this.state.value} onChange={this.onChange} ref={this.inputRef} />
          <button>입력</button>
        </form>
        <div>시도 : {this.state.tries.length}</div>
        <ul>
          {this.state.tries.map((v) => {
            return <Try key={v.try + v.result} value={v} />;
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
