d = [0] * 100


def fibo_recursive(x):
    if x == 1 or x == 2:
        return x

    if d[x] != 0:
        return d[x]

    d[x] = fibo_recursive(x - 1) + fibo_recursive(x - 2)

    return d[x]


print(fibo_recursive(1))
print(fibo_recursive(2))
print(fibo_recursive(3))
print(fibo_recursive(5))
print(fibo_recursive(99))
