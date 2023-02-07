const express = require('express');
const cors = require('cors');
const app = express(); // express 서버
const port = 3000;

// cors 안에 조건을 설정할 수 있다(default: 모든 요청을 허용).
// 아래의 코드 한 줄이 없다면 다른 html에서 요청이 왔을 때, CORS 오류가 뜨게 된다.
app.use(cors());

app.get('/sound/:name', (req, res) => {
  const { name } = req.params;
  // console.log(name);

  if (name === 'dog') {
    res.json({ sound: '멍멍' });
  } else if (name === 'cat') {
    res.json({ sound: '야옹' });
  } else if (name === 'pig') {
    res.json({ sound: '꿀꿀' });
  } else {
    res.json({ sound: '알수없음' });
  }
});

app.listen(port);
