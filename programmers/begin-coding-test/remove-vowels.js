// 모음
const vowels = ['a', 'e', 'i', 'o', 'u'];
const vowelRegex = /[aeiou]/g

function solution2(ms) {
    return [...ms].filter(s => !vowels.some(it => it === s))
        .join('');
}

function solution3(my_string) {
    return my_string.replace(vowelRegex, '');
}

function solution(ms) {
    console.time('solution2');
    solution2(ms)
    console.timeEnd('solution2');

    console.time('solution3');
    solution3(ms)
    console.timeEnd('solution3');

    return solution3(ms);
}

console.log(
    solution('business day is monday')
);
