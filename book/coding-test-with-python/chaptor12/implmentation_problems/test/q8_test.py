from unittest import TestCase


class Q8Test(TestCase):

    def test(self):
        from chaptor12.implmentation_problems.main.q8 import solution
        self.assertEqual(solution("K1KA5CB7"), "ABCKK13")
        self.assertEqual(solution("ABCDEF"), "ABCDEF")
