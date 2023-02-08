const http = require('http');
const fs = require('fs');
const url = require('url');

const app = http.createServer(function (req, res) {
  let _url = req.url;
  const queryData = url.parse(_url, true).query;
  const { pathname } = url.parse(_url, true);

  if (pathname === '/') {
    if (queryData.id === undefined) {
      const title = 'Welcome';
      data = 'Hello, Node.js';
      const template = `
        <!doctype html>
        <html>
        <head>
          <title>WEB1 - ${title}</title>
          <meta charset="utf-8">
        </head>
        <body>
          <h1><a href="/">WEB</a></h1>
          <ol>
            <li><a href="/?id=HTML">HTML</a></li>
            <li><a href="/?id=CSS">CSS</a></li>
            <li><a href="/?id=JavaScript">JavaScript</a></li>
          </ol>
          <h2>${title}</h2>
          <p>${data}</p>
        </body>
        </html>`;
      res.writeHead(200);
      res.end(template);
    } else {
      fs.readFile(`./data/${queryData.id}`, 'utf-8', (err, data) => {
        const title = queryData.id;
        const template = `
          <!doctype html>
          <html>
          <head>
            <title>WEB1 - ${title}</title>
            <meta charset="utf-8">
          </head>
          <body>
            <h1><a href="/">WEB</a></h1>
            <ol>
              <li><a href="/?id=HTML">HTML</a></li>
              <li><a href="/?id=CSS">CSS</a></li>
              <li><a href="/?id=JavaScript">JavaScript</a></li>
            </ol>
            <h2>${title}</h2>
            <p>${data}</p>
          </body>
          </html>`;
        res.writeHead(200);
        res.end(template);
      });
    }
  } else {
    res.writeHead(404);
    res.end('Not found');
  }
});
app.listen(3000);
