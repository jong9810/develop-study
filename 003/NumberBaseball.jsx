const React = require('react');
const { useState, useRef } = React;

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

module.exports = NumberBaseball;
