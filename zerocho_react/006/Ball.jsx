import React, { PureComponent, memo } from 'react';

// 함수형 컴포넌트
const Ball = memo(({ number }) => {
  let background;
  if (number <= 10) {
    background = 'red';
  } else if (number <= 20) {
    background = 'orange';
  } else if (number <= 30) {
    background = 'yellow';
  } else if (number <= 40) {
    background = 'green';
  } else {
    background = 'blue';
  }
  return (
    <div className='ball' style={{ background }}>
      {number}
    </div>
  );
});

/*/ 클래스형 컴포넌트
class Ball extends PureComponent {
  render() {
    const { number } = this.props;
    let background;
    if (number <= 10) {
      background = 'red';
    } else if (number <= 20) {
      background = 'orange';
    } else if (number <= 30) {
      background = 'yellow';
    } else if (number <= 40) {
      background = 'green';
    } else {
      background = 'blue';
    }
    return (
      <div className='ball' style={{ background }}>
        {number}
      </div>
    );
  }
}
/*/

export default Ball;
