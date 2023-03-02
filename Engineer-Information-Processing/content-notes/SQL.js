// SQL(Structured Query Language) 구문
// 관계형 데이터베이스 관리 시스템(RDBMS)의 데이터를 관리하기 위해 설계된 특수 목적의 프로그래밍 언어이다.

// (1) DDL(Data Definition Language)
// 주로 데이터베이스 관리자나 데이터 베이스 설계자가 데이터베이스 스키마를 정의하거나 조작하기 위한 언어이다.
// 생성, 수정, 삭제 등의 데이터 전체 골격을 결정하는 역할을 담당한다.

// - DDL의 데이터 정의어(CREATE, ALTER, DROP)
// 1) CREATE : SCHEMA(스키마), DOMAIN(도메인), TABLE(테이블), VIEW(뷰), INDEX(인덱스)를 정의
// 2) ALTER : TABLE에 대한 정의를 변경하는 데 사용
// 3) DROP : SCHEMA(스키마), DOMAIN(도메인), TABLE(테이블), VIEW(뷰), INDEX(인덱스)를 삭제

// (2) DML(Data Manipulation Language)
// 저장된 데이터를 실질적으로 처리하는 데 사용하는 언어이다.
// 데이터 베이스 사용자와 데이터 베이스 관리 시스템 간의 인터페이스를 제공한다.

// - DML 레코드 제어 명령어(SELECT, INSERT, DELETE, UPDATE)
// 1) SELECT : 테이블에서 조건에 맞는 튜플을 검색
// 2) INSERT : 테이블에 새로운 튜플을 삽입
// 3) DELETE : 테이블에서 조건에 맞는 튜플을 삭제
// 4) UPDATE : 테이블에서 조건에 맞는 튜플의 내용을 변경

// (3) DCL(Data Control Language)
// 객체 권한 부여 등의 제어어로 데이터의 보안, 무결성, 데이터 회복, 병행 수행 제어 등을 정의하는 데 사용하는 언어이다.
// 데이터 베이스 관리자가 데이터 관리를 목적으로 사용한다.

// - DCL 데이터 제어어(COMMIT, ROLLBACK, GRANT, REVOKE)
// 1) COMMIT : 명령어로 수행된 결과를 실제 물리적 디스크로 저장하고, 명령어로 수행을 성공적으로 완료하였음을 선언한다.
// 2) ROLLBACK : 명령어로 수행을 실패하였음을 알리고, 수행된 결과를 원상복구시킨다.
// 3) GRANT : 데이터베이스 사용자에게 사용 권한을 부여한다.
// 4) REVOKE : 데이터베이스 사용자로부터 사용 권한을 취소한다.

// - SELECT
// SELECT (속성명) FROM (테이블명) WHERE (조건) ORDERED BY (속성명 ASC(오름차순 : default) or DESC(내림차순))

// - GRANT
// GRANT CREATE TABLE PARK;
// SQL문 의미 : PARK에게 [CREATE TABLE] 권한을 부여한다.

// - REVOKE
// REVOKE CREATE TABLE FROM PARK;
// SQL문 의미 : PARK으로부터 [CREATE TABLE] 권한을 취소한다.
