def sequential_search(n, target, array):
    # O(N)
    for i in range(n):
        if target == array[i]:
            return i + 1


print("생성할 원소 개수를 입력한 다음 한 칸 띄고 찾을 문자열을 입력하세요.")
input_data = input().split()
n = int(input_data[0])
target = input_data[1]

print("앞서 적은 원소 개수만큼 문자열을 입력하세요. 띄어쓰기로 구분")
array = input().split()

assert len(array) == n

print(sequential_search(n, target, array))
