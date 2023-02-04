import React, { Component, PureComponent, createRef } from 'react';
// import React, { useState, useRef, memo } from 'react';

// 클래스 컴포넌트
class ResponseCheck extends Component {
  state = {
    state: 'waiting',
    message: '클릭해서 시작하세요.',
    result: [],
  };

  renderAverage = () => {
    const { result } = this.state;
    return result.length === 0 ? null : <div>평균 시간: {result.reduce((a, c) => a + c) / result.length}ms</div>;
  };
  startTime;

  onClickScreen = () => {
    const { state } = this.state;
    let responseTime;
    if (state === 'waiting') {
      this.setState({
        state: 'ready',
        message: '초록 화면이 될 때까지 대기.',
      });
      let timeoutId = setTimeout(() => {
        this.startTime = new Date();
        this.setState({
          state: 'go',
          message: '클릭하세요!',
        });
      }, Math.floor(Math.random() * 2000 + 1000));
    } else if (state === 'ready') {
      this.setState({
        state: 'waiting',
        message: '클릭해서 시작하세요.',
      });
    } else if (state === 'go') {
      responseTime = new Date() - this.startTime;
      this.setState((prevState) => {
        return {
          state: 'waiting',
          message: '클릭해서 시작하세요.',
          result: [...prevState.result, responseTime],
        };
      });
    }
  };

  render() {
    const { state, message } = this.state;
    return (
      <>
        <div id='screen' className={state} onClick={this.onClickScreen}>
          {message}
        </div>
        {this.renderAverage()}
      </>
    );
  }
}
//

/*/ 함수 컴포넌트
const ResponseCheck = () => {
  //

  return (
    <>
      <div></div>
    </>
  );
};
/*/

export default ResponseCheck;
