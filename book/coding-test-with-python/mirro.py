from collections import deque

"""
input
---
5 6
101010
111111
000001
111111
111111

---
expect: 10
"""

n, m = map(int, input().split())

graph = []
for i in range(n):
    graph.append(list(map(int, input())))

dx = [0, -1, 0, 1]
dy = [1, 0, -1, 0]

def bfs(x, y, graph):
    dq = deque()
    dq.appendleft((x, y))

    while dq:
        x, y = dq.popleft()

        for i in range(len(dx)):
            nx = x + dx[i]
            ny = y + dy[i]

            # 벗어날 경우 무시
            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue

            # 벽인 경우 무시
            if graph[nx][ny] == 0:
                continue

            # 해당 노드를 처음 방문할 시에만 거리 기록
            if graph[nx][ny] == 1:
                graph[nx][ny] = graph[x][y] + 1
                dq.append((nx, ny))

    return graph[n-1][m-1]


result = bfs(0, 0, graph)
print(result)
