from collections import deque

def solution(begin, target, words):
    return bfs(begin, target, words)

def bfs(s, target, words):
    if target not in words :
        return 0

    answer = 0

    dq = deque()
    dq.appendleft(s)

    while dq and target not in dq:
        for _ in range(0, len(dq)):
            poped = dq.pop()

            for i in range(0, len(words)):
                word = words[i]
                match_count = 0
                word_list = list(word)
                poped_list = list(poped)

                for i in range(0, len(poped)):
                    if word_list[i] == poped_list[i]:
                        match_count += 1

                if match_count == len(poped) - 1:
                    dq.appendleft(word)

        answer += 1

    return answer

# dfs로는 안됨!!!
def dfs(s, target, words, count, visited):
    if target not in words:
        return 0

    if (s == target):
        print(count)
        return count

    # words 중 글자 하나만 바꿔서 해결되는 문자 목록
    # words에 있는걸 글자를 문자 하나로 쪼개서, s와 일치하는 문자가 len(s) - 1개일 경우 포함시킨다.
    loop_target_indexies = []
    for i in range(0, len(words)):
        word = words[i]
        if visited[i] == 1:
            continue

        match_count = 0
        for j in range(0, len(word)):
            if list(s)[j] == list(word)[j]:
                match_count += 1

        if match_count == len(s) - 1:
            # loop_targets.append(word)
            loop_target_indexies.append(i)

    # used에 남는게 더이상 없다면 count를 그냥 리턴한다.
    if len(loop_target_indexies) == 0:
        return count

    # TODO: 찾을 대상이 없다면 어떻게 처리할 것인가?
    counts = []
    for i in loop_target_indexies:
        word = words[i]
        visited[i] = 1 # TODO: 나중에 풀어줘야 함
        count = dfs(word, target, words, count+1, visited)
        visited[i] = 0

        counts.append(count)
    print(counts)

    if len(counts) == 0:
        return 0
    else:
        return min(counts)


result = solution("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"])
print(result)
