from collections import deque

# Initialize a 7x7 map with zeros
board = [[0] * 7 for _ in range(7)]

# Input the values for the map
for i in range(7):
    map_row = list(map(int, input().split()))

    for j in range(len(map_row)):
        board[j][i] = map_row[j]

answer = 0

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]


def bfs(x, y):
    global board
    dq = deque()
    board[x][y] = 1
    dq.append([x, y])

    level = 0

    while dq:
        dq_len = len(dq)
        for _ in range(dq_len):
            px, py = dq.popleft()

            if px == 6 and py == 6:
                return level

            for i in range(4):
                nx = px + dx[i]
                ny = py + dy[i]

                if 0 <= nx <= 6 and 0 <= ny <= 6 and board[nx][ny] == 0:
                    board[nx][ny] = 1
                    dq.append([nx, ny])

        level += 1

    return -1


print(bfs(0, 0))
