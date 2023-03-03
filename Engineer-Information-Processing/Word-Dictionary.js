// IT 용어 사전

// - Refactoring :결과의 변경 없이 코드의 구조를 재조정함
// - Architecting : 기능면에서 바라본 본 구성 방식. 구조, 밑그림(설계 관련)
// - Specification(규격, 사양) : 명세서
// - Renewal(갱신) : 유지보수

// - DRM
// 출판자 또는 저작권자가 그들이 배포한 디지털 자료나 하드웨어의 사용을 제어하고 이를 의도한 용도로만 사용하도록 제한하는 데 사용되는 모든 기술들을 지칭하는 용어
// - 클리어링 하우스
// 디지털 저작권 라이선싱을 중개하고 라이선스 발급을 수행하는 정산소로서 라이센서(licensor)와 라이센시(licensee)가 아닌 제3의 운영주체가 운영한다.

// - 정보은닉(or 은닉성) : 다른 객체에게 자신의 정보를 숨기고 자신의 연산만을 통해 접근을 허용하는 것으로, 클래스 외부에서 특정 정보에 접근을 막는다는 의미

// - 복호화 : 디코딩, 즉 부호화된 데이터를 부호화되기 전 형태로 바꾸어 사람이 읽을 수 있는 형태로 되돌려 놓은 것
// - 형상관리 : 소프트웨어의 개발과정에서 소프트웨어의 변경사항을 관리하기 위해 개발된 일련의 활동
// - 저작권 : 창작물을 만든 이가 자신의 저작물에 대해 가지는 법적 권리
// - 크랙 : 소프트웨어를 수정하여 개발자가 원하지 않는 기능들, 보통은 수정 방식(복사 보호, 소프트웨어 조작 보호)을 비활성화하거나 제거하는 일

// - 액터 : 시스템과 상호작용하는 모든 것(사람, 기계, 시스템 등)
// - 유스케이스
// 행위자(actor)가 관심을 가지고 있는 유용한 일을 달성하기 위한 시나리오의 집합을 명시한다.
// 사용자 입장에서 바라본 시스템의 특성을 설명한 구조로서, 행위자, 시간의 흐름, 또는 시스템에 의해 개시되는 시나리오 집합의 형태를 갖추고 있다.

// - 데이터 흐름도(DFD) : 프로세스나 시스템의 정보 흐름을 나타내는 도표.

// - 데이터 사전(Data Dictionary, DD)
// 프로그래머의 편의를 위해 데이터 모델 내의 데이터 객체들이나 항목들의 설명을 모아놓은 것.
// 시스템 자신이 필요로 하는 여러가지 객체(기본 테이블, 뷰, 인덱스, 데이터베이스, 패키지, 접근 권한 등)에 관한 정보를 포함하고 있는 시스템 데이터베이스이다.
// 시스템 카탈로그(System Catalog), 메타 데이터(Meta Data)라고도 한다.
// 시스템 카탈로그 자체도 시스템 테이블로 구성되어 있어 SQL문을 이용하여 내용 검색이 가능하다.
// 사용자가 시스템 카탈로그를 직접 갱신할 수 없다.
// SQL문으로 여러가지 객체에 변화를 주면 시스템이 자동으로 갱신한다.

// - UML 표기법 : 시스템을 모델로 표현해주는 대표적인 모델링 언어.

// - 컴포넌트(Component)
// 명백한 역할을 가지고 독립적으로 존재할 수 있는 시스템의 부분으로 넓은 의미에서는 재사용되는 모든 단위라고 볼 수 있으며,
// 인터페이스를 통해서만 접근할 수 있는 것.
// 프로그래밍에 있어 재사용이 가능한 각각의 독립된 모듈.
// 특정 기능수행을 위해 독립적으로 분리.

// - 인터페이스 : 서로 다른 두 시스템이나 소프트웨어 등을 이어주는 부분 또는 접속 장치를 의미

// - 기능적 요구사항
// 시스템이 실제로 어떻게 동작하는지에 관점을 둔 요구사항
// - 비기능적 요구사항
// 시스템 구축에 대한 성능, 보안, 품질, 안정성 등으로 실제 수행에 보조적인 요구사항

// - 워크스루(Walk-through) : 요구사항 명세서 작성자를 포함하여 사전 검토한 후에 짧은 검토 회의를 통해 결함을 발견.
// - 인스펙션(Inspection) : 요구사항 명세서 작성자를 제외한 다른 검토 전문가들이 요구사항 명세서를 확인하면서 결함을 발견.

// - 정적 테스트 : 소프트웨어를 실행하지 않고 요구사항 정의서, 설계서, 소스 코드 등의 개발 산출물을 테스팅하는 것
// - 동적 테스트 : 실제 구현된 시스템(프로그램)을 실행하여 테스팅하는 것
// - 화이트박스 테스트
// 소프트웨어 또는 컴포넌트 등의 로직에 대한 테스트를 수행하기 위해 설계 단계에서 요구된 사항을 확인하는 개발자 관점의 단위테스팅 기법
// 종류 : 기초 경로 검사(Basic Path Testing), 조건 검사(Condition Testing), 루프 검사(Loop Testing), 데이터 흐름 검사(Data Flow Testing)
// 검증 기준 : 문장 검증 기준, 분기 검증 기준, 조건 검증 기준, 분기/조건 기준
// - 블랙박스 테스트
// 소프트웨어의 내부 구조나 작동 원리를 모르는 상태에서 소프트웨어의 동작을 검사하는 방법
// 종류 : 동치(동등) 분할 검사, 경계값 분석, 원인-효과 검사, 오류 예측 검사, 비교 검사

// - 컴파일(Compile)
// 주어진 언어로 작성된 컴퓨터 프로그램을 다른 언어의 동등한 프로그램으로 변환하는 기능
// 고급언어(사람이 인식하는 언어)를 저급언어(기계어)로 변환하는 기능이다.

// - 코딩(Coding)
// 프로그래밍 언어를 가지고 컴퓨터 프로그램을 작성할 수 있는 환경을 제공

// - 디버깅(Debugging)
// 프로그램에서 발견되는 버그를 찾아 수정할 수 있는 기능

// - 배포(Deployment)
// 소프트웨어를 최종 사용자에게 전달하기 위한 기능

