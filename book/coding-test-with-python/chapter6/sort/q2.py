# 위에서 아래로
from typing import List
from unittest import TestCase


def solution(ints: List[int]) -> List[int]:
    """

    :param ints: 내림차순할 리스트
    :return: 내림차순된 리스트
    """

    return sorted(ints, reverse=True)


class Q2Test(TestCase):

    def test(self):
        ints = [15, 27, 12]

        self.assertEqual(solution(ints), [27, 15, 12])
