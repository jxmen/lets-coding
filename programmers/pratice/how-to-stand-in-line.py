# 프로그래머스 - 줄 서는 방법

from math import factorial


def fact(n):
    return factorial(n)


def solution(n, k):
    # 총 n!의 경우의 수를 갖고있음
    # 맨 앞의 수가 정해지면 (n - 1)! 의 경우의 수를 가짐
    result = []
    num_list = [i for i in range(1, n + 1)]
    while (n != 0):
        # k에서 (n - 1)!을 나눴을 때의 몫이 첫번째 오는 자리
        idx = (k - 1) // fact(n - 1)

        # k에서 (n - 1)!을 나눴을 때의 나머지가 다시 k로 오는 자리
        k = k % fact(n - 1)

        result.append(num_list.pop(idx))
        n -= 1

    return result


answer = solution(3, 5)
print(answer)
