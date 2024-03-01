from unittest import TestCase


def solution(m, arr):
    # 원하는 높이가 m일때 m의 최대값
    start = 0
    end = max(arr)

    result = 0
    while start <= end:
        mid = (start + end) // 2
        total = 0
        for x in arr:
            # 잘랐을 때 떡 길이가 더 길때만 수행
            if x > mid:
                total += (x - mid)

        if total < m:
            end = mid - 1
        else:
            result = mid
            start = mid + 1

    return result


class Q3Test(TestCase):
    def test(self):
        m = 6
        n_list = [19, 15, 10, 17]

        self.assertEqual(solution(m, n_list), 15)
