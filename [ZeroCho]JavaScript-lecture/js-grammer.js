// 문자열과 다른 자료형을 연산할 때, 더하기 연산은 다른 자료형을 문자형으로 형변환 시킨 후 더해준다.
// 더하기를 제외한 나머지 연산은 문자열을 다른 자료형으로 형변환 시킨 후 연산을 진행한다.
// 웬만하면 자료형을 똑같이 맞춰준 후에 연산을 하는 것이 예상하지 못한 결과가 나오는 것을 막는 방법이다.

// typeof '' : 인자로 전달해준 값의 자료형을 결과로 반환해주는 함수

// 비교 연산자
// ==, != : 연산자를 기준으로 왼쪽에 있는 값과 오른쪽에 있는 값만을 비교해서 결과를 boolean 자료형으로 반환해준다.
// ===, !== : 값과 자료형을 모두 비교해서 결과를 반환해준다.
// 웬만하면 ===, !==을 사용하는 것이 좋음. 예상치 못한 결과를 줄일 수 있다.

// !! or boolean('') : 값을 boolean 자료형으로 변환해주는 연산자나 함수이다.
// ''(빈 문자열), 0, false, NaN, undefined, null 값은 false로, 나머지는 모두 true로 변환된다.

// NaN : Not a Number의 약자로, 자료형은 number이지만 NaN은 숫자가 아닌 값을 의미한다.
// NaN == NaN 자기 자신을 비교했을 때 false값을 반환하는 유일한 값이다.

// 빈 자료형
// undefined : 반환할 값이 없을 때 반환되는 값, 기본값
// null : 의도적으로!! 빈 값을 대입할 때 많이 사용한다.
// undefined와 null은 값은 빈 값으로 같지만 자료형이 다르다.
// 협업할 때 변수에 undefined값이 있는 것과 null값이 있는 것은 완전히 다른 의미로 해석된다.
// undefined -> 아직 값이 할당이 안된 변수인지 의도적으로 값을 할당하지 않은 것인지 알 수 없다..
// null -> 동료가 의도적으로 값을 null로 바꿨다는 것을 확실히 알 수 있음!!

// typeof null의 실행 결과는 "object"로 잘 알려진 자바스크립트 언어의 버그이다. 
// 언어가 만들어진 초창기에 생긴 버그로 언어가 이미 많이 사용된 후에 발견되었기 때문에 고치지 않고 유지하고 있다.

// let number = 1;
// number; : number 변수에 저장된 값(1) 자체를 반환해줌
// console.log(number); : number 변수에 저장된 값(1)을 화면에 보여주고 undefined값을 반환해줌.(화면에 결과만 출력하고 값은 반환x)

// 자주 보이는 에러는 외워두는 것이 좋다.
// console.log()에서 console을 선언하지 않아도 에러가 발생하지 않는 이유?
// console은 브라우저가 이미 선언을 해주었기 때문에 사용 가능하다. 
// 브라우저가 console과 같이 기본적인 도구들을 제공해준다.
// window, document(웹페이지 조작), console 등등

// let : 같은 이름으로 변수가 다시 선언되는 것이 불가능하다.
// js에서 변수명은 한글, 한자, 숫자도 가능하고 특수문자 중에서는 $, _를 포함할 수 있다.
// 주의할 점은 변수명에 띄어쓰기는 불가하고, 숫자는 변수명의 첫 글자로 나오면 안된다.
// 변수명 웬만하면 영어와 특수문자로 짓는 것이 좋고, 최대한 자세하고 명확하게 그 값이 뭔지를 알 수 있도록 짓는 것이 중요하다!!

// let으로 변수를 선언할 때, 변수명으로 사용하면 안되는 이름이 몇 가지 있다. 에러가 뜨기 때문에 암기할 필요는 없다.
// 예약어 : 이미 의미와 용법이 지정되어 사용되는 단어로서, 프로그래머가 임의로 다른 목적이나 의미로 바꾸어 사용할 수 없는 단어.
// await, break, case, catch, class, const, continue, debugger, default, delete, do, else, enum, export, extends, 
// false, finally, for, function, if, import, in, instanceof, new, null, return, super, switch, this, throw, true, 
// try, typeof, var, void, while, with, yield
// 예약어이지만 변수명으로 사용 가능한 경우도 있고, 예약어가 아니지만 변수명으로 사용하지 못하는 예외적인 경우도 있다.
// ex) let은 예약어가 아니지만 변수명으로 사용하지 못한다. 

// 사소하지만 아주 중요한 차이 (아직까지는 와닿지 않는다.)
// 변수를 선언하고 초기화할 때는 undefined를 반환해주지만, 
// 이미 선언된 변수의 값을 변경할 때는 변경되는 값을 반환해준다.

// 상수, const
// const로 선언하면 값을 변경할 수 없다. (엄밀히 말하면 변경할 수 있는 경우도 있지만 일반적으로는 불가능하다.)
// const로 상수를 선언할 때는 초기화를 반드시 같이 해주어야 한다. (상수는 초기화할 때만 대입 연산자를 사용한다.)

// 변수, var : 옛날에 많이 사용했지만, 최근에는 var 대신 let을 사용한다.
// var은 기본적으로 let과 동일하지만, let과 다르게 같은 변수 이름으로 재선언할 수 있다.
// var로 변수를 선언할 때는 undefined, Infinity, let도 변수명으로 사용할 수 있다.(버그가 많이 발생해서 잘 안씀.)

// js는 기본적으로 코드를 위에서 아래로, 왼쪽에서 오른쪽으로 읽고 실행한다.
// 예외적으로 대입연산자(=)는 오른쪽을 먼저 연산하고 왼쪽에 대입해준다.

// 조건문 
// if-else if-else 구문
// switch-case 구문 : if문을 좀더 편하게 만든 구문
/*  switch(조건식){
    case 비교조건식:
      동작문1;
      동작문2;
  }*/
// switch-case구문은 조건에 만족할 경우 그 밑의 코드까지 다 실행이 된다.
// 따라서 break;를 넣어서 switch문을 탈출하게 하는 방법도 많이 사용된다.
// default: if구문의 else와 동일한 역할을 한다.

// 조건문 안에 조건문을 중첩하는 경우, 들여쓰기가 너무 깊이 돼서 코드의 가독성을 떨어뜨릴 수 있다.
// 이 문제의 해결방안은 조건문의 중첩을 줄이기 위해 조건식을 더 논리적으로 짜는 방법이 있다.

// 조건부 연산자(삼항 연산자)
// (조건식) ? (true일 경우 동작문) : (false일 경우 동작문)
// 조건식에 따라 동작문을 실행하여 결과값을 반환해준다. 
// 조건부 연산자를 사용하면 코드의 길이를 줄일 수 있지만, 코드의 가독성을 떨어뜨리는 경향이 있다.
// 조건부 연산자를 중첩할 때, 중첩된 부분을 소괄호로 묶어주거나 줄을 바꾸고 들여쓰기를 하면 코드의 가독성을 높일 수 있다.

// 반복문