// - JSON(JavaScript Object Notation)
// 속성-값 쌍으로 이루어진 데이터 오브젝트를 전달하기 위해 사용하는 개방형 표준 포맷이다.
// AJAX(Asynchronous Javascript And Xml)에서 많이 사용되고 XML을 대체하는 주요 데이터 포맷이다.
// 언어 독립형 데이터 포맷으로 다양한 프로그래밍 언어에서 사용되고 있다.

// - AJAX(Asynchronous Javascript And Xml) (asynchronous : 비동기적인)
// Ajax는 빠르게 동작하는 동적인 웹 페이지를 만들기 위한 개발 기법의 하나이다.
// Ajax는 웹 페이지 전체를 다시 로딩하지 않고도, 웹 페이지의 일부분만을 갱신할 수 있다.
// 즉, Ajax를 이용하면 백그라운드 영역에서 서버와 통신하여, 그 결과를 웹 페이지의 일부분에만 표시할 수 있다.

// - 스택(Stack) : LIFO(Last-In-First-Out), 데이터의 삽입과 삭제가 한 쪽 끝에서 일어남.
// - 큐(Queue) : FIFO(First-In-First-Out), 데이터의 삽입과 삭제가 양방향에서 일어남.
// - Tree & Graph : 비선형 구조

// - 트랜잭션(Transaction) : "쪼갤 수 없는 업무 처리의 최소 단위"를 말한다. 거래내역이라고도 한다.

// - 클러스터(Cluster)
// 데이터베이스에서 클러스터란 여러개의 서버가 하나의 데이터베이스를 나눠서 처리하는 형태를 뜻한다.
// 고가용성, 병렬처리, 성능향상 이러한 3가지를 만족하는 시스템의 구성형태를 데이터베이스 클러스터라고 한다.
// 컴퓨터 클러스터란 여러 대의 컴퓨터들이 연결되어서 하나의 시스템처럼 동작하는 컴퓨터들의 집합을 말한다. 여러개의 객체를 하나로 모은다는 개념을 가지고 있다.
// DB 클러스터도 똑같은 맥락이다. DB 서버를 여러개 둔다고 생각하면 된다. 이에 대한 기본적인 장점은 서버 한 대가 죽어도 대비가 가능하다는 점이다.
// DB 클러스터와 비슷한 형태로 복수개의 독립된 DB가 서로 연계되는 경우가 있다. 이 DB는 서로 데이터를 읽거나 쓰는데, DBLink 또는 SQL/MED라고 한다.

// - E-R 다이어그램(Entity-Relation Diagram, ERD)
// 현실 세계에 존재하는 데이터와 그들 간의 관계를 사람이 이해할 수 있는 형태로 명확하게 표현하기 위해서 가장 널리 사용되고 있는 모델
// 개념적 모델링의 대표적인 도구

// - Partially Committed : 마지막 연산이 실행된 직후의 상태로 아직 Commit 연산 실행 전
// - Committed : 트랜잭션이 실행을 성공적으로 완료하여 Commit 연산을 수행한 상태

// - 개체(Entity) : 사람, 장소, 사물, 사건 등과 같이 독립적으로 존재하면서 고유하게 식별이 가능한 단위이다.
// - 무결성(Integrity) : 데이터의 정확성과 일관성을 유지하고 보증하는 것을 가리키며 데이터베이스나 RDBMS 시스템의 중요한 기능이다.
// - 정확성(Correctness) : 중복이나 누락이 없는 상태
// - 일관성(Consistency) : 원인과 결과의 의미가 연속적으로 보장되어 변하지 않는 상태

// - 차수(Degree) : 속성의 수(열의 개수)
// - 카디널리티(Cardinality) : 튜플의 수(행의 개수)
// - 속성(Attribute) : 릴레이션에서 열
// - 카테시안 곱(Cartesian product)
// From절에 2개 이상의 Table이 있을때 두 Table 사이에 유효 join 조건을 적지 않았을때,
// 해당 테이블에 대한 모든 데이터를 전부 결합하여 Table에 존재하는 행 갯수를 곱한 만큼의 결과값이 반환되는 것이다.

// // - 프로토콜(Protocol)
// 복수의 컴퓨터 사이나 중앙 컴퓨터와 단말기 사이에서 데이터 통신을 원활하게 하기 위해 필요한 통신 규약.
// 신호 송신의 순서, 데이터의 표현법, 오류 검출법 등을 정함.

// - 헤더(Header)
// 데이터 앞 부분에 파일에 대한 정보를 실어놓은 부분
// 주로 데이터 형식에 대한 정보나 시간 데이터, 주소 데이터로 구성되어 있고 데이터의 종류에 따라 정리되기 쉽게 규격화해 놓은 데이터이다.
// 각 레이어는 아래 위로 인접한 층과 연계하여 통신을 처리한다.

// - 패킷(Packet)
// 네트워크를 통해 전송하기 쉽도록 자른 데이터의 전송 단위이다.
// 본래는 소포를 뜻하는 용어로, 소화물을 뜻하는 패키지(package)와 덩어리를 뜻하는 버킷(bucket)의 합성어이다.
// 정보 기술에서 패킷 방식의 컴퓨터 네트워크가 전달하는 데이터의 형식화된 블록이다.
// 패킷은 제어 정보와 사용자 데이터로 이루어지며, 이는 페이로드라고도 한다.

// - IP(Internet Protocol) : 인터넷이 통하는 네트워크에서 어떤 정보를 수신하고 송신하는 통신에 대한 규약

// - RIP(Routing Information Protocol)
// 거리 벡터 라우팅 프로토콜
// 라우팅 프로토콜을 IGP와 EGP로 분류했을 때 IGP에 해당한다.
// 최단 경로 탐색에는 Bellman-Ford 알고리즘을 사용한다.
// 최적의 경로를 산출하기 위한 정보로서 홉(거리 값)만을 고려하므로, RIP를 선택한 경로가 최적의 경로가 아닌 경우가 종종 발생할 수 있다.
// 소규모 네트워크 환경에 적합하다.
// 최대 홉 카운트를 15홉 이하로 한정하고 있다.

