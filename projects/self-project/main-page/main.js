$(document).ready(function () {
  // 분류 기준을 선택하면 해당 분류 기준에 따른 인기 게시글 보여줌
  $('#recommend-sort').on('click', function () {
    $('.pop-reviews').removeClass('view-pop');
    $('.pop-reviews').removeClass('comment-pop');
    $('.pop-reviews').addClass('recommend-pop');
  });
  $('#view-sort').on('click', function () {
    $('.pop-reviews').removeClass('recommend-pop');
    $('.pop-reviews').removeClass('comment-pop');
    $('.pop-reviews').addClass('view-pop');
  });
  $('#comment-sort').on('click', function () {
    $('.pop-reviews').removeClass('view-pop');
    $('.pop-reviews').removeClass('recommend-pop');
    $('.pop-reviews').addClass('comment-pop');
  });

  // 인기 게시글 이전 버튼
  $('#prev-pop-btn').on('click', function () {
    $('.pop-reviews').last().prependTo('#popular-reviews-container');
  });

  // 인기 게시글 다음 버튼
  $('#next-pop-btn').on('click', function () {
    $('.pop-reviews').first().appendTo('#popular-reviews-container');
  });

  // 자동으로 3초마다 다음으로 넘어가기
  /* setInterval(function () {
    $('#next-pop-btn').trigger('click');
  }, 3000); */

  // 로그인 버튼 클릭시 id, pw 확인하여 로그인된 화면 or 메시지 출력
  let savedId = 'admin';
  let savedPw = '1234';
  $('#before-login').on('submit', function (e) {
    e.preventDefault();
    if (e.target.userId.value === savedId && e.target.userPw.value === savedPw) {
      $('#before-login').addClass('blind');
      $('#after-login').removeClass('blind');
    } else {
      alert('아이디 또는 비밀번호를 확인하세요.');
    }
  });

  // id찾기, pw찾기, 회원가입 누르면 팝업창 띄우기
  $('#find-id').on('click', function (e) {
    e.preventDefault();
    window.open('../signup-findidpw-page/findid.html', 'find id', 'width=400, height=600');
  });
  $('#find-pw').on('click', function (e) {
    e.preventDefault();
    window.open('../signup-findidpw-page/findpw.html', 'find pw', 'width=400, height=600');
  });
  $('#sign-up').on('click', function (e) {
    e.preventDefault();
    window.open('../signup-findidpw-page/signup.html', 'sign up', 'width=400, height=600');
  });
});
