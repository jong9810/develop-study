const React = require('react');
const { useState, useRef } = React;

const WordRelay = () => {
  const [word, setWord] = useState('제로초');

  return <div>{word}</div>;
};

module.exports = WordRelay;
