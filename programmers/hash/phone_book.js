/**
 * 일반적인 순회 방법. 시간 복잡도는 O(n^2)이며, 효율성 테스트에서 실패한다.
 */
function solution2(phone_book) {
    if (phone_book.length === 1) {
        return false;
    }

    // 해시로 어떻게 최적화 할까? 해시 테이블?
    // 123 - 456
    const hash = {};
    phone_book.forEach(it => hash[it] = it);

    for (const key in hash) {
        for (const pb of phone_book) {
            if (pb === key) continue;

            if (pb.startsWith(key)) {
                return false;
            }
        }
    }

    return true;
}

/**
 * https://betterdev.tistory.com/12
 **/
function solution(phone_book) {
    if (phone_book.length === 1) {
        return false;
    }

    const set = new Set(phone_book);
    for (const str of phone_book) {

        for (let i=0; i<str.length; i++) {
            const temp = str.substring(0, i);

            // set이 검색이 빠르다는 장점을 이용해, 0~i번까지 접두어가 있는지 순회하면서 찾는다.
            if (set.has(temp)) {
                return false;
            }
        }

    }

    return true;
}