// - UNIX 운영체제
// 주로 서버용 컴퓨터에서 사용된다.
// time sharing system을 위해 설계된 대화식 운영체제이다.
// C언어로 작성되어 이식성이 높고, 장치간 호환성이 높다.
// Multi-User, Multi-Tasking을 모두 지원한다.
// 트리 구조의 파일 시스템이다.

// - UDP(User Datagram Protocol)
// 비연결형 및 비신뢰성 전송 서비스를 제공한다.
// 흐름 제어나 순서 제어가 없어 전송 속도가 빠르다.
// 수신된 데이터의 순서 재조정 기능을 지원하지 않는다.
// 복구 기능을 제공하지 않는다.
// 단순한 헤더 구조로 오버헤드가 적다.
// TCP와 같이 트랜스포트 계층에 존재한다.

// - 스레드(thread) : 영어 단어의 뜻은 '실'
// 어떠한 프로그램 내에서, 특히 프로세스 내에서 실행되는 흐름의 단위를 말한다.
// 일반적으로 한 프로그램은 하나의 스레드를 가지고 있지만, 프로그램 환경에 따라 둘 이상의 스레드를 동시에 실행할 수 있다.

// - 커널(kernel) : 영어 단어의 뜻은 '핵심'
// 컴퓨터 운영 체제의 핵심이 되는 컴퓨터 프로그램으로, 시스템의 모든 것을 완전히 통제한다.
// 운영 체제의 다른 부분 및 응용 프로그램 수행에 필요한 여러 가지 서비스를 제공한다.
// 컴퓨터의 하드웨어와 직접 상호작용하는 모듈이다.
// 제어 프로그램 중 주기억장치에 상주하는 모듈이다.
// 운영체제를 구성하는 프로세스와 운영체제의 제어 아래에서 수행하는 프로그램에 대한 자원할당을 수행한다.

// - 기밀성 : 사용을 승인받은 사람만 해당 정보에 접근할 수 있는 성질(방화벽, 암호, 비밀번호 등)
// - 무결성 : 적절한 권한을 가진 사용자에 의해 인가된 방법으로만 정보를 변경할 수 있도록 하는 성질
// - 가용성 : 정보 자산에 대해 적절한 시간에 언제든 접근 가능한 성질

// - BSD(Berkeley Software Distribution)
// 1977 ~ 1995년까지 미국 캘리포니아 대학교 버클리(University of California, Berkeley)의 CSRG(Computer Systems Research Group)에서 개발한 유닉스 운영체제
// 오늘날 BSD라는 용어는 유닉스 계열 운영 체제 계열에서 분기되어 형성된 BSD 파생판을 두루 가리키는 용어로 자리잡혀 있다.
// 오리지널 BSD 코드로부터 파생된 운영 체제들은 현재까지도 활발히 개발되고 널리 사용되고 있다.

// - 호스트 기반 침입 탐지 시스템(Host-based Intrusion Detection System, HIDS)
// 침입 및 오용을 탐지하기 위해 설치된 컴퓨터 시스템을 모니터링하고 활동을 기록하고 지정된 기관에 통지하여 응답하는 시스템

// - ACL(Access Control List, 접근 제어 목록)
// 개체나 개체 속성에 적용되어 있는 허가 목록을 말한다.
// 이 목록은 누가 또는 무엇이 객체 접근 허가를 받는지, 어떠한 작업이 객체에 수행되도록 허가를 받을지를 지정하고 있다.

// - tcp wrapper
// 호스트 기반 네트워킹 ACL 시스템으로서, 리눅스 또는 BSD 같은 운영 체제의 인터넷 프로토콜 서버에서 네트워크 접근을 필터링하기 위해 사용된다.
// - trace checker
// 측정 데이터를 자동으로 면밀히 분석하는 도구이다.
// 보고서 형식의 테스트 결과가 필요한 모든 경우에 특히 유용하다.
// 보고서 형식은 읽기 쉽고 해석하기 쉽다.
// 어디에서 수집된 데이터인지 또는 통합하려는 툴체인에 의존하지 않는다.

// - 셀(Cell)
// 이동 통신에서 하나의 기지국이 포괄하는 지역을 가리키는 개념이다.
// 휴대전화를 뜻하는 영어인 셀룰러 폰(cellular phone)은 여기에서 유래하였다.
// 이동 통신은 제한된 주파수 대역을 다수의 사용자가 이용하므로 주파수의 재활용이 중요한 기술적 요소가 된다.
// - Cellular : 이동 무선 통신에서 셀의 설치에 의해 통신망을 구성, 운용하는 것
// - V2X(Vehicle To Everything)
// 운전 중, 유/무선망을 통하여 다른 차량 및 도로 등 인프라가 구축된 사물과 교통정보와 같은 정보를 교환하는 통신기술이다.
// 본인의 자동차가 도로에 있는 모든 사물, 사람과 Communication 을 할수 있게 해 주는 기술.
// - C-V2X(Cellular Vehicle To Everything, 차량 사물 셀룰러 통신)
// C 는 Cellular 의 약자로서 V2X 의 커뮤니케이션 통로를 현재 사용중인 Cellular 망을 이용하는 것이 핵심이다.

// - BcN(Broadband Convergence Network, 광대역통합망)
// 음성·데이터, 유·무선 등 통신·방송·인터넷이 융합된 품질보장형 광대역 멀티미디어 서비스를
// 언제 어디서나 끊김없이(seamless) 안전하게 이용할 수 있는 차세대 통합 네트워를 말한다.

// - Zing
// 근거리무선통신(NFC)보다 8000배 빠른 초고속 근접 통신 기술이다. 한국전자통신연구원(ETRI)이 개발하고 코프가 사업화를 하고 있다.
// 기기를 키오스크에 갖다 대면 원하는 데이터를 바로 가져올 수 있다. 10㎝ 이내 거리에서 3.5Gbps 속도로 데이터를 전송한다.

// - 아키텍처(Architecture)
// 시스템 목적을 달성하기위해 시스템의 상호작용등의 시스템디자인에 대한 제약 및 설계.
// 최적화를 목표로 두고 시스템 구성과 동작원리 그리고 시스템의 구성환경등을 설명 및 설계하는 청사진 또는 설계도.
// - 아키텍처 구성
// 1) 시스템 구성 및 동작 원리
// 2) 시스템 구성요소에 대한 설계 및 구현을 지원하는 수준을 기술
// 3) 구성 요소 간의 관계 및 외부환경과의 관계 묘사
// 4) 요구사양 및 시스템 수명주기 고려
// 5) 시스템의 전체적인 최적화를 목표

