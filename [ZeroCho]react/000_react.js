//-------------------------------------------------------------------------------------------------------------------------------------------------

// 프로그래밍 팁
// 남의 코드를 가져올 때 <script> 태그에 src="코드 경로"로 불러온다.

// 코드를 가장 깔끔하게 짜는 방법은 안 짜는 것이다(프로그래밍계의 속담).
// ex) this(클래스 컴포넌트)가 어렵기 때문에 this를 안 쓰는 방법(함수 컴포넌트)이 나온 것이다.
// React로 만들지 않은 사이트도 React로 만들 수 있을 정도가 되려면 state 찾는 훈련을 해야한다.

// React 감 잡는 훈련!!!
// 아무 웹 사이트를 켜고 그 사이트에서 state가 무엇일지 찾아보기
// 더 나아가서 찾은 state가 어떤 데이터 타입일지 상상해보기
// ex) 로그인 state : boolean(true==로그인 함, false==로그인 안 함)

// CSS로 구현할 수 있으면 CSS로 하고, 그게 어려울 경우 state를 사용해서 구현하는 게 좋다.

// JSX랑 JavaScript는 웬만하면 섞어쓰지 말고 분리하는 것이 좋다.

//-------------------------------------------------------------------------------------------------------------------------------------------------

// REACT
// React 자체는 라이브러리이지만, React 생태계를 통틀어서는 프레임워크가 맞다.
// React는 state가 바뀌면 어딘가에 저장되어 있는 데이터가 화면에 보이게 한다.

// React 사용 목적
// React는 자바스크립트이다.
// React는 데이터 중심으로 움직인다.
// React의 주 목적은 데이터와 화면을 일치시키는 것이다(데이터 바뀜 -> 화면 자동으로 바뀜).

// React 주의사항
// React에서는 기존 html 태그는 소문자로, 사용자가 만든 컴포넌트는 대문자로 시작해야한다.
// React에서는 닫는 태그를 작성하지 않으면 에러가 발생한다.
// 문자열은 ""로, 자바스크립트 코드는 {}로 묶어주어야 한다.
// 반환하는 값이 여러 줄일 경우 소괄호로 묶어준다(return (값);).
// return에는 태그가 하나만 와야 한다(여러 개 태그 반환하는 경우에는 <></>(fragment)로 묶어주면 됨).
// react에서는 스테이트 객체를 함부로 바꾸지 말고 복사해서 수정해야 한다(불변성).
// ex)
// this.state.liked = true; // (X)
// this.setState({liked: true}); // (O) : 스테이트를 수정하는 방법(setState 메서드)

// pop, push, shift, unshift, splice -> 배열을 직접적으로 수정(불변성을 훼손시킴)
// splice, concat -> 새로운 배열을 만들어냄(불변성을 훼손시키지 않음)

// JavaScript : 화면이 어떻게 바뀔지 상상 -> 화면에 있는 데이터 자바스크립트로 바꿔주기
// React : 데이터가 어떻게 바뀔지 상상 -> 화면을 바꿔주기

// React 구성요소
// 컴포넌트(component) : 데이터와 화면을 하나로 묶어놓은 것(사용자 정의 태그).
// 스테이트(state) : 데이터(화면의 바뀔 부분을 스테이트로 만들어 주면 됨).
// 화면(screen) : render의 return 부분.

// React 방식(class, function)
// react를 사용하는 방법은 함수 방식과 클래스 방식으로 두 가지이다.
// 클래스 방식은 ErrorBoundary에서만 사용되고 거의 사용되지 않는다.

// 클래스 컴포넌트 : 코드를 읽을 수 있을 정도만 익히면 된다.

// 함수 컴포넌트
// React.useState() 메서드를 사용하여 스테이트를 정의하고 수정한다.
// ex) const [liked, setLiked] = React.useState(false);
// 함수 컴포넌트도 클래스 컴포넌트와 비슷하게 return 한 값이 화면에 표시됨.
// 함수 컴포넌트를 사용하면 this를 사용하지 않아도 돼서 헷갈릴 일이 없다(함수 컴포넌트 장점).
//

// ErrorBoundary : https://velog.io/@bbaa3218/React-%EC%97%90%EB%9F%AC-%EB%B0%94%EC%9A%B4%EB%8D%94%EB%A6%ACError-Boundary

// 코드
// ReactDOM.render(<LikeButton />, document.querySelector('#root')); // React 17버전 코드
// ReactDOM.createRoot(document.querySelector('#root')).render(<LikeButton />); // React 18버전 코드

// {this.state.liked ? 'Liked' : 'Like!'} // 조건문 대신 삼항 연산자를 사용
// {[1,2,3].map((i)=>{return <div>i<div>;})} // 반복문 대신 배열 map 메서드를 사용

// const [liked, setLiked] = React.useState(false); // 구조분해 할당 or 비구조화 할당
// 위 한줄 코드와 아래 두 줄 코드는 완전히 같은 코드이다(반드시 배열로 할당해야함).
// const liked = React.useState(false)[0]; // state
// const setLiked = React.useState(false)[1]; // state를 바꾸는 함수

// React Developer Tools
// 개발자 도구에 Components, Profiler 탭이 생김.
// Components 탭 : 컴포넌트와 스테이트를 확일할 때 사용
// Profiler 탭 : 성능 문제를 해결할 때 사용

//-------------------------------------------------------------------------------------------------------------------------------------------------

// JSX

// JavaScript + XML -> 자바스크립트이지만 형태는 html과 비슷한 문법

// 기존의 React.createElement() 대신에 html의 태그 문법으로 작성한다.

//-------------------------------------------------------------------------------------------------------------------------------------------------

// Babel 라이브러리

