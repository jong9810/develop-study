import React, { useState } from 'react';

const RSPGame = () => {
  const [word, setWord] = useState('hello world');

  return (
    <>
      <div>{word}</div>
    </>
  );
};

export default RSPGame;
