// console.log('Hello, World');

var figlet = require('figlet');

figlet('Kim Jong In', function (err, data) {
  if (err) {
    console.log('Something went wrong...');
    console.dir(err);
    return;
  }
  console.log(data);
});
