import sys

n = int(input())
coins = sorted(list(map(int, input().split())), reverse=True)

assert len(coins) == n

m = int(input())

max_int = sys.maxsize
answer = max_int


def dfs(level, sum, coins, price):
    global answer
    if sum > price:
        return

    if level >= answer:
        return

    if sum == price:
        answer = min(answer, level)
        return

    for coin in coins:
        dfs(level + 1, sum + coin, coins, price)


dfs(0, 0, coins, m)
print(answer)
