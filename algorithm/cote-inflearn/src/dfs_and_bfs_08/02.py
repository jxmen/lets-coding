c, n = map(int, input().split())

# Initialize an empty list to store numbers
numbers = []

for _ in range(n):
    number = int(input())
    numbers.append(number)

answer = 0

# TODO: time limit error?
def dfs(idx, total, nums, limit):
    if total > limit:
        return

    if idx == n:
        global answer
        answer = max(answer, total)

        return

    dfs(idx + 1, total + nums[idx], nums, limit)
    dfs(idx + 1, total, nums, limit)


dfs(0, 0, numbers, c)
print(answer)
