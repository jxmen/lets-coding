n, m = map(int, input().split())

pm = [0 for _ in range(m)]


def dfs(idx):
    global pm
    if idx == m:
        for p in pm:
            print("%d " % p, end='')
        print()

        return

    for i in range(1, n + 1):
        pm[idx] = i
        dfs(idx + 1)


dfs(0)
