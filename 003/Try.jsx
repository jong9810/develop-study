import React, { Component } from 'react';

class Try extends Component {
  render() {
    return (
      <li>
        <b>{this.props.value.try}</b> - {this.props.value.result}
      </li>
    );
  }
}

export default Try;
