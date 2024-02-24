from unittest import TestCase

"""
Goal
- Insertion Sort에 대해 설명할 수 있다.
- Insertion Sort 과정에 대해 설명할 수 있다.
- Insertion Sort을 구현할 수 있다.
- Insertion Sort의 시간복잡도와 공간복잡도를 계산할 수 있다.
- Insertion Sort와 Selection Sort 차이에 대해 설명할 수 있다.
"""


def insertion_sort(arr):
    for cur_idx in range(1, len(arr)):
        cur = arr[cur_idx]
        prev_idx = cur_idx - 1

        while prev_idx >= 0 and arr[prev_idx] > cur:
            arr[prev_idx + 1] = arr[prev_idx]
            prev_idx -= 1

        arr[prev_idx + 1] = cur

    return arr


class InsertionSortTest(TestCase):

    def test(self):
        sorted_list = insertion_sort([3, 5, 2, 4, 10])

        self.assertEqual(sorted_list, [2, 3, 4, 5, 10])
