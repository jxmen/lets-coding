function solution2(i, j, k) {
    return new Array(j-i+1)
        .fill()
        .map((it, idx) => idx + i)
        .flatMap(it => it.toString().split(''))
        .filter(it => it === k.toString())
        .length;
}

// split으로 k의 여집합을 구하는 예제
function solution(i, j, k) {
    let a ='';
    for(i;i<=j;i++){
        a += i;
    }

    return a.split(k).length-1;
}

console.log(
    solution(1, 13, 1) === 6,
    solution2(1, 13, 1) === 6
);
