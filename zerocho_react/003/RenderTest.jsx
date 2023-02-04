import React, { PureComponent } from 'react';

class Test extends PureComponent {
  state = {
    counter: 0,
    string: 'hello',
    number: 1,
    boolean: true,
    object: {},
    array: [],
  };

  // shouldComponentUpdate(nextProps, nextState, nextContext) {
  //   if (this.state.counter !== nextState.counter) {
  //     return true;
  //   }
  //   return false;
  // }

  onClick = () => {
    // 잘못된 예시
    // const array = this.state.array;
    // array.push(1);
    // this.setState({
    //   array: array,
    // });
    // console.log(array); // this.state.array는 바뀌지만 렌더링은 되지 않음(PureComponent가 바뀐 것을 감지하지 못함)

    this.setState({
      array: [...this.state.array, 1],
    });
    console.log(array);
  };

  render() {
    console.log('렌더링', this.state);
    return (
      <div>
        <button onClick={this.onClick}>클릭</button>
        <div>{this.state.array}</div>
      </div>
    );
  }
}

export default Test;
