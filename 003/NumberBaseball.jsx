import React, { useState, useRef, Component } from 'react';

const NumberBaseball = () => {
  const [text, setText] = useState('Hello, number baseball');

  return (
    <div>
      {text}
      <br />
      {text}
    </div>
  );
};

export default NumberBaseball;
