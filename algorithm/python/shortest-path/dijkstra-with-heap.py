"""
우선순위 큐를 통해 최적화한 알고리즘

출처: 이것이 취업을 위한 코딩테스트다 with 파이썬
"""

import heapq
import sys
input = sys.stdin.readline
INF = int(1e9)

n, m = map(int, input().split())
start = int(input())

graph = [[] for _ in range(n+1)]
visited = [False] * (n+1)
distance_table = [INF] * (n+1) # 최단거리 테이블

for _ in range(m):
    # node -> dist 가는 비용이 cost
    a,b,c = map(int, input().split())
    graph[a].append((b,c))

def dijkstra(start):
    q = []
    distance_table[start] = 0
    visited[start] = True
    heapq.heappush(q, (0, start))

    while q:
        dist, now = heapq.heappop(q)
        if distance_table[now] < dist:
            continue

        visited[now] = True
        for i in graph[now]:
            cost = dist + i[1]
            if cost < distance_table[i[0]]:
                distance_table[i[0]] = cost
                heapq.heappush(q, (cost, i[0]))

dijkstra(start)

for i in range(1, n+1):
    if distance_table[i] == INF:
        print("INFINITY")
    else:
        print(distance_table[i])
