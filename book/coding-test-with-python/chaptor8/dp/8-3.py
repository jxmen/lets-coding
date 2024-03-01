d = [0] * 100


def pibo(x):
    if x == 1 or x == 2:
        return x

    d[1], d[2] = 1, 2
    for i in range(3, x + 1):
        d[i] = d[i - 1] + d[i - 2]

    return d[x]


print(pibo(1))
print(pibo(2))
print(pibo(3))
print(pibo(5))
print(pibo(99))
