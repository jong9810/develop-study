import React, { Component, PureComponent, createRef } from 'react';

class ResponseCheck extends Component {
  state = {
    state: 'waiting',
    message: '클릭해서 시작하세요.',
    result: [],
  };
  timeout;
  startTime;
  endTime;

  onReset = () => {
    this.setState({ result: [] });
  };
  renderAverage = () => {
    const { result } = this.state;
    return (
      <>
        <div>평균시간: {result.length === 0 ? null : result.reduce((a, c) => a + c) / result.length}ms</div>
        <div>
          <button onClick={this.onReset}>리셋</button>
        </div>
      </>
    );
  };
  onClickScreen = () => {
    const { state } = this.state;
    if (state === 'waiting') {
      this.setState({
        state: 'ready',
        message: '초록 화면이 될 때까지 대기.',
      });
      this.timeout = setTimeout(() => {
        this.startTime = new Date();
        this.setState({
          state: 'go',
          message: '클릭하세요!',
        });
      }, Math.floor(Math.random() * 2000 + 1000));
    } else if (state === 'ready') {
      this.setState({
        state: 'waiting',
        message: '너무 성급했습니다..',
      });
      clearTimeout(this.timeout);
    } else if (state === 'go') {
      this.endTime = new Date();
      this.setState((prevState) => {
        return {
          state: 'waiting',
          message: '클릭해서 시작하세요.',
          result: [...prevState.result, this.endTime - this.startTime],
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

export default ResponseCheck;
