n, m = map(int, input().split())

a_list = []

for _ in range(n):
    a, b = map(int, input().split())
    a_list.append((a, b))

answer = 0


def dfs(idx, score, times, arr, limit_times):
    if times > limit_times:
        return

    if idx == n:
        global answer
        answer = max(answer, score)

        return

    dfs(idx + 1, score + arr[idx][0], times + arr[idx][1], arr, limit_times)
    dfs(idx + 1, score, times, arr, limit_times)


dfs(0, 0, 0, a_list, m)
print(answer)
