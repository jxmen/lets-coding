n = int(input())
n_arr = list(map(int, input().split()))

m = int(input())
m_arr = list(map(int, input().split()))


def binary_search(arr, target, start, end):
    mid = (start + end) // 2
    if arr[mid] == target:
        return True

    while start <= end:
        if arr[mid] == target:
            return True

        if arr[mid] > start:
            end = mid - 1
        else:
            start = mid + 1

        mid = (start + end) // 2

    if arr[mid] == target:
        return True

    return False


n_arr.sort()
for target in m_arr:
    if binary_search(arr=n_arr, target=target, start=0, end=len(n_arr) - 1):
        print("yes")
    else:
        print("no")