// - 워크로드(Workload)
// 고객 대면 애플리케이션이나 백엔드 프로세스 같이 비즈니스 가치를 창출하는 리소스 및 코드 모음을 말한다.
// 워크로드는 단일 리소스의 하위 집합으로 AWS 계정으로 구성되거나 여러 리소스에 걸친 리소스의 모음일 수도 있다.
// 워크로드는 애플리케이션을 지원하는 스토리지 객체이다. 애플리케이션별로 하나 이상의 워크로드 또는 인스턴스를 정의할 수 있다.
// 일부 애플리케이션의 경우 시스템에서 기본 볼륨 특성이 비슷한 볼륨을 포함하도록 워크로드를 구성한다.
// 이러한 볼륨 특성은 워크로드가 지원하는 애플리케이션 유형에 따라 최적화된다.
// 예를 들어, Microsoft SQL Server 애플리케이션을 지원하는 워크로드를 생성한 다음 해당 워크로드에 대한 볼륨을 생성하는 경우,
// 기본 볼륨 특성은 Microsoft SQL Server를 지원하도록 최적화되어 있다.

// - 컨테이너(Container)
// 소프트웨어 서비스를 실행하는 데 필요한 특정 버전의 프로그래밍 언어 런타임 및 라이브러리와 같은 종속 항목과 애플리케이션 코드를 함께 포함하는 경량 패키지.
// 운영체제 수준에서 CPU, 메모리, 스토리지, 네트워크 리소스를 쉽게 공유할 수 있게 해주며,
// 컨테이너가 실제로 실행되는 환경에서 애플리케이션을 추상화할 수 있는 논리 패키징 메커니즘을 제공한다.
// - 컨테이너의 이점
// 1) 책임 분리
// 예를 들어, 개발자는 애플리케이션의 로직과 종속 항목에 집중하고,
// IT 운영팀은 특정 소프트웨어 버전 및 구성과 같은 애플리케이션의 세부 요소 대신 배포 및 관리에 집중할 수 있다.
// 2) 워크로드 이동성
// 컨테이너는 Linux, Windows, Mac 등의 운영체제를 가리지 않고, 가상머신, 물리적 서버, 개발자 컴퓨터, 데이터 센터, 온프레미스 환경, 퍼블릭 클라우드 등
// 사실상 어느 환경에서나 구동되므로 개발 및 배포가 쉬워진다.
// 3) 애플리케이션 격리
// 컨테이너는 운영체제 수준에서 CPU, 메모리, 스토리지, 네트워크 리소스를 가상화하므로
// 개발자에게 다른 애플리케이션으로부터 논리적으로 격리된 OS환경을 제공한다.

// - 어휘 분석기
// 어휘 분석 단계에서 검출되는 의미 있는 조각을 어휘항목(lexeme)이라고 하며,
// 어휘 분석기는 소스 코드에서 이러한 어휘항목을 검출하여 토큰을 생성한다(어휘 분석에서 사용하는 용어를 정의함).

// - 프레임워크(Framework)
// 반제품 상태의 제품을 토대로 도메인별로 필요한 서비스 컴포넌트를 사용하여 재사용성 확대와 성능을 보장받을 수 있게 하는 개발 소프트웨어이다.
// 설계 관점에 개발 방식을 패턴화시키기 위한 노력의 결과물인 소프트웨어 디자인 패턴을 반제품 소프트웨어 상태로 집적화시킨 것으로 볼 수 있다.
// 프레임워크의 동작 원리를 그 제어 흐름의 일반적인 프로그램과 반대로 동작한다고 해서 IoC(Inversion of Control)이라고 설명하기도 한다.
// 프레임워크는 이미 정해진 코드를 호출해 사용하고 자체적인 흐름을 가지고 있다.
// 뼈대 골조를 의미하고, 특정 기능을 수행하기 위한 "클래스"나 "인터페이스"를 모아둔 집합체이다.

// - 라이브러리(Library)
// 주로 소프트웨어를 개발할 때 컴퓨터 프로그램이 사용하는 비휘발성 자원의 모임이다.
// 여기에는 구성 데이터, 문서, 도움말 자료, 메시지 틀, 미리 작성된 코드, 서브루틴(함수), 클래스, 값, 자료형 사양을 포함할 수 있다.
// OS/360 및 이후 세대에서는 파티션 데이터 세트로 부른다.
// 프레임워크와는 달리 사용자 코드에서 라이브러리를 호출해서 사용하고, 그에 대한 제어도 사용자 코드가 가지는 방식이다.

// - 클라우드 기반 HSM(Cloud-based Hardware Security Module)
// 클라우드 시스템을 사용하면서 필요한 각종 암호 키를 보관, 관리하고 암호, 인증, 전자 서명 등에 필요한 암호 알고리즘을 수행한다.
// 클라우드 시스템 내에서 사용되는 보안 관련 설정부터 암호 키 관리, 암호화 서비스 등
// 모든 보안 관련 사항을 서비스로 제공하기 때문에 서비스형 하드웨어 보안 모듈(HSM as a Service)이라고도 한다.
// 클라우드 사용자는 다른 클라우드 자원과 마찬가지로 사용료만 지불하고 HSM 기능을 사용할 수 있다.
// 클라우드 시스템 내의 DB 데이터 암호화 작업 등에 사용하며, 필요한 경우에는 암호 키를 외부의 상용 HSM으로 내보낼 수도 있다.
// cloud HSM 서비스는 IBM의 ‘IBM cloud HSM’, 구글의 ‘Google cloud HSM’, 아마존의 ‘AWS cloud HSM’ 등 클라우드 서비스 업체에서 일반적으로 같이 제공한다.
// 참고로 cloud HSM 제공자는 암호 키를 보관할 때 사용하는 장소와 방법에 따라 FIPS 140-2 Level 3 인증 HSM 보안 등급 규격을 준수해야 한다.
// 클라우드(데이터 센터) 기반 암호화 키 생성, 처리, 저장 등을 하는 보안 기기이다.
// 국내에서는 공인인증제의 폐지와 전자서명법 개정을 추진하면서 클라우드 HSM 용어가 자주 등장하였다.
// 클라우드에 인증서를 저장하므로 기존 HSM 기기나 휴대폰에 인증서를 저장해 다닐 필요가 없다.
// 하드웨어적으로 구현되기 때문에 소프트웨어식 암호 기술에 내재된 보안 취약점을 해결할 수 있다.

