import React from 'react';

// let { tryInfo } = props; // 구조분해 할당
const Try = ({ tryInfo }) => {
  return (
    <li>
      <b>{tryInfo.try}</b> - {tryInfo.result}
    </li>
  );
};

export default Try;
