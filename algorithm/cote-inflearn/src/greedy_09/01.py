import operator


class Body:

    def __init__(self, m, kg):
        self.m = m
        self.kg = kg


n = int(input())

bodies = []
for i in range(n):
    m, kg = map(int, input().split())
    bodies.append(Body(m, kg))

# 키를 기준으로 내림차순 정렬, class 내에서 __lt__ 함수를 구현해도 된다.
bodies.sort(key=operator.attrgetter('m'), reverse=True)

answer = 0
max_kg = 0
for body in bodies:
    # 모든 지원자를 비교해서, 다른 지원자보다 높은 지원자면 탈락, 그렇지 않으면 선발된다.
    # 이전 합격한 사람 중 몸무게가 더 큰 사람이 있다면 탈락
    if body.kg >= max_kg:
        max_kg = body.kg
        answer += 1

print(answer)
