from unittest import TestCase


def merge(left_half, right_half):
    result = []
    i = j = 0

    while i < len(left_half) and j < len(right_half):
        if left_half[i] < right_half[j]:
            result.append(left_half[i])
            i += 1
        else:
            result.append(right_half[j])
            j += 1

    result.extend(left_half[i:])
    result.extend(right_half[j:])

    return result


def merge_sort(arr):
    if len(arr) <= 1:
        return arr

    mid = len(arr) // 2

    # left, right 반으로 나눈다.
    left_half = arr[:mid]
    right_half = arr[mid:]

    left_half = merge_sort(left_half)
    right_half = merge_sort(right_half)

    # 왼쪽과 오른쪽을 합친다.
    return merge(left_half, right_half)



class MergeSortTest(TestCase):

    def test(self):
        arr = [5, 7, 9, 0, 3, 1, 6, 2, 4, 8]

        self.assertEqual([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], merge_sort(arr))
