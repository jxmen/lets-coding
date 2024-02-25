from dataclasses import dataclass
from typing import Type, List
from unittest import TestCase


@dataclass
class Student:
    name: str
    score: int


def solution(students: List[Student]) -> List[Student]:
    return sorted(students, key=lambda x: x.score, reverse=True)


class Q3Test(TestCase):

    def test(self):
        student1 = Student(name="홍길동", score=77)
        student2 = Student(name="이순신", score=95)

        self.assertEqual(solution([student1, student2]), [student2, student1])