// - 엔드포인트(End Point) : 네트워크에 연결하고 네트워크를 통해 통신하는 모든 디바이스

// - LAN(Local Area Network)
// 비교적 가까운 거리에 있는 단말 간의 네트워크를 말한다.
// 따라서 원거리 즉, Wide Area Network의 약자인 WAN과 구분할 수 있는데, 일상 생활에서는 구분 없이 사용한다.

// - Virtual Local Area Network(VLAN)
// 논리적으로 나눈 스위치 네트워크를 말한다(물리적 배치와 상관없이 논리적으로 LAN을 구성할 수 있는 기술).
// 논리적으로 분리된 인터넷 프로토콜(IP) 하위 네트워크 이며, 이 하위 네트워크의 엔드포인트는 기능 또는 기타 공유 특성에 의해 연결됩니다.
// 논리적으로 네트워크를 구분하기 때문에 네트워크 구성이 유연하다. VLAN을 사용하면 네트워크의 보안성이 강화된다.

// SNMP(Simple Network Management Protocol)
// UDP/IP(사용자 데이터그램 프로토콜/인터넷 프로토콜)를 사용하여 이더넷 연결을 통해 네트워크 관리 작업을 수행하는 응용 계층 프로토콜
// IP 네트워크상의 장치로부터 정보를 수집 및 관리하며, 또한 정보를 수정하여 장치의 동작을 변경하는 데에 사용되는 인터넷 표준 프로토콜이다.
// SNMP를 지원하는 대표적인 장치에는 라우터, 스위치, 서버, 워크스테이션, 프린터, 모뎀 랙 등이 포함된다.

// - Mesh Network
// Mesh-Network.js 참고

// - 모뎀(MODEM, MOdulator and DEModulator)
// 정보 전달(주로 디지털 정보)을 위해 신호를 변조하여 송신하고 수신측에서 원래의 신호로 복구하기 위해 복조하는 장치를 말한다.
// 주로 컴퓨터 정보통신을 위한 주변기기로 많이 사용한다. 변조를 하는 이유는 전송선에 디지털 신호를 바로 보내면 신호 전달이 잘 되지 않기 때문이다.
// 데이터가 같은 비트로 연속되면 전송특성상 신호 전달에 문제가 발생하므로 전송선의 특성에 맞추어 변조한다.
// 모뎀은 아날로그/디지털 변환기의 일종으로 컴퓨터의 디지털 신호를 아날로그 신호로 바꾸어 전송하고, 아날로그 신호를 받아 디지털 신호로 읽어낸다.
// 좁은 의미에서는 개인용 컴퓨터와 전화선을 이어주는 주변기기이다.
// 모뎀은 인터넷 연결에 꼭 필요한 기기(일반적으로 공유기를 나타내는 말)
// 라우터는 모뎀과 연결하여 여러 기기에서 인터넷에 접속할 수 있도록 하는 기기

// - Worm
// 악성코드(Malware)의 감염 방식 중 하나로, 웜은 여러 단말기에 분산되어 퍼져 그 단말기들이 연결된 네트워크 전체를 감염시킨다.
// 최근에는 악성코드 대부분이 네트워크 연결을 기본으로 트로이 목마의 위장 기능과 바이러스의 증식 기능을 탑재하기에 웜과 다른 악성코드를 칼같이 구분하지 않는다.
//  네트워크 전체를 공격한다는 특징 때문에 사이버 공격 도구로의 가치가 크게 주목받게 되면서
// 가장 위협적인 악성코드로써 전통적 악성코드 3종 분류 중 거의 유일하게 살아남은 분류로써 막대한 피해를 일으켜오고 있다.

// - Rogue Ware(Rogue Security Software, 가짜 백신 소프트웨어)
// 사용자가 컴퓨터에 바이러스가 있다고 잘못 믿게 하여 컴퓨터에 실제로 악성 프로그램을 설치하도록 하거나,
// 가짜 악성 프로그램 제거 도구에 대한 비용을 지불하도록 설득한다. 공포심을 통해 사용자를 조종하는 방식.

// - Adware
// 특정 소프트웨어를 실행할 때 또는 자동으로 활성화되는 광고 프로그램으로 이 자체는 악성코드로 보기는 힘들지만,
// 무분별한 광고 팝업을 뜨게 하는 등의 악용 위험성으로 악성코드로 분류되기도 함.

// - Reflection Attack(반사 공격)
// 송신자가 생성한 메시지를 가로채 공격자가 그 메시지를 다시 송신자에게 재전송하여 접근 권한을 얻는 형태의 공격 방법.
// - Reflection Attack 과정
// 사전에 암호 키를 공유한 송신자와 수신자는 상대방 식별을 위하여 각각 난수값을 생성하여 전송하고 이에 대한 암호값을 요청한 후
// 수신된 암호값을 복호화하여 그 결과 값이 자신이 송신하였던 난수값과 일치하는지 여부를 확인하여 상대방을 인증한다.
// 이때 공격자는 송신자가 보낸 난수값에 대한 암호값을 알기 위해 송신자가 보낸 난수값을 송신자에게 재전송하고,
// 이를 수신자의 난수값으로 인식한 송신자는 이에 대한 암호값을 공격자에게 전송하게 된다.
// 공격자는 이 암호값을 다시 송신자에게 전송함으로써 자신을 인증시키고, 그 결과 접근 권한을 획득.

// - ICMP(Internet Control Message Protocol)
// 여러 정보를 전달하거나 컨트롤하는 용도로 사용되는 프로토콜입니다.
// 패킷 전송이 실패했을 때 에러가 났음을 알림과 동시에, 해결 가능한 힌트를 제공합니다.

// - Ping of Death(죽음의 핑)
// 규정 크기 이상의 ICMP 패킷을 전송하여 시스템을 마비시키는 공격을 말한다.

