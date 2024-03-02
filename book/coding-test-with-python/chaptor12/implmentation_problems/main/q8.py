def is_number(x: str):
    return x.isdigit()


def solution(a_str: str):
    alphas = []
    nums = []

    # O(N)
    for s in list(a_str):
        if is_number(s):
            nums.append(s)
        else:
            alphas.append(s)

    num_sum = sum(map(int, nums))

    alphas.sort() # O(K log K)

    # O(N) + O(K log K) = O(N + K log K)
    # K가 0일경우 O(N)
    return ''.join(alphas) if num_sum == 0 else ''.join(alphas) + str(num_sum)
