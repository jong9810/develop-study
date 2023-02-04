// import React, { memo, useState } from 'react';
import React, { PureComponent } from 'react';

// 클래스 컴포넌트
class Try extends PureComponent {
  constructor(props) {
    super(props);
    // 다른 동작 가능
    const filtered = this.props.filter(() => {});
    this.state = {
      try: this.props.tryInfo.try,
      result: this.props.tryInfo.result,
    };
  }

  onClick = () => {
    this.setState({
      try: '****',
      result: '*S *B',
    });
  };

  render() {
    // const { tryInfo } = this.props;
    return (
      <li onClick={this.onClick}>
        <b>{this.state.try}</b> - {this.state.result}
      </li>
    );
  }
}
//

/*/ 함수 컴포넌트
// let { tryInfo } = props; // 구조분해 할당
const Try = memo(({ tryInfo }) => {
  // props 바꾸고 싶을 때
  // const [result, setResult] = useState(tryInfo.result);

  // const onClick = () => {
  //   setResult('1');
  // };

  return (
    <li>
      <b>{tryInfo.try}</b> - {tryInfo.result}
    </li>
  );
});
/*/

Try.displayName = 'Try';
export default Try;
