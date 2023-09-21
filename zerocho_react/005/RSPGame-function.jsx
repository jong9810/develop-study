import React, { useState, useRef, useEffect } from 'react';
import useInterval from './useInterval';

const rspCoords = {
  바위: '0',
  가위: '-142px',
  보: '-284px',
};

const scores = {
  가위: 1,
  바위: 0,
  보: -1,
};

const computerChoice = (imgCoord) => {
  return Object.entries(rspCoords).find((v) => v[1] === imgCoord)[0];
};

const RSPGame = () => {
  const [result, setResult] = useState('');
  const [imgCoord, setImgCoord] = useState(rspCoords.바위);
  const [score, setScore] = useState(0);
  const [isRunning, setIsRunning] = useState(true);
  /*
  const interval = useRef();

  // useEffect는 여러 번 사용이 가능하다.
  // useEffect의 첫 번째 파라미터는 화면이 렌더링 될 때 실행할 함수를 정의한다.
  // useEffect의 첫 번째 파라미터 함수의 return 값으로 정의한 함수는 컴포넌트가 사라질 때 실행된다.
  // useEffect의 두 번째 파라미터는 배열로 상태를 넣는다(상태 값이 변하면 첫번쨰 파라미터 함수를 실행).
  useEffect(() => {
    // componentDidMount, componentDidUpdate 역할(1대1 대응은 아님)
    interval.current = setInterval(changeHand, 100);
    return () => {
      // componentWillUnmount 역할
      clearInterval(interval.current);
    };
  }, [imgCoord]);
  */

  const changeHand = () => {
    if (imgCoord === rspCoords.바위) {
      setImgCoord(rspCoords.가위);
    } else if (imgCoord === rspCoords.가위) {
      setImgCoord(rspCoords.보);
    } else if (imgCoord === rspCoords.보) {
      setImgCoord(rspCoords.바위);
    }
  };

  const onClickBtn = (choice) => () => {
    if (isRunning) {
      setIsRunning(false);
      const myScore = scores[choice];
      const cpuScore = scores[computerChoice(imgCoord)];
      const diff = myScore - cpuScore;
      if (diff === 0) {
        setResult('비겼습니다.');
      } else if ([-1, 2].includes(diff)) {
        setResult('이겼습니다.');
        setScore((prevScore) => prevScore + 1);
      } else if ([-2, 1].includes(diff)) {
        setResult('졌습니다.');
        setScore((prevScore) => prevScore - 1);
      }
      setTimeout(() => {
        setIsRunning(true);
      }, 1000);
    }
  };

  useInterval(changeHand, isRunning ? 100 : null);

  return (
    <>
      <div
        id='computer'
        style={{ background: `url(https://en.pimg.jp/023/182/267/1/23182267.jpg) ${imgCoord} 0` }}
      ></div>
      <div>
        <button id='rock' className='btn' onClick={onClickBtn('바위')}>
          바위
        </button>
        <button id='scissor' className='btn' onClick={onClickBtn('가위')}>
          가위
        </button>
        <button id='paper' className='btn' onClick={onClickBtn('보')}>
          보
        </button>
      </div>
      <div>점수 : {score}</div>
      <div>{result}</div>
    </>
  );
};

export default RSPGame;
