function solution(s)
{
    // stack으로 구현한다.
    const stack = [];

    s.split('').forEach(it => {
        const poped = stack.pop();
        if (poped === it) {
            return;
        }

        if (poped !== undefined) {
            stack.push(poped);
        }

        stack.push(it);
    });

    return stack.length === 0 ? 1 : 0;
}
