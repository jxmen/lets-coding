const {producer} = require('./index');

const EVENT_PAYLOAD = {
    id: 1,
    message: 'Hello World!',
};

async function main() {
    await producer({
        body: EVENT_PAYLOAD,
        context: {
            stage: 'local'
        }
    });
}

main();
