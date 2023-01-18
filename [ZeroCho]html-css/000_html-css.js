// 웹(Web) : 1개 이상의 사이트가 연결되어 있는 인터넷 서비스의 한가지 형태(World Wide Web의 줄임말)
// 웹은 전 세계의 컴퓨터를 연결하여 정보 공유를 하는 데에 주 목적이 있음. 연결된 상태를 네트워크에 연결되어 있다고 표현함.
// 인터넷 : 1개 이상의 네트워크가 연결되어 있는 형태
// 프로토콜(Protocol) : 네트워크 상에서 약속한 통신 규약(HTTP, FTP, SMTP, POP, DHCP)
// IP : 네트워크 상에서 컴퓨터를 식별할 수 있는 주소
// DNS : IP 주소를 인간이 쉽게 외우도록 맵핑한 문자열
// Port : 해당 컴퓨터의 구동되고 있는 프로그램을 구분할 수 있도록 부여한 번호
// HTML(HyperText Markup Language), CSS(Cascading Style Sheets)
// HyperText : 하이퍼링크를 통해 어떤 문서에서 다른 문서로 접근할 수 있는 텍스트
// Markup : (콘텐츠를) 표시하다
// Language : 언어
// 렌더링 : HTML 코드가 웹브라우저를 통해 해석되고 표현되는 과정

// 제로초 HTML-CSS 강의

// 1. 웹 개발 꿀팁
// 개발자도구에서 Element 탭에서는 웹의 html과 css를 작성하고, Console 탭에서는 javascript를 작성한다.
// Element 탭에서 ctrl + F를 하면 검색할 수 있다.
// Sources 탭에는 웹에 사용된 이미지, 폰트, 코드를 다 볼 수 있다.
// 개발자 도구를 키고 ctrl + shift + C 를 누른 후 웹 페이지에서 궁금한 곳을 누르면 해당하는 태그로 바로 이동해준다.
// 프론트 엔드에서 작업하는 내용은 모두에게 공개되기 때문에 보안에 위험한 내용은 쓰면 안된다.
// './' : 현재 폴더를 의미한다.
// 웹 표준을 완벽하게 지키진 않더라도 최대한 웹 표준에 가깝게 만드는 것이 좋다.
// 구역 안에서 가운데 정렬될 부분은 가로, 세로에 상관없이 div로 먼저 묶어주자(div 태그 자체를 가운데 정렬 시키기 위해).

// html 배치가 뒤죽박죽 되어있으면 나중에 수정하거나 에러가 어디서 났는지 찾을 때(유지보수) 엄청 고생한다.
// 따라서 html 구조를 먼저 잘 잡아놓고, 레이아웃 잡는 것을 심혈을 기울여서 해야한다.
// 세부적인 디자인은 그 이후에 하면 된다.

// 레이아웃 잡는 과정
// 웹의 구역을 크게 가로로 먼저 나누어서 생각해보면 레이아웃을 잘 잡을 수 있다.
// 그 다음에 크게 나눈 구역에서 또 가로로 구역을 나누고,
// 가로로 나눌 구역이 더이상 없다면 세로로 구역을 나누어서 태그를 배치한다.

// UI/UX 개발자들은 버튼 클릭시 마우스 모양이 화살표인지 손가락인지로도 논쟁한다(마우스 모양에 따라 클릭률이 달라짐).
// 웹 개발자들은 웹 사용자 편의를 위해 탭처리, 음성지원 등도 신경써야 한다.

// headings map : 웹의 머릿말 태그에 따라 트리 구조로 보여줌(크롬 확장프로그램).
// validity 확장 플러그인 : html5 웹 표준에 맞게 html문서를 작성했는지 검사해줌.
//

// 2. HTML 태그 설명

// 태그 안에는 속성을 줄 수 있는데 attributes라고도 함.
// html은 빈 태그인 경우 닫는 태그를 넣지 않아도 작동은 되지만, 그래도 작성하도록 하자.

// 3. HTML 태그 종류

// head : 문서의 부가적인 내용을 담고 있다.
// 파비콘(favicon) : 인터넷 웹 브라우저의 주소창에 표시되는 웹사이트나 웹페이지를 대표하는 아이콘

// meta : 문서에 대한 정보를 담고 있다.

// link : 다른 파일을 불러올 때 사용하는 태그
// rel 속성으로 어떤 파일을 불러오는지 나타내고, href 속성으로 불러올 파일의 경로, 파일이름을 주면된다.

