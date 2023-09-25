import React, { useState, useRef, useEffect } from 'react';
import Ball from './Ball';

const Lotto = () => {
  //
  return (
    <>
      <div>당첨 숫자</div>
      <div id='결과창'>
        {winBalls.map((v) => (
          <Ball key={v} number={v} />
        ))}
      </div>
      <div>보너스!</div>
      {bonus && <Ball number={bonus} />}
      <button onclick={redo ? onClickRedo : () => {}}>한번 더!</button>
    </>
  );
};

export default Lotto;