// - Session Hijacking(세션 가로채기)
// 컴퓨터 시스템의 정보나 서비스에 무단으로 접근하기 위해 유효한 컴퓨터 세션(세션 키)을 이용하는 것.
// 일반적인 예로 두 컴퓨터 간에 활성화된 상태(즉, 로그인된 상태)에서 공격자가 피공격자의 로그인 정보를 활용하여 자신에게 필요한 행위를 하는 것을 뜻한다.

// - Piggyback Attack(피그백 공격)
// 공격자가 다른 사용자의 연결에서 계정을 사용하지 않는 비활성 기간(비활성 간격)을 이용하여 시스템에 엑세스(접근)한다.
// 이는 간선(회선 간) 공격이라고도 불린다.
// 쉽게 설명하자면, 시스템에 대한 합법적인 권한을 가진 사용자가 시스템에 접근할 때 활성화된 기간(직접 로그인 인증을 받아야 하는 상황)에는
// 접근 권한이 없는 공격자가 비활성화된 기간(합법적 사용자가 시스템에 접근 인증을 받은 상황)에 마치 사용자와 관련있는 사람인 듯이
// 태그를 붙여 몰래 뒤따라 들어가는 것이라고 할 수 있다.

// - XSS(크로스 사이트 스크립팅)
// 웹 사이트에 악성 스크립트를 주입하는 행위.
// 공격자가 상대방의 브라우저에 스크립트가 실행되도록 해서 사용자의 세션을 가로채거나, 웹 사이트를 변조하거나,
//  악의적 콘텐츠를 삽입하거나, 피싱 공격을 진행하는 것.

// - RBAC(Role Based Access Control, 역할 기반 접근 통제)
// 컴퓨터 시스템 보안에서 권한이 있는 사용자들에게 시스템 접근을 통제하는 하나의 방법이다.
// RBAC는 정보에 대한 사용자(User)의 접근 권한을 각 사용자의 Identity나 이미 정해진 규칙에 의해 판단하지 않고,
// 사용자가 소속된 조직 내에서의 역학(Role)에 따라 결정한다.
// 즉, 정보에 대한 접근 권한이 역할에 따라 배정된다.
// 500명 이상의 직원이 있는 기업 다수가 사용하며, 강제 접근 제어(MAC)나 임의 접근 제어(DAC)를 구현할 수 있다.

// - mandatory : 필수적인
// - MAC(Mandatory Access Control, 강제 접근 통제)
// 모든 정보에 security level을 각 user에게 security clearance(보안허가)를 할당하고 clearance를 가진 data에 대해서만 접근하도록 허용한다.

// - discretionary : 재량의
// - DAC(Discretionary Access Control, 임의 접근 통제)
// user의 identity에 따라 object에 대한 접근을 제어한다.

// - man-month : 엄밀히는 men per month이고, 프로젝트에 투입되는 인원 대비 프로젝트 기간(월)을 나타낸다.

// - COCOMO
// 소프트웨어 개발의 공정 개발 기간의 견적 방법 중 하나이며, 1981년에 배리 W. 보임이 제창했다.
// 이 모델은 프로젝트에 영향을 줄 수있는 다양한 특성들을 변수로 회귀공식을 만들어 소프트웨어 개발 비용을 산정한다.
// 예를 들면, 개발기간, 투입공수가 프로젝트에 영향을 줄 수 있는 특성에 해당한다.
// 모델의 복잡도에 따라 Basic COCOMO, Intermediate COCOMO, Detailed COCOMO으로 세분화 할 수 있다.
// 개발 유형에 따라 조직형(Organic), 반분리형(Semi-Detached), 내장형(Embedded)으로 구분한다.

// - 지식 : 본인이 알고 있는 것(패스워드, PIN 등)
// - 소유 : 본인이 가지고 있는 것(토큰, 스마트카드 등)
// - 존재 : 본인을 나타내는 것(홍채, 지문 등)
// - 행위 : 본인이 하는 것(서명, 움직임, 음성 등)

// - AAA(Authentication Authorization Accounting)
// 보안의 한 방법으로, 지속적으로 독립적인 세가지 보안 기능(Authentication, Authorization, Accounting)을 설정 하는 구조적인 프레임이다.

// - Authentication(인증)
// 인증이란 식별 가능한 정보(이름, 이메일)를 이용하여 서비스에 등록 유저의 신원을 입증하는 과정이다.
// 즉, 자신의 서비스에 등록된 사용자에게만 서비스를 제공한다는 뜻.
// 역할 : 사용자 신원 확인, 로그인과 패스워드 응답, 도전과 응답, 메시징, 암호화
// - 인증(Authentication)의 방식
// 1) 세션-쿠키 방식 : 이번 글에는 해당 방식만 알아볼 것이다.
// 2) 토큰 (JASON Web Token, JWT) 방식
// 3) 다른 채널을 통해 인증(OAuth)

// - Authorization(인가)
// 인증만 가지고는 서비스를 운영하기에는 무리가 있다.
// 인증을 한 사용자에게 모든 서비스를 제공하게 된다면?
// 내가 작성한 글이 다른 사람에 의해서 수정되거나 삭제될 수 있다.
// 따라서 인증된 사용자가 접근하려는 자원에 대한 권한이 있는지 확인하는 절차가 필요할 것이다.
// 또한 인가는 항상 앞에 인증이라는 선행 프로세스가 필요하다(인증하지 않은 유저의 권한을 알 수 없기 때문에).
// 역할 : 멀티 프로토콜로 IP, IPX, ARA, Telnet, ssh를 지원

// - Accounting(회계)
// 인증받은 사용자가 무슨 동작을 하는지 기록하고 기록한 데이터를 AAA서버로 보내는 역할을 한다.

// - SDN(Software Defined Networking, 소프트웨어 정의 네트워킹)
// 네트워크 리소스를 최적화하고 변화하는 비즈니스 요구, 애플리케이션 및 트래픽에
// 신속하게 네트워크를 채택하는 데 도움이 되는 네트워크 가상화 및 컨테이너화에 대한 접근 방식이다.
// 개방형 API(오픈플로우)를 통해 네트워크의 트래픽 전달 동작을 소프트웨어 기반 컨트롤러에서 제어/관리하는 접근방식이다.
// 트래픽 경로를 지정하는 컨트롤 플레인과 트래픽 전송을 수행하는 데이터 플레인이 분리되어 있다.
// 따라서 네트워크의 세부 구성정보에 얽매이지 않고 요구사항에 따라 네트워크를 관리할 수 있다.