// body : 화면을 그려주는 태그들을 담고 있다.
// 웹에서 즐겨찾기 아래부터 스크롤바까지가 화면을 나타낸다.

// heading 태그들 : 머릿말 태그. h1, h2, h3, h4, h5, h6가 있고, 숫자가 작을 수록 글자 크기가 크다(중요도 표시 역할).

// fieldset : 테두리를 만들어주는 태그.

// legend : 범례 태그.

// input : 기본값(type="text")은 텍스트를 입력받는 창을 만들어준다.
// type 속성에 따라 역할이 바뀌는데, checkbox, radio 등이 있다.
// type이 "checkbox" 이면, 여러 개를 선택할 수 있지만 "radio"인 경우 name 속성에 같은 범주로 묶여있는 경우 한 가지만 선택 가능하다.

// textarea : input 태그와 비슷하게 텍스트를 입력받지만, 긴 텍스트를 입력받아야 할 때 사용한다. 창을 늘리고 줄일 수 있다.

// ul : unordered list. 순서가 없는 목록을 만들어서 나열해준다.
// ol : ordered list. 순서가 있는 목록을 만들어서 나열해준다.
// li : list item. ul이나 ol의 자식 태그로 넣어주면 목록에 하나의 원소가 됨.

// img : src 속성에 이미지 파일의 경로와 파일이름을 넣으면 이미지를 화면에 표시해준다.
// alt 속성에 넣은 내용은 화면에는 나타나지 않지만 이미지에 대한 설명을 적어줄 수 있다.

// i : i 태그로 감싸면 텍스트를 기울어진 스타일로 만들어줌(italic)

// b : b 태그로 감싸면 텍스트를 굵게 표시해줌(bold)
// i 태그나 b 태그 같은 태그는 디자인적인 요소인데 css가 아니라 html이 담당해도 되는지에 대한 논쟁이 있다고 함.

// span : 텍스트 단위로 공간을 분할해줌.
// span 태그의 기본 css display 속성의 속성값은 inline이다.

// div : 행 단위로 공간을 분할해줌(division). 레이아웃을 잡을 때 구역을 나누는 태그로 아주 많이 사용된다.
// div 태그의 기본 css display 속성의 속성값은 block이다.
// div가 너무 많이 사용되다 보니, div 태그를 구분하기 위해 예전에는 id 속성에 head, main, footer, nav 등을 주고 사용하였다.
// html5가 되면서 어떤 구역인지 나타내주는 header, nav, article, main, footer 등 태그를 만들어주었다(시멘틱 구조태그).

// 시멘틱 구조 태그 종류(Semantic tag)
// header : 페이지나 콘텐츠의 머리말 부분에 사용하는 div 태그
// footer : 웹 페이지의 가장 아래 쪽에 위치하는 웹에 대한 부가적인 정보를 보여주는 꼬리말 부분에 사용하는 div 태그
// main : 웹 페이지의 주 내용이 작성되는 부분에 사용하는 div 태그(딱 한번만 사용해야 함 <- 웹 표준)
// nav : 네비게이션 바 영역에 사용하는 div 태그
// section : article보다 큰 영역을 나타낼 때 사용하는 div 태그
// article : 보통 제목 태그와 문단 태그를 포함하며 여러 문단을 묶어주고, 개별 콘텐츠에 사용하는 div 태그
// aside : 콘텐츠나 페이지의 사이드 영역에 사용하는 div 태그(퀵 메뉴, 스크롤탑버튼 등)
// details : 사용자가 보거나 숨길 수 있는 세부 콘텐츠를 정의할 때 사용하는 div 태그
// summary : <details> 태그를 통해 보이는 콘텐츠를 담는 div 태그
// figure : 일러스트레이션, 다이어그램, 사진, 코드 목록 등 자체 포함된 콘텐츠들을 담을 때 사용하는 div 태그
// figcaption : <figure> 태그로 정의한 콘텐츠들의 제목, 설명 등을 작성하는 div 태그

// button : 버튼을 생성해주는 태그

// form : input 태그와 button 태그를 넣고 묶어주면 버튼을 누를 때마다 submit 이벤트를 발생시킴.

// iframe :

// table :

// 4. CSS 설명

// Cascading Style Sheet : 조상 태그의 CSS 속성이 기본적으로 있고 자식 태그의 CSS가 그것을 덮어씌운다.
// 태그에 아무런 CSS 속성을 주지 않으면 조상 태그의 CSS 속성을 그대로 받는다.
// 자식 태그에 조상 태그에 적용된 CSS 속성을 다른 속성값으로 정의해주면 조상 태그의 속성값이 아닌 자식 태그에 정의된 속성값을 따름.
// ex)
// html{color: red;}
// div{color: blue;}
// -> html 내의 다른 태그들의 글자색은 빨강, div 태그의 글자 색은 파랑

