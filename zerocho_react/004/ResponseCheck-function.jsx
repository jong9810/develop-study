import React, { useState /*, useRef, useMemo, useCallback, useEffect */ } from 'react';

let startTime;
let timeout;
const ResponseCheck = () => {
  const [state, setState] = useState('waiting');
  const [message, setMessage] = useState('클릭해서 시작하세요.');
  const [result, setResult] = useState([]);
  let endTime;

  const onReset = () => {
    setResult([]);
  };
  const renderAverage = () => {
    return (
      <>
        <div>{result.length === 0 ? null : `평균시간: ${result.reduce((a, c) => a + c) / result.length}ms`}</div>
        <div>
          <button onClick={onReset}>리셋</button>
        </div>
      </>
    );
  };
  const onClickScreen = () => {
    if (state === 'waiting') {
      setState('ready');
      setMessage('초록색이 되면 클릭하세요.');
      timeout = setTimeout(() => {
        startTime = new Date();
        // console.log(startTime);
        setState('go');
        setMessage('클릭하세요!');
      }, Math.ceil(Math.random() * 2000 + 1500));
      // console.log(timeout);
    } else if (state === 'ready') {
      clearTimeout(timeout);
      console.log(timeout);
      setState('waiting');
      setMessage('너무 성급했습니다..');
    } else if (state === 'go') {
      endTime = new Date();
      // console.log(startTime, endTime, endTime - startTime);
      // console.log(timeout);
      setState('waiting');
      setMessage('클릭해서 시작하세요.');
      setResult((prevResult) => {
        return [...prevResult, endTime - startTime];
      });
    }
  };

  return (
    <>
      <div id='screen' className={state} onClick={onClickScreen}>
        {message}
      </div>
      <div>{renderAverage()}</div>
    </>
  );
};

export default ResponseCheck;
