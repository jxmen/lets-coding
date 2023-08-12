function solution2(ms) {
    return ms.split('')
        .reverse()
        .join('');
}

function solution(ms) {
    return [...ms]
        .reverse()
        .join('');
}

console.log(
    solution('hello') === 'olleh'
);
