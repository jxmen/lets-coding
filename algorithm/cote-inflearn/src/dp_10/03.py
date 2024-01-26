n = int(input())

arr = list(map(int, input().split()))

dp = [0 for _ in range(n)]
dp[0] = 1

answer = 0
for i in range(n):

    _max = 0
    for j in range(i - 1, -1, -1):
        # 현재 값보다 작은 값이고 dp에 있는 값이 최대값이면
        if arr[j] < arr[i] and dp[j] > _max:
            _max = dp[j]

    dp[i] = _max + 1
    answer = max(dp[i], answer)

print(answer)
