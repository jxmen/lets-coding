const { app } = require('./index');
const port = 3000;

// Express.js 서버 시작
app.listen(port, () => {
    console.log(`Local server listening at http://localhost:${port}`);
});