// - NFS(Network File System)
// 네트워크상에서 공유되는 파일 시스템.
// 다른 원격 호스트의 파일 시스템을 로컬 디스크에 접근하듯 간단하게 접근하여 자신의 디렉토리처럼 사용할 수 있다.

// - Network Mapper
// 네트워크 보안을 위한 유틸리티.
// 네트워크의 보안을 위해 물리적 연결, 어떤 서버와 운영체제가 작동 중인지 등을 조사하는 응용 프로그램

// - AOE Network(Activity On Edge Network)
// 어떤 프로젝트를 마치기까지 수행되는 작업의 각 단계(상태)를 그래프의 정점(Vertex)으로 표현하고,
// 작업 하나가 완료되어 다음 단계로 넘어가는 시간을 그래프의 간선(Edge)으로 나타낸 방향 그래프.

// - PERT(Program Evaluation and Review Technique) 차트
// 프로젝트에서 작업 일정을 수립하고 작업을 정리 및 계획하는 데 사용하는 툴이다.
// PERT는 프로그램 평가(Program Evaluation) 및 검토 기법(Review Technique)을 뜻합니다
// 프로젝트의 타임라인을 개별 작업으로 세분화하여 시각적으로 표현한 것으로, 간트 차트와 유사하지만 구조가 다르다.
// 프로젝트의 작업과 각 작업의 종속 관계를 시각적으로 표현한다.
// 프로젝트를 실제로 시작하기 전에 초기 일정과 예상 타임라인을 생성하여 프로젝트 이해관계자에게 공유하는 방식으로 사용할 수 있다.
// - PERT 차트와 같은 프로젝트 로드맵을 만들면 다음과 같은 프로젝트 계획 활동을 수행하는 데 도움이 된다.
// 1) 일정 및 타임라인에 대해 리더의 승인 받기
// 2) 이해관계자에게 프로젝트의 목표를 전달하기
// 3) 복잡한 프로젝트를 시각적으로 계획하기
// 4) 개별 작업을 완료하는 데 필요한 기간 예상하기

// - 네트워크 도표
// 제안된 사업의 시행으로 각종 행동이 연쇄적으로 어떤 환경 영향을 미치는지를 인과 관계를 중심으로 원인과 영향을 추적하여 일종의 네트워크로 표시하는 방식.
// 일반적으로 몇 개의 가능한 인과 관계를 네트워크로 표시하여, 제안된 사업의 각 행동이 야기할 각종 환경 영향을 사용자가 손쉽게 파악할 수 있게 한다.

// - GANTT 차트
// GANTT가 제안한 프로젝트 일정관리를 위한 바(bar)형태의 도구로서, 업무별로 일정의 시작과 끝을 그래픽으로 표시하여 전체 일정을 한눈에 볼 수 있다.
// 또한, 각 업무(activities) 사이의 관계를 보여줄 수도 있다.

// - 테스트 스텁(Test Stub)
// 제어 모듈이 호출하는 타 모듈의 기능을 단순히 수행하는 도구로, 일시적으로 필요한 조건만을 가지고 있는 테스트용 모듈.
// - 테스트 드라이버(Test Driver)
// 테스트의 대상이 되는 하위 모듈을 호출하고 파라미터를 전달하는 가상의 모듈로, 상향식 테스트에 사용된다.
// - 테스트 슈트(Test Suites)
// 테스트 대상 컴포넌트나 모듈, 시스템에 사용되는 테스트 케이스의 집합.
// - 테스트 케이스(Test Case)
// 사용자의 요구사항을 정확히 준수했는지 확인하기 위한 입력값, 실행조건, 기대결과 등으로 만들어진 테스트 항목의 명세서.

// - 클린코드 작성 원칙
// 1) 추상화 : 상위 클래스는 프로그램 특성만 간략하게, 하위 클래스는 세부적인 내용 구현.
// 2) 의존성 배제 : 다른 모듈에 미치는 영향을 최소화하여 코드 변경시 영향이 가지 않도록 작성.
// 3) 중복성 최소화 : 코드의 중복을 최소화.
// 4) 가독성 : 누구든 읽기 쉽게 작성.
// 5) 단순성 : 프로그램을 최소 단위로 분리해 작업을 한 번에 하나씩 처리하도록 작성.

// - 정형 기술 검토(Formal Technical Review, FTR)
// 1) 제품 검토의 집중성
// 오류 검출에 초점을 두고 해결책을 나중으로 미룸.
// 2) 사전 준비성
// 검토를 위한 자료를 사전에 배포하여 검토하도록 한다.
// 3) 의제의 제한성
// 의견을 제한하되 충분히 받아들인다.
// 4) 안건 고수성
// 안건을 세우면 고수한다.
// 5) 논쟁 반박의 제한성
// 논쟁과 반박을 제한한다.
// 6) 문제 공개성
// 문제 영역을 공개한다.
// 7) 참가 인원의 제한성
// 참가자의 수를 제한한다.
// 8) 문서성
// 발견된 오류는 문서화한다.

// - 소프트웨어 재공학(Software Re-engineering)
// 기존 소프트웨어를 버리지 않고 기능을 개선시키거나 기능을 새로운 소프트웨어로 재활용하는 등 소프트웨어 재사용 공법
// Analysis : 기존 소프트웨어를 분석하여 재공학 대상을 선정하는 것
// Migration(이주) : 기존 소프트웨어를 다른 운영체제나 하드웨어 환경에서 사용할 수 있도록 변환하는 작업
// Restructuring : 기존 소프트웨어를 향상시키기 위하여 코드를 재구성하는 작업(기능과 외적 동작은 변하지 않음)
// Reverse Engineering : 기존 소프트웨어를 분석하여 소스코드를 얻어내는 작업(소스코드로 소프트웨어를 만드는 작업의 역작업)

// - NS Chart(Nassi-Schneiderman Chart)
// 논리의 기술에 중점을 두고 도형을 이용한 표현 방법이다.
// 3가지 기본 구조만으로 논리를 표현(표준화 가능).
// Flow Chart의 최대 단점인 화살표가 표시되지 않음.
// 기본구조의 입구와 출구는 각 하나씩이다.
// 이해하기 쉽고 코드 변환이 용이하다.
// 연속, 선택, 반복 등의 제어 논리 구조를 표현한다.

