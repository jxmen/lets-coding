function solution(n) {
    const cnt = getCnt(n);

    return Number(cnt % BigInt(1234567));
}

let cache = {};

function getCnt(n) {
    if (cache[n]) {
        return BigInt(cache[n]);
    }

    if (n <= 3) {
        return BigInt(n);
    }

    // 1칸 또는 2칸 이동한 후, 해당 칸에서 남은 경우의 수를 더한 값을 리턴한다.
    const val1 = getCnt(n - 1);
    cache[n-1] = BigInt(val1);

    const val2 = getCnt(n - 2);
    cache[n-2] = BigInt(val2);

    return BigInt(val1 + val2);
}
