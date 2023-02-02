import React, { Component, PureComponent, createRef } from 'react';
// import React, { useState, useRef, memo } from 'react';

class Reaction extends Component {
  state = {
    text: 'Hello, reaction',
  };

  render() {
    return (
      <>
        <div>{this.state.text}</div>
      </>
    );
  }
}

export default Reaction;
