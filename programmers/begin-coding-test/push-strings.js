function solution2(A, B) {
    if (A === B) {
        return 0;
    }
    
    for (let i=1; i<A.length; i++) {
        const arr = [...A];
        
        Array.from({length:i})
            .forEach(() => {
                arr.unshift(arr.pop());    
        });
        
        if (arr.join('') === B) {
            return i;
        }
    }

    return -1;
}

function solution(a, b) {
    return (b+b).indexOf(a);
}