n, m = map(int, input().split())
nums = list(map(int, input().split()))

assert len(nums) == n

ch = [0 for _ in range(n)]
pm = [0 for _ in range(m)]


def dfs(idx, nums, ch, pm):
    if idx == m:
        for p in pm:
            print("%s " % p, end='')
        print()

        return

    for i in range(len(nums)):

        # 현재 호출한 함수와 호출하려는 함수의 인덱스가 같은 경우 건너뛴다 (e.g. (3,3) )
        if ch[i] == 1:
            continue

        ch[i] = 1
        pm[idx] = nums[i]
        dfs(idx + 1, nums, ch, pm)
        ch[i] = 0


dfs(0, nums, ch, pm)
