from unittest import TestCase


def solution(x, count = 0):
    if x == 1:
        return count

    # 연산을 총 4개씩 하고, 이를 또 재귀함수를 돌리자.

    answers = []
    if x % 5 == 0:
        answers.append(solution(x / 5, count + 1))

    if x % 3 == 0:
        answers.append(solution(x / 3, count + 1))

    if x % 2 == 0:
        answers.append(solution(x / 2, count + 1))

    answers.append(solution(x - 1, count + 1))

    return min(answers)


class Q2Test(TestCase):

    def test(self):
        self.assertEqual(solution(1), 0)
        self.assertEqual(solution(2), 1)
        self.assertEqual(solution(26), 3)
