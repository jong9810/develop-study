import React, { Component } from 'react';

class RSPGame extends Component {
  state = {
    result: '',
    score: 0,
    imgCoord: 0,
  };

  componentDidMount() {
    //
  }

  render() {
    const { result, score, imgCoord } = this.state;
    return (
      <>
        <div
          id='computer'
          style={{ background: `url(https://en.pimg.jp/023/182/267/1/23182267.jpg) ${imgCoord} 0` }}
        ></div>
        <div>
          <button id='rock' className='btn'>
            바위
          </button>
          <button id='scissor' className='btn'>
            가위
          </button>
          <button id='paper' className='btn'>
            보
          </button>
        </div>
        <div>점수 : {score}</div>
        <div>{result}</div>
      </>
    );
  }
}

export default RSPGame;
