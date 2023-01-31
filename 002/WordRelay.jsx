const React = require('react');
// const { Component } = React;
const { useState, useRef } = React;

const WordRelay = () => {
  const [word, setWord] = useState('제로초');
  const [value, setValue] = useState('');
  const [result, setResult] = useState('');
  const inputRef = useRef(null);

  const onSubmit = (e) => {
    e.preventDefault();
    if (word[word.length - 1] === value[0]) {
      setWord(value);
      setValue('');
      setResult('딩동댕');
    } else {
      setValue('');
      setResult('땡');
    }
    inpurRef.current.focus();
  };

  const onChange = (e) => {
    setValue(e.target.value);
  };

  return (
    <div>
      <div>제시어 : {word}</div>
      <form onSubmit={onSubmit}>
        <input type='text' value={value} onChange={onChange} ref={inputRef} />
        <button>입력</button>
      </form>
      <div>{result}</div>
    </div>
  );
};

module.exports = WordRelay;
