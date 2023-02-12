import React, { Component } from 'react';

class RSPGame extends Component {
  state = {
    word: 'Hello world',
  };

  render() {
    return (
      <>
        <div>{this.state.word}</div>
      </>
    );
  }
}

export default RSPGame;
