from unittest import TestCase


def binary_search_recursive(array, target, start, end):
    mid = (start + end) // 2

    if array[mid] == target:
        return mid + 1

    # 다시 이진 탐색 수행
    mid_value = array[mid]

    if mid_value > target:
        # start와 mid 사이에 있는 경우
        return binary_search_recursive(array, target, start, mid - 1)  # 어차피 mid 값은 target이 아니므로, -1을 해준다.
    else:
        # mid와 end 사이에 있는 경우
        return binary_search_recursive(array, target, mid + 1, end)  # 어차피 mid 값은 target이 아니므로, +1을 해준다.


def binary_search(arr, target, start, end):
    mid = (start + end) // 2

    while arr[mid] != target:
        if arr[mid] > target:
            end = mid - 1
        else:
            start = mid + 1

        mid = (start + end) // 2

    return mid + 1


class BinarySearchTest(TestCase):

    def test1(self):
        n, target = 10, 7
        arr = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]

        self.assertEqual(binary_search_recursive(arr, target, 0, n - 1), 4)
        self.assertEqual(binary_search(arr, target, 0, n - 1), 4)

    def test2(self):
        n, target = 3, 3
        arr = [1, 3, 5, 7]

        self.assertEqual(binary_search_recursive(arr, target, 0, n - 1), 2)
        self.assertEqual(binary_search(arr, target, 0, n - 1), 2)
