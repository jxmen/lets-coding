import operator


class Reservation:

    def __init__(self, start, end):
        self.start = start
        self.end = end

n = int(input())

r_list = []
for _ in range(n):
    # 회의 시작시간/끝시간
    start, end = map(int, input().split())

    r_list.append(Reservation(start, end))

r_list.sort(key = operator.attrgetter('end', 'start'))
# r_list.sort(key = operator.attrgetter('start'))

answer = 0
prev_end = 0
for r in r_list:
    if r.start >= prev_end:
        answer += 1
        prev_end = r.end

print(answer)
