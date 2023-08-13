function gcd(a, b) {
    // 최대공약수를 구하는 함수
    if (b === 0) {
        return a;
    }
    return gcd(b, a % b);
}

function lcm(a, b) {
    // 최소공배수를 구하는 함수
    return (a * b) / gcd(a, b);
}

// 두 수의 최소공배수를 계산하는 예제
const num1 = 12;
const num2 = 18;
const result = lcm(num1, num2);
console.log(`두 수 ${num1}과 ${num2}의 최소공배수는 ${result}입니다.`);
