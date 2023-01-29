const path = require('path'); // node에서 제공하는 경로를 조작하는 프로그램(파일)?

module.exports = {
  name: 'word-relay-setting',
  mode: 'development', // 실제 서비스에서는 production으로 바꾸면 됨.
  devtool: 'eval',

  // entry랑 output이 제일 중요하다.
  entry: {
    app: ['./client.jsx', 'WordRelay.jsx'],
  }, // 입력
  output: {
    // path.join(기존경로, 합칠경로) -> 기존 경로와 합칠 경로를 받아서 하나의 경로로 함쳐준다.
    // __dirname : 현재 폴더 경로
    path: path.join(__dirname, 'dist'),
    filename: 'app.js',
  }, // 출력
};
