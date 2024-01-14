# 라이브러리로도 쉽게 된다.
# import math
# print(math.comb(33, 19))

n, r = map(int, input().split())

assert 3 <= n <= 33 and 0 <= r <= n

cache = {}

def get_comb(n, r):
    if (n, r) in cache:
        return cache[(n, r)]

    if r == 1:
        cache[(n, r)] = n
        return n

    if r > n:
        return 0

    comb1 = get_comb(n - 1, r - 1)
    comb2 = get_comb(n - 1, r)
    cache[(n, r)] = comb1 + comb2

    return cache[(n, r)]

print(get_comb(n, r))
