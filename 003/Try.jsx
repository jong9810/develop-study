import React from 'react';

const Try = (props) => {
  return (
    <li>
      <b>{props.tryInfo.try}</b> - {props.tryInfo.result}
    </li>
  );
};

export default Try;
