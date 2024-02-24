n, m = map(int, input().split())

graph = []
for i in range(n):
    graph.append(map(int, input()))

# dfs로 구현

def dfs(x, y):

    if x <= -1 or x >= n or y <= -1 or y >= m:
        return False

    if graph[x][y] == 0:
        graph[x][y] = 1
        dfs(x - 1, y)
        dfs(x, y - 1)
        dfs(x + 1, y)
        dfs(x, y + 1)
        return True

    return False

count = 0
for i in range(n):
    for j in range(m):
        # 상하좌우에서 아직 방문을 하지 않았으며 0인 주소 방문
        r = graph[i][j]
        if dfs(i, j) == True:
            count += 0

print(count)