// JSX를 만나면 React.createElement()로 바꿔줌.

// babel 라이브러리 불러오기
// 1) <head> 태그에 아래 코드 추가.
// <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
// 2) babel 라이브러리 적용할 <script> 태그에 아래와 같이 type 속성 적용.
// <script type="text/babel">

//-------------------------------------------------------------------------------------------------------------------------------------------------

// 1. 구구단 게임

// <></> or React.Fragment : 빈 태그(fragment)로 태그들을 감싸주면 렌더링 할 때 쓸데없는 div 태그 하나가 생성되는 것을 막을 수 있다.
// () : 그룹 연산자, 연산의 우선순위를 바꿔줄 때도 사용하지만, 코드 가독성을 높일 때 사용하기도 한다(이 경우에는 생략 가능).

// 자바스크립트 기본 이벤트 핸들러 종류
// onClick, onChange, onSubmit, onLoad, onInput, onFocus, onBlur 등
// input 태그에서 변화가 있을 때, onChange나 onInput을 통해 콜백함수를 줄 수 있다.

// 이벤트 핸들러 팁
// <form> 태그로 감싸놓은 경우에는 <form> 태그에 onSubmit을 주고, <input과> <button> 태그만 사용하는 경우 <button> 태그에 onClick을 준다.

// input 태그 prop
// value="값" : input 태그의 초기값을 "값"으로 설정해준다.
// input; <input ref={(c) => {this.input = c;}}/> : 클래스 컴포넌트에서 DOM을 input 변수에 대입해주는 코드이다.

// 클래스 컴포넌트를 사용하는 경우 콜백함수를 따로 뺄 때, 화살표 함수로 만들어야 한다(클래스 안에 있는 화살표 함수에서 this 가 클래스를 가리킴).
// render() 안에 메서드가 정의되어 있는 경우, render()를 호출할 때마다 메서드가 생성되기 때문에 성능에 좋지 않을 수 있다.
// 클래스 컴포넌트는 this.setState(), 함수 컴포넌트는 useState()메서드를 사용해서 state를 관리할 수 있다.

// state가 바뀌면 화면이 바뀌므로, state 값은 다음 화면에 보여줄 데이터이다.
// setState() 안에 메서드를 정의해서 이전 state값을 현재 state값으로 사용할 수 있다.

// setState()는 비동기 코드이다.
// 따라서 동일한 setState()를 여러 번 작성하고 실행을 시켜도 여러 번 적용이 되지 않을 수도 있다.
// 그렇기 때문에 이전 state 값을 현재 state 값으로 사용할 때에는 setState() 안에 메서드로 정의해서 사용하는 것이 좋다(setState 안에 this.state가 들어갈 때).

// React를 사용하면 document는 거의 사용하지 않는다.
// React가 화면을 컨트롤 해주고, 개발자는 데이터만 변경해준다고 생각하면 된다.
// React에서 class 속성은 사용할 수 없고 대신 className 속성으로 사용해야 한다(javascript class와 헷갈리기 때문에 막힘).
// <label> 태그에 for 이라는 속성도 마찬가지 이유로 htmlFor로 대체해서 사용해야 한다.

// React Hook
// https://ko.reactjs.org/docs/hooks-overview.html
// Hook은 함수 컴포넌트에서 React state와 생명주기 기능(lifecycle features)을 “연동(hook into)“할 수 있게 해주는 함수이다.
// Hook은 class 안에서는 동작하지 않습니다. 대신 class 없이 React를 사용할 수 있게 해주는 것이다(function).
// (하지만 이미 짜놓은 컴포넌트를 모조리 재작성하는 것은 권장하지 않는다. 대신 새로 작성하는 컴포넌트부터는 Hook을 이용하면 된다.)

// React Hooks 종류
// 1) useState() : state를 선언하고 초기화할 때 사용.
// 2) userRef() : ref prop으로 DOM을 가져올 때 사용. 가져온 DOM을 사용할 때는 current를 붙여주어야 함. ex) inputRef.current.focus()
// 3) useEffect()

// Hook 사용시 단점
// 클래스 컴포넌트는 렌더링될 때 render() 부분만 새로 실행되기 때문에 메서드들이 다시 생성되지 않는다.
// 함수 컴포넌트는 렌더링될 때마다 컴포넌트 안의 함수(메서드)가 새로 생성되기 때문에 조금 더 느릴 수 있다(최적화 문제).
// Hooks 사용할 때 state 들을 하나의 객체로 저장할 경우, state를 바꿀 때 모든 state 들을 다시 적어주어야 해서 불편함.
// 따라서 Hooks 에서는 state 를 따로 선언해주어야 한다.

// 하나의 함수 안에서 여러 state가 바뀌어도 여러 번 렌더링 되지 않고 한 번만 렌더링된다.
// Hooks가 비동기이기 때문에 setState를 모아서 한 번에 처리하기 때문에 렌더링이 한 번만 일어난다.
// setState가 비동기이기 때문에 이전 데이터를 사용하지 못해서 setState 내에 함수를 넣어서 이 문제를 해결한다(카운터(counter) 예제에서 많이 쓰임).

// DOM(Document Object Model, 문서 객체 모델)
// DOM은 객체 지향 모델로써 구조화된 문서를 표현하는 방식이다.
// DOM은 HTML, XML 문서의 프로그래밍 interface 이다.
// DOM은 자바스크립트와는 독립적인 기술 표준이다.
// DOM은 HTML, CSS와 같은 W3C의 기술의 한 종류이다.
// DOM은 문서의 구조화된 표현(structured representation)이다.
// 쉽게 말하면 html 태그를 document.querySelector()로 선택한 것이 DOM이다.

//-------------------------------------------------------------------------------------------------------------------------------------------------
