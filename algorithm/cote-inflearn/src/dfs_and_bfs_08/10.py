
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


def dfs(x, y, board):
    global answer

    if x == 6 and y == 6:
        answer += 1
        return

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        # 맵 안쪽이고 이동할 수 있을때만 재귀를 실행한다.
        if 0 <= nx <= 6 and 0 <= ny <= 6 and board[nx][ny] == 0:
            board[nx][ny] = 1
            dfs(nx, ny, board)
            board[nx][ny] = 0


board[0][0] = 1
dfs(0, 0, board)
print(answer)