// 기본적인 구조는 타겟{속성명: 속성값;} 이다.
// 개발자 도구에 user agent stylesheet라고 나타나는 것은 기본적으로 브라우저가 태그에 적용해주는 CSS 속성이다(브라우저마다 다를 수 있음).
// 크로스 브라우징 : 브라우저마다 다른 특징을 알고 잘 처리하는 웹 프로그래밍 기술.

// css 속성 적용 방법
// (1) 인라인 스타일시트(Inline Style Sheet) : html 태그 내에 style속성으로 정의하는 방식(권장하지 않음).
// (2) 인터널 스타일시트(Internal Style Sheet) : HTML 문서 안에 <style> 태그 안에 CSS 코드를 작성하는 방식.
// (3) 링킹 스타일시트(Linking Style Sheet) : 별도의 CSS 파일을 만들고 HTML link태그로 연결하는 방식.
// 하나의 파일에 모든 것을 담기 보다 역할에 따라 파일을 나누어 주는 것이 나중에 유지보수할 때 좋음.

// 선택자(selector)
// (1) # : id 선택자
// (2) . : class 선택자
// (3) nav li:first-child -> nav 태그 안에 있는 li 중에서 첫 번째 것을 선택해줌.
// (4) nav li:nth-child(2) -> nav 태그 안에 있는 li 중에서 두 번째 것을 선택해줌.
// 실무에서는 nth-child는 최대한 사용하지 않는 것이 좋다(nth child를 쓰면 목적이 분명히 드러나지 않음).
// nth child는 수정을 할 때도 뭐가 뭔지 알아보기 힘들고 순서에 의존하기 때문에 하나를 수정하기 위해 여러 태그를 수정해야 할 수도 있다(유지보수 안 좋음).
// 차라리 클래스 이름을 하나 더 주어서 클래스마다 다르게 css를 적용하는 것이 더 좋을 수도 있다.

// css도 다른 프로그래밍 언어와 마찬가지로 중복되는 것이 세 번 이상 반복되면 중복되는 것을 묶어서 빼주면 좋다.
//

// 시멘틱 구조태그는 css 적용할 때 생략이 불가능하다.(div 태그만 생략가능)
// 자식 태그를 선택하고 싶을 때는 태그 > 태그{스타일} 처럼 꺾쇠를 붙여주면 된다.
// 자손 태그를 선택할 때는 태그 태그{스타일} 처럼 한 칸 띄어주면 된다.

// margin이 있는 부분은 기본적으로는 다른 컨텐츠가 오지 못한다.
// 하지만, 특별한 방법을 사용하면 margin 위에 컨텐츠를 위치시킬 수 있다(position).

// class 속성은 같은 이름을 여러 번 사용해도 되지만, id 속성은 한 id는 한 번만 사용해야 나중에 문제가 생기지 않는다.
// 하나의 태그에 여러 개의 class를 적용할 수도 있다. ex) <div class="blind invisible"> -> blind와 invisible 클래스

// 실제 웹 서비스를 만들 때는 서버에 이미지를 여러 번 요청하면 성능이 떨어질 수 있기 때문에,
// 관련있는 이미지들을 모아놓은 이미지 스프라이트를 서버에 요청하게끔 한다.

// css 리셋 : 브라우저마다 태그의 기본 css가 다르기 때문에 프론트 엔드를 할 때에는 기본 css를 제거해주는 것이 좋다.
// css reset이나 reset.css 파일을 사용하면 기본 css를 없앨 수 있다.

// 같은 형제 태그가 정렬이 맞지 않는 경우는 높이가 다른 경우가 대부분이다.
// vertical-align, horizontal-align 속성으로 정렬 맞춤.

// 컨테이닝 블록의 모든 것
// 컨테이닝 블록 : 태그를 감싸고 있는 태그, 요소의 위치와 크기를 지정하는 데 사용하는 블록을 의미한다.
// https://developer.mozilla.org/ko/docs/Web/CSS/Containing_block
// 컨테이닝 블럭을 찾으면 요소가 어디에 위치할 지를 예측하기 쉬워진다(연습이 필요함).
// position 속성값이 static이나 relative이면 부모 태그가 display:inline만 아니면 컨테이닝 블록이 된다.

