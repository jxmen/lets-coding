from unittest import TestCase
from itertools import chain


def count_sort(arr):
    cnt = get_count_arr(arr)

    res = []
    for i in range(len(cnt)):

        # cnt array에서 들어가 있는 개수만큼 담는다.
        for _ in range(cnt[i]):
            res.append(i)

    return res


def count_sort_fp(arr):
    cnt = get_count_arr(arr)

    iterable = chain.from_iterable([i] * cnt[i] for i in range(len(cnt)))

    return list(iterable)


def get_count_arr(arr):
    count = [0] * (max(arr) + 1)

    for i in arr:
        count[i] += 1

    return count


class CountSortTest(TestCase):

    def test(self):
        arr = [2, 2, 0, 0, 1, 3, 3, 3]

        self.assertEqual(get_count_arr(arr), [2, 1, 2, 3])

        base_arr = [7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2]
        expect_arr = [0, 0, 1, 1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 9, 9]
        self.assertEqual(count_sort(base_arr), expect_arr)
        self.assertEqual(count_sort_fp(base_arr), expect_arr)
