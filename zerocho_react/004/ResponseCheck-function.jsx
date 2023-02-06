import React, { useState, useRef /*, useMemo, useCallback, useEffect */ } from 'react';

const ResponseCheck = () => {
  const [state, setState] = useState('waiting');
  const [message, setMessage] = useState('클릭해서 시작하세요.');
  const [result, setResult] = useState([]);
  const timeout = useRef(null);
  const startTime = useRef();
  const endTime = useRef();

  const onReset = () => {
    setResult([]);
  };
  const renderAverage = () => {
    return (
      <>
        <div>{result.length === 0 ? null : `평균시간: ${result.reduce((a, c) => a + c) / result.length}ms`}</div>
        <div>{result.length === 0 ? null : <button onClick={onReset}>리셋</button>}</div>
      </>
    );
  };
  const onClickScreen = () => {
    if (state === 'waiting') {
      setState('ready');
      setMessage('초록색이 되면 클릭하세요.');
      timeout.current = setTimeout(() => {
        startTime.current = new Date();
        // console.log(startTime);
        setState('go');
        setMessage('클릭하세요!');
      }, Math.ceil(Math.random() * 2000 + 1500));
      // console.log(timeout);
    } else if (state === 'ready') {
      clearTimeout(timeout.current);
      // console.log(timeout);
      setState('waiting');
      setMessage('너무 성급했습니다..');
    } else if (state === 'go') {
      endTime.current = new Date();
      // console.log(startTime, endTime, endTime - startTime);
      // console.log(timeout);
      setState('waiting');
      setMessage('클릭해서 시작하세요.');
      setResult((prevResult) => {
        return [...prevResult, endTime.current - startTime.current];
      });
    }
  };

  return (
    <>
      <div id='screen' className={state} onClick={onClickScreen}>
        {message}
      </div>
      {renderAverage()}

      {/* renderAverage 함수를 return 안에 if문 사용하는 방법으로 구현하기 */}
      {/* {(() => {
        if (result.length === 0) {
          return null;
        }
        return (
          <>
            <div>평균시간: {result.reduce((a, c) => a + c) / result.length}ms</div>
            <div>
              <button
                onClick={() => {
                  setResult([]);
                }}
              >
                리셋
              </button>
            </div>
          </>
        );
      })()} */}
    </>
  );
};

export default ResponseCheck;
