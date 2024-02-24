import unittest


def selection_sort(arr):
    for i in range(0, len(arr)):
        min_idx = i

        for j in range(i, len(arr)):
            # 1. 주어진 배열 중에 최소값을 찾는다.
            if arr[j] < arr[min_idx]:
                min_idx = j

        # 2. 그 값을 맨 앞에 위치한 값과 교체한다.
        # 3. 맨 처음 위치를 뺀 배열을 같은 방법으로 교체
        arr[i], arr[min_idx] = arr[min_idx], arr[i]

    return arr


class SelectionSortTest(unittest.TestCase):

    def test(self):
        sorted_list = selection_sort([10, 5, 4, 3, 9])

        self.assertEqual(sorted_list, [3, 4, 5, 9, 10])
