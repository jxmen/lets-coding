from unittest import TestCase


def is_bst(arr, idx=0):
    left_idx = idx * 2 + 1
    right_idx = idx * 2 + 2

    if left_idx > len(arr) or right_idx > len(arr):
        return True

    cur = arr[idx]
    if arr[left_idx] > cur or arr[right_idx] < cur:
        return False

    return True if (is_bst(arr, left_idx) and is_bst(arr, right_idx)) else False


class IsBstTest(TestCase):

    def test(self):
        self.assertTrue(is_bst([1]))

        self.assertFalse(is_bst([1, 2, 3]))
        self.assertTrue(is_bst([2, 1, 3]))

        self.assertTrue(is_bst([30, 17, 48, 5, 23, 37, 50]))
        self.assertFalse(is_bst([30, 17, 48, 100, 200, 37, 50]))
