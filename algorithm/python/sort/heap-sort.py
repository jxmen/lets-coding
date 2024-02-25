from unittest import TestCase


def heapify(arr, heap_size, index):
    """

    :param arr: 최대 힙을 구성할 배열
    :param index:
    :param heap_size:
    :return:
    """
    largest = index
    left_child = 2 * index + 1
    right_child = 2 * index + 2

    # 왼쪽 자식과 비교해서 왼쪽 자식이 더 크다면 변경
    if left_child < heap_size and arr[largest] < arr[left_child]:
        largest = left_child

    # 오른쪽 자식이 존재하고 현재 노드보다 큰 경우
    if right_child < heap_size and arr[largest] < arr[right_child]:
        largest = right_child

    # largest가 변경되었을 경우, 현재 노드와 largest 위치의 노드를 교환
    if largest != index:
        # 현재 노드와 largest 위치의 노드를 교환
        arr[largest], arr[index] = arr[index], arr[largest]

        # 변경된 위치에서 다시 heapify 호출
        heapify(arr, heap_size, largest)


def heap_sort(arr):
    n = len(arr)

    # 최대 힙을 구성하고
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)

    # 힙에서 하나씩 루트를 제거하여 정렬
    for i in range(n - 1, 0, -1):
        # 루트와 마지막 노드 교환
        arr[i], arr[0] = arr[0], arr[i]

        # 남은 부분에서 다시 최대 힙 구성
        heapify(arr, i, 0)

    return arr


class HeapSortTest(TestCase):

    def test(self):
        arr = [5, 0, 3, 1, 6, 2, 4]

        self.assertEqual([0, 1, 2, 3, 4, 5, 6], heap_sort(arr))
