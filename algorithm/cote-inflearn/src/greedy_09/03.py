import operator

n = int(input())

friend_list = []


class Friend:

    def __init__(self, start, end):
        self.start = start
        self.end = end


for _ in range(n):
    start, end = map(int, input().split())

    friend_list.append(Friend(start, end))

friend_list.sort(key=operator.attrgetter('start'))

answer = 0

from collections import deque

q = deque()

for f in friend_list:
    q_len = len(q)
    for i in range(q_len):
        popped = q.popleft()
        if popped.end > f.start:
            q.append(popped)

    q.append(f)
    if len(q) > answer:
        answer = len(q)

print(answer)