// - 코드 인스펙션(Code Inspection)
// 결함 뿐만 아니라 모든 것이 표준대로 되어 있는 지 확인하기 위한 검토.
// 표준이나 명세서에 서술항 내용과 비교하여 편차와 에러를 식별하기 위해 산출물을 근거로 수행하는 검사.
// 정적 테스트에 가깝다.

// - ISO### ISO/IEC 25000
// ISO 9126, ISO 14598. ISO 12119의 여러 표준 문서를 통합하고 재구성하여 만든 표준 문서(SW 품질 평가 통합 모델).
// SQuaRE(Software Quality and Requirement Evaluation)라고도 함.
// ISO/IEC 9126, ISO/IEC 12119, ISO/IEC 14598의 3개 표준을 통합한 모델.
// 2500n, 2501n, 2502n, 2503n, 2504n의 다섯가지 분야로 나눌수 있고, 확장 분야인 2505n이 있다.
// 1) 2500n(개요 및 품질관리)
// SQuaRE에 대한 개요, 전체에 대한 계획과 관리
// 2) 2501n(품질모델)
// 품질 모델 및 품질 사용 안내
// 3) 2502n(품질측정)
// 매트릭을 통한 측정 방법 제시
// 4) 2503n(품질요구)
// 품질 요구사항
// 5) 2504n(품질평가)
// 품질 프로세스에 관한 개요, 관점들의 평가 프로세스
// 6) 2505n(확장분야)

// - 임계 경로법(Critical Path Method, CPM)
// 프로젝트를 일정 기일 내에 완성시키고 해당 계획이 원가의 최소값에 의해 보증되는 등의 최적 스케줄을 구하는 관리 방법.
// 네트워크를 중심의 논리적 구성하고 시간과 비용 문제를 취급한다.
// 주로 건설과 설계를 포함하는 복잡한 일에 이용되어 효과를 발휘 함.
// - 위험 분석(Risk Analysis)
// 프로젝트에 내재된 위험 요소를 인식하고 그 영향을 분석하여 이를 관리하는 활동. 프로젝트를 성공시키기
// 위하여 위험 요소를 사전에 예측, 대비하는 모든 기술과 활동을 포함한다.
// - 업무 분업 구조(Work Breakdown Structure)
// 성과 목표 완전 달성을 위한 프로그램.
// 산업 관리 간접 부문의 기술 혁신형 업무-목표를 설정하여 소정 기간, 자원 내에서 달성하는 형태의 업무-를 효과적으로 수행하기 위한 수법.
// - 폭포수 모델(Waterfall Model)
// S/W 개발 생명주기에 기반하고 있는 소프트웨어 개발 기법 중 하나.
// 한 번 떨어지면 다시 거슬러 올라갈 수 없는 폭포수처럼, 각 개발 단계를 확실히 매듭 짓고 다음 단계로 넘어간다는 의미.

// - 데이터베이스 물리적 설계시 고려사항
// 어떤 인덱스를 만들 것인지에 대한 고려
// 성능 향상을 위한 개념 스키마의 변경 여부 검토
// 레코드의 크기
// 파일과 구조 저장을 위한 최소한의 공간
// 빈번한 질의와 트랜잭션들의 수행 속도를 높이기 위한 고려사항

// - 관계 대수
// https://m.blog.naver.com/k97b1114/140152644090
// 컴퓨터 과학의 관계형 데이터베이스의 관계 모델에서, 집합론과 1차 논리에 기반하여 관계(표)로 표현된 데이터를 취급하는 대수적인 연산 체계

// - CREATE TABLE 문에 포함되는 기능
// 1) PRIMARY KEY : 테이블의 기본 키를 정의, 유일하게 테이블의 각 행을 식별
// 2) FOREIGN KEY : 참조 대상을 테이블로 명시, 외래 키를 정의, 열과 참조된 테이블의 열 사이의 외래 키 관계를 적용하고 설정
// 3) UNIQUE : 테이블 내에서 얻은 유일한 값을 갖도록 하는 속성
// 4) NOT NULL : 해당 컬럼은 NULL값을 포함하지 않도록 하는 속성
// 5) CHECK : 개발자가 정의하는 제약조건, 참(TRUE)이어야 하는 조건을 지정
// 6) DEFAULT : 해당 필드의 기본값을 설정

// - 분산 데이터베이스의 구성 요소
// 1) 분산 처리기
// 2) 분산 데이터베이스
// 3) 통신 네트워크
// 4) 분산 트랜잭션
// - 분산 데이터베이스의 구조 - 전역, 분할(단편화), 할당, 지역 스키마

// - CASCADE : 영어 뜻은 종속
// 일반적으로 데이터베이스 내의 임의의 다른 테이블과 PRIMARY KEY 또는 FOREIGN KEY로서의 관계가 여전히 존재하는 경우,
// PRIMARY KEY가 존재하는 테이블을 마음대로 제거 및 비활성화 할 수 없다.
// 즉, CASCADE는 부모 테이블과 자식테이블 간에 참조 설정이 되어 있을 때,
// 부모 테이블의 제약 조건을 비활성화 시키면서 이를 참조하고 있는 자식 테이블의 제약 조건까지 함께 비활성화시키기 위해 사용한다.
// 또한 부모 테이블만 삭제하고 싶을 경우, 원래는 자식테이블을 먼저 삭제하고 부모를 삭제 한 뒤 자식테이블을 다시 만들어야하는 번거로움이 따른다.
// 하지만 CASCADE를 쓰면 일시적으로 참조관계를 끊을 수 있다.

// - 데이터베이스 병행제어
// 트랜잭션이 병행 수행될 때 트랜잭션이 데이터베이스의 일관성을 파괴하지 않고, 다른 트랜잭션에 영향을 주지 않도록 트랜잭션 간의 상호작용을 제어하는 것을 말한다.
// - 병행제어의 목적
// 1) 여러 사용자들의 데이터베이스 공동 사용을 최대화
// 2) 사용자의 응답 시간 최소화
// 3) 데이터베이스 시스템의 활용도 최대화
// 4) 데이터베이스의 일관성 유지
//