// 블록 서식 문맥(Block Format Context)
// : 블록들 간의 레이아웃이 어떻게 배치되는지에 대한 내용
// : 부모 태그 입장에서 자식 태그를 감쌀 수 있는가에 대한 내용!
// 감쌀 수 있는 경우 : block format context이다.
// 감쌀 수 없는 경우 : block format context가 아니다.

// 블록 서식 문맥이 생성되는 조건
// (1) float 속성이 none이 아닌 요소
// (2) 절대위치로 지정된 요소(position 속성이 absolute나 fixed인 요소)
// (3) display 속성이 inline-block 인 요소
// (4) overflow 속성이 visible이 아닌 요소

// 쌓임 맥락(Stacking Context)
// 화면에 태그들이 어떻게 위치하는지, CSS로 어떻게 위치를 바꿀 수 있는지에 대한 내용

// 뷰 포트 : 쉽게 생각하면 화면.

// 정렬이나 요소 배치할 때, 여러가지 방법이 있지만 원리만 이해하고 자기가 많이 사용하는 한 가지 방법만 사용해도 좋다.

// 만약 같은 클래스의 태그 중 첫 번째 태그만 margin 속성을 주고 싶지 않으면,
// 클래스 안에 margin 속성을 주고, 첫 번째 태그에만 margin:0; 을 주는 방법도 있다.

// css 우선순위
// 기본적으로 뒤에 나오는 css가 우선순위가 더 높다.
// !important > inline style attribute(속성) > id > 다른 attribute와 class > tag element 순으로 우선순위가 높다.
// 우선순위가 같다면 (메달의)개수가 많은 css가 우선순위가 더 높다.
// tag : 동메달, class : 은메달, id : 금메달로 생각하면 쉽다(의사 클래스도 클래스이므로 은메달로 침).
// 만약 우선순위가 같은데 앞에 나오는 css를 적용하고 싶다면 앞의 css에 똑같은 이름의 클래스를 하나 더 붙이면 우선순위가 더 높아진다.
// !important는 정말 마지막까지 고민해도 방법을 모르겠는 경우에 최후의 수단으로 붙이는 것이 좋다(되도록 사용 X).

// 의사 클래스(or 수도 클래스) : 선택자에 추가하는 키워드로, 선택한 요소가 특별한 상태여야 만족할 수 있다.
// ex) ul li:first-child -> ul 태그의 li 중 첫 번째 li를 가리킴(first-child가 의사 클래스임).

// 5. CSS 속성 종류

// width : 태그의 폭을 설정해주는 속성
// height : 태그의 높이를 설정해주는 속성

// display : 태그가 차지하는 공간을 설정하거나 보이지 않게 설정할 수 있다.

// display 속성값 종류
// none : 태그가 보이지 않고, 스크린 리더(시각장애인용)에도 인식되지 않음.
// block : 태그가 공간의 전체 너비를 다 차지하게 만든다(width가 있든 없든 공간은 다 차지함).
// inline-block : 기본적으로 컨텐츠가 차지하는 공간만큼, width, height 속성을 정의한 경우 width, height만큼의 공간을 차지하게 만든다.
// inline : 컨텐츠가 차지하는 공간(텍스트, 이미지 등)만큼만 차지하게 만든다(빈 공간 마련 불가능).
// flex :
// span은 기본적으로 display가 inline이고, inline인 경우 컨텐츠가 없으면 공간을 차지하게 만드는 것이 불가능하다.
// 따라서 컨텐츠가 없지만, 공간은 차지하고 싶으면 inline-block을 display의 속성값으로 주면 된다.

// background-image: url(이미지 경로) ->  이미지를 배경으로 설정한다.
// background-position: 3px 10px -> 오른쪽으로 3px, 위쪽으로 10px만큼 이미지 좌표를 이동한다.(-이면 각각 왼쪽, 아래쪽으로 이동)
// background-repeat: no-repeat -> 배경이 반복해서 표시되는 것을 막아줌.

// text-indent: 10% -> 글자 들여쓰기를 지정해주는 속성.

// overflow:visible(기본값) -> 자식 태그가 부모 태그보다 클 때, 자식 태그를 그대로 보여준다.
// overflow:hidden -> 자식 태그가 부모 태그보다 클 때, 자식 태그의 넘치는 부분을 잘라준다(많이 쓰임).
// overflow:auto -> 자식 태그가 부모 태그보다 클 때, 스크롤 바를 만들어 준다.

// text-decoration:none -> a 태그에 text-decoration:none을 주면 밑줄을 없앨 수 있다.

