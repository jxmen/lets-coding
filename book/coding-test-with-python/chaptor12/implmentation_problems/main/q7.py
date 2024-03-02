n = int(input())

half = len(str(n)) // 2  # 6일경우 3
l = list(str(n))

leftSum = sum(map(int, l[:half]))
rightSum = sum(map(int, l[half:]))

if leftSum == rightSum:
    print("LUCKY")
else:
    print("READY")
