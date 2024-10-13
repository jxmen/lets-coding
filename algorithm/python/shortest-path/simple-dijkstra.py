"""
배열을 사용하여 다익스트라 알고리즘 구현하는 코드  
시간 복잡도는 모든 노드를 돌 때 O(N), 가장 짧은 노드를 찾는 과정에서 O(N)이므로 O(N^2)이다.

출처: 이것이 취업을 위한 코딩테스트다 with 파이썬
"""

import sys
input = sys.stdin.readline
INF = int(1e9)

n, m = map(int, input().split())
start = int(input())

graph = [[] for _ in range(n+1)]
visited = [False] * (n+1)
distance = [INF] * (n+1) # 최단거리 테이블

for _ in range(m):
    # a->b로 가는 비용이 c
    a, b, c = map(int, input().split())
    graph[a].append((b, c))

# [[], [(2, 2), (3, 5), (4, 1)], [(3, 3), (4, 2)], [(2, 3), (6, 5)], [(3, 3), (5, 1)], [(3, 1), (6, 2)], []]
print(graph)

def get_smallest_node():
    min_value = INF
    index = 0
    for i in range(1, n+1):
        if distance[i] < min_value and not visited[i]:
            min_value = distance[i]
            index = i
    return index

def dijkstra(start):
    distance[start] = 0
    visited[start] = True
    # start에서 갈 수 있는 노드들의 거리를 distance에 업데이트
    for node, edge_distance in graph[start]:
        distance[node] = edge_distance

    # O(N)
    for _ in range(n-1):
        # 방문하지 않은 노드 중에서 가장 짧은 노드를 선택
        now = get_smallest_node() # O(N)
        visited[now] = True
        for j in graph[now]:
            cost = distance[now] + j[1]
            if cost < distance[j[0]]:
                distance[j[0]] = cost

dijkstra(start)

for i in range(1, n+1):
    if distance[i] == INF:
        print("INFINITY")
    else:
        print(distance[i])

"""
input example

6 11
1
1 2 2
1 3 5
1 4 1
2 3 3
2 4 2
3 2 3
3 6 5
4 3 3
4 5 1
5 3 1
5 6 2

output example

0
2
3
1
2
4
"""
