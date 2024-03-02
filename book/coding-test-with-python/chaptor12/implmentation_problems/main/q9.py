def solution(s):
    answer = 0

    answers = []
    # 문자열을 1~n까지 압축했을때의 길이들을 다 리스트에 담고 최소값을 리턴한다.

    for i in range(1, len(s) + 1):

        # 이전 값이 같을때만 압축해야 한다.
        # 문자열을 i 길이의 만큼 잘라야 한다.
        strs = []
        prev_str, prev_count = None, 1
        for j in range(0, len(s), i):
            # 문제: len이 3일때 마지막 까지만 잘라야 한다.
            # j = 2, i = 2일 경우 array index out of range
            sliced = ''
            if (i + j) > len(s):
                sliced = s[j:]
            else:
                sliced = s[j:j+i]

            if prev_str is None:
                prev_str = sliced
                continue

            if sliced == prev_str:
                prev_count += 1
                continue

            if prev_count < 2:
                strs.append(prev_str)
            else:
                strs.append(str(prev_count) + prev_str)

            prev_count = 1
            prev_str = sliced

        if prev_count < 2:
            strs.append(prev_str)
        else:
            strs.append(str(prev_count) + prev_str)

        answers.append(''.join(strs))

    return min(len(answer) for answer in answers)
