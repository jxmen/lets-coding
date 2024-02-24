from unittest import TestCase


def quick_sort_py(arr):
    """
    파이썬 문법의 장점을 활용해 간결하게 구성된 퀵 정렬.

    다만 left, right에 있는 원소들을 구할 때 pivot을 제외한 모든 원소들을 확인하므로 시간이 조금 더 걸린다.
    """
    if len(arr) == 1:
        return arr

    # pivot을 처음 원소로 선택한다.
    pivot = arr[0]
    tail = arr[1:]

    # 왼쪽에서부터 pivot보다 큰 원소를 선택한다.
    left_side = [x for x in tail if x <= pivot]
    # 오른쪽에서부터 pivot보다 작은 원소를 선택한다.
    right_side = [x for x in tail if x > pivot]

    # 왼쪽에 있는 것들은 피봇보다 왼쪽으로, 오른쪽에 있는 것을은 피봇보다 오른쪽으로 이동
    return quick_sort_py(left_side) + [pivot] + quick_sort_py(right_side)


def quick_sort(arr, start, end):
    # 원소가 1개일 경우 바로 종료
    if start >= end:
        return arr

    pivot = start
    left = start + 1
    right = end

    # 왼쪽에는 피벗보다 작은 수, 오른쪽은 피벗보다 큰 수
    while left <= right:
        while left <= end and arr[left] <= arr[pivot]:
            left += 1

        while right > start and arr[right] >= arr[pivot]:
            right -= 1

        if left > right:
            arr[pivot], arr[right] = arr[right], arr[pivot]
        else:
            arr[left], arr[right] = arr[right], arr[left]

    quick_sort(arr, left, right - 1)
    quick_sort(arr, right + 1, end)

    return arr


class QuickSortTest(TestCase):

    def test(self):
        arr = [3, 5, 4, 2, 10]
        sorted_list = quick_sort(
            arr=arr,
            start=0,
            end=len(arr) - 1
        )

        self.assertEqual([2, 3, 4, 5, 10], sorted_list)

    def test_py(self):
        sorted_list = quick_sort_py([3, 5, 4, 2, 10])

        self.assertEqual([2, 3, 4, 5, 10], sorted_list)
