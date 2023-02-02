import React, { memo, PureComponent } from 'react';

/*/ 클랫 컴포넌트
class Try extends PureComponent {
  render() {
    const { tryInfo } = this.props;
    return (
      <li>
        <b>{tryInfo.try}</b> - {tryInfo.result}
      </li>
    );
  }
}
/*/

// 함수 컴포넌트
// let { tryInfo } = props; // 구조분해 할당
const Try = memo(({ tryInfo }) => {
  return (
    <li>
      <b>{tryInfo.try}</b> - {tryInfo.result}
    </li>
  );
});
//

Try.displayName = 'Try';
export default Try;
