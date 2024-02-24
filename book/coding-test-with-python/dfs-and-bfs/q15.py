import sys
from collections import deque

# n = 도시 개수, m = 도로 개수, k = 거리 정보, x = 도시의 번호
n, m, k, x = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(n + 1)]

# 도로 정보 입력 받기
for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())

    graph[a].append(b)

# 모든 도시에 대한 최단 거리 최소화
distance = [-1] * (n + 1)
distance[x] = 0  # 출발 도시까지의 거리는 0으로 설정

dq = deque([x])  # dq 생성 후 appendLeft 한 것과 같음

while dq:
    now = dq.popleft()  # [2,3]

    for next in graph[now]:
        if distance[next] == -1:
            distance[next] = distance[now] + 1
            dq.appendleft(next)

check = False
for i in range(1, n + 1):
    if distance[i] == k:
        print(i)
        check = True

if check == False:
    print(-1)
