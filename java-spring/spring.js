// Spring

// - Spring 프로젝트 생성
// 1) https://start.spring.io/에 접속하여 양식을 작성한 후에 Generate 버튼을 누른다.
// - Maven, Gradle project
// 필요한 라이브러리를 임포트하고 빌드하는 라이프사이클까지 다 관리해주는 툴
// 최근에는 Gradle project를 많이 사용한다.
// - Spring Boot
// 버전을 선택하는 부분.
// Snapshot, M1 : 아직 개발중이거나 릴리즈되지 않은 버전.
// - Project Metadata
// Group : 주로 기업명 등을 기입한다.
// Artifact : 빌드되어 나오는 프로젝트의 결과물의 이름을 기입한다.
// Name : Artifact와 비슷하고 프로젝트 이름을 기입한다.
// Description : 프로젝트에 대한 설명을 기입한다.
// - Dependencies
// 어떤 라이브러리를 임포트하여 사용할 것인지 정의하는 부분이다.
// ex) Spring Web, Thymeleaf(HTML 템플릿 엔진) 등
// 2) 다운로드된 zip 파일을 압축해제하여 원하는 디렉토리에 저장한 후에 IntelliJ로 build.gradle을 열어준다.

// - Spring 프로젝트 구조
// 1) .idea : IntelliJ가 사용하는 설정 파일.
// 2) gradle\wrapper : gradle을 쓰는 폴더.
// 3) src\main\java : 실제 java 소스 코드 파일들을 저장하는 디렉토리.
// 4) src\main\resources : 프로젝트의 java 코드를 제외한 HTML, XML, 속성들, 설정 같은 것을 저장하는 디렉토리.
// 5) src\test : 테스트 코드들과 관련된 파일들을 저장하는 디렉토리.
// 6) build.gradle : 프로젝트에 대한 기본적인 정보, 설정들을 정의하는 파일.
//    repositories { mavenCentral() } : 라이브러리를 다운로드 받는 사이트를 보여준다.
//    dependencies {} : 임포트한 라이브러리들을 보여준다.

// - Spring 라이브러리
