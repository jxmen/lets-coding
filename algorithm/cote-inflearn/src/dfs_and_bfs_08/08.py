from math import comb

n, f = map(int, input().split())
# assert 1 <= n <= 10

p = [0 for _ in range(n)]
ch = [0 for _ in range(n + 1)]

b = []
for i in range(n):
    b.append(comb(n - 1, i))

flag = False

def dfs(level, sum):
    global flag
    if flag:
        return

    if level == n:
        if sum == f:
            for x in p:
                print("%s " % x, end='')
            print()
            flag = True
    else:
        for i in range(1, n + 1):
            if ch[i] == 0:
                ch[i] = 1
                p[level] = i
                dfs(level + 1, sum + (p[level] * b[level]))
                ch[i] = 0


dfs(0, 0)
