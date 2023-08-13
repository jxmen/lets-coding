function solution2(n) {
    return Array(n)
        .fill()
        .map((_, idx) => idx + 1)
        .filter(it => it % 2 !== 0);
}

// Array.from을 써서 해보자.
function solution(n) {
    return Array.from({length: n}, (v, i) => i+1)
        .filter(it => it % 2 !== 0);
}

