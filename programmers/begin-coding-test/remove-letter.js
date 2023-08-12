function solution(ms, letter) {
    return [...ms].filter(it => it !== letter)
        .join('');
}

function solution2(ms, letter) {
    return ms.replaceAll(letter, "");
}

function solution3(ms, letter) {
    return ms.split(letter)
        .join('')
}

console.log(
    solution('hello', 'l') === 'heo',
    solution2('hello', 'l') === 'heo',
    solution3('hello', 'l') === 'heo',
);
