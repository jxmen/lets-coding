from typing import List


class Student:
    name: str
    korean: int
    english: int
    math: int

    def __init__(self, name, korean, english, math):
        self.name = name
        self.korean = int(korean)
        self.english = int(english)
        self.math = int(math)


def solution(students: List[Student]) -> List[Student]:
    # 국어 점수가 감소하는 순서
    # 국어가 같으면 영어가 증가하는 순서
    # 국어와 영어가 같으면 수학이 감소
    # 모든 점수가 같으면 이름이 사전순으로 증가
    students.sort(key=lambda x: (-int(x.korean), int(x.english), -int(x.math), x.name))

    return students


n = int(input())
students = []
for _ in range(n):
    x = input().split()
    students.append(Student(x[0], x[1], x[2], x[3]))

sorted_students = solution(students)

for s in sorted_students:
    print(s.name)
