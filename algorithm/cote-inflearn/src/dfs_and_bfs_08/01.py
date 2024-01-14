n = int(input())
n_list = list(map(int, input().split()))

assert len(n_list) == n

total = 0
for nl in n_list:
    total += nl

flag = False


def dfs(idx, sum, arr):
    global flag

    if flag:
        return

    if (total - sum) == sum:
        flag = True
        print("YES")
        return

    if (idx + 1 == n):
        return

    # 집합을 쓴다/안쓴다 두개의 dfs로 뻗어나간다.
    dfs(idx + 1, sum + arr[idx + 1])
    dfs(idx + 1, sum, arr)


dfs(0, 0, n_list)

if not flag:
    print("NO")