// box-sizing: content-box(기본값) -> width, height를 content의 사이즈로 설정한다.
// box-sizing: border-box -> width, height를 border의 사이즈로 설정한다.

// border:none -> 테두리를 삭제한다.
// outline: none -> input 태그의 입력창에 커서가 있을 때 테두리가 생기는 것을 없애줌.
// padding: 0; -> 상하좌우 패딩을 모두 없앤다.
// margin: 0; -> 상하좌우 마진을 모두 없앤다.

// 세로 정렬
// (1) vertical-align
// vertical-align:middle -> 형제 태그의 수직 방향 정렬을 맞출 때 사용(형제 태그에 같은 속성값을 주면 됨).
// vertical-align 속성은 다른 태그를 기준으로 세로 정렬을 하기 때문에 옆에 다른 태그가 있을 때만 정렬이 된다.

// (2) align-items
// display:flex; align-items:center; -> IE에서는 display:flex;를 사용하지 못한다는 단점이 있다.

// (3) 헬퍼를 사용하는 방법
// 헬퍼 태그 : vertical-align 속성을 사용하기 위해 생성하는 width가 0이고 height: 100%인 보이지 않는 div 태그
// 헬퍼 태그를 생성한 후에 세로 정렬할 태그들에 display:inline-block; vertical-align:middle; 을 주면 세로 정렬이 된다.
// 단점은 margin:0; padding:0을 주어도 display:inline-block이기 때문에 약간의 간격이 생긴다는 것이다.

// (4) 테이블 효과를 사용하는 방법
// 컨테이닝 블록에 display:table; 을 주고, 정렬할 태그에 display: table-cell; vertical-align:middle; 을 주면
// 태그가 테이블처럼 되어서 손쉽게 세로 가운데 정렬을 할 수 있다.
// 단점은 table-cell들의 width와 height를 조절하기가 어려워진다는 것이다.

// (5) transform 속성을 이용하는 방법
// 세로 정렬할 태그에 position:relative; top:50%;를 하면
// 컨테이닝 블록의 절반 높이에 태그가 위치하는데 태그 크기의 절반만큼 더 내려가는데,
// 여기서  transform:transformY(-50%);를 하면 태그 크기의 절반만큼 위로 올려주어서 세로 가운데 정렬이 된다.

// (6) float
//

// 오른쪽 정렬
// (1) text-align:right;
// (2) 컨테이닝 블록 position:relative; 요소 블록 position:absolute; right:0;
// (3) float:right;
// text-align 과 float의 다른 점은 float은 요소를 둥둥 띄우는 것처럼 처리하기 때문에 다른 요소가 float 요소의 공간을 차지할 수 있다.
// float을 준 요소의 공간이 사라지는 것이 아니라 그 주변의 공간을 다른 요소들이 감싸는 형태로 배치가 된다.
// float 요소의 부모 태그가 float요소를 준 태그를 감싸고 있지 않을 수 있다(block format context).
//

// 박스 모델
// (1) margin : border와 다른 태그의 컨텐츠들 사이의 공간
// (2) border : 테두리 부분
// (3) padding : content와 border 사이의 공간
// (4) content : 텍스트나 이미지가 들어있는 공간
// margin을 제외한 border, padding, content가 실제 태그의 공간을 나타낸다.
// 기본적으로 html에서는 content, padding, border의 너비를 따로 친다.
//

// position 속성 : html은 기본적으로 코드가 작성된 순서대로 화면에 표시하지만, 그것과 상관없이 위치를 정하고 싶을 때 사용하는 속성이다.
// position: absolute -> 자신의 컨테이닝 블록을 기준으로 절대적인 위치를 설정할 수 있음.
// position: relative -> static position을 기준으로 상대적인 위치를 설정할 수 있음.
// position: fixed ->
// position: static(기본값) -> 해당 태그가 원래 있어야할 위치에 표시됨.

// border: 1px solid black -> 테두리를 실선 검정색 1px 두께로 설정함.
// border-radius: 50px; -> 테두리를 반지름 50px만큼 둥글게 설정함.

// transform 속성 : 태그를 변형시키는 속성

// list-style:none -> list item 앞에 붙는 동그라미를 없애줌.
// list-style 속성으로 li앞에 붙는 문양을 설정할 수 있음.

// z-index 속성 : 태그가 겹칠 때, 어떤 태그가 더 위로 올라올지 결정할 때 사용하는 속성
// z-index 속성값이 더 높을수록 태그가 위로 올라온다.