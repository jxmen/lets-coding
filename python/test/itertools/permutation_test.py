import unittest

from itertools import permutations


class PermutationTest(unittest.TestCase):

    def test_1_길이의_순열을_생성하면_리스트의_길이만큼_순열의_길이가_된다(self):
        test_list = [1, 2, 3]
        permute_list = list(permutations(test_list, 1))

        print(permute_list)

        self.assertEqual(len(permute_list), len(test_list))

    def test_길이를_넘기지_않으면_list의_길이만큼의_순열을_생성한다(self):
        test_list = [1, 2, 3]
        length = len(test_list)

        actual = list(permutations(test_list))

        self.assertEqual(actual, list(permutations(test_list, length)))
        self.assertEqual(actual, [
            (1, 2, 3), (1, 3, 2),
            (2, 1, 3), (2, 3, 1),
            (3, 1, 2), (3, 2, 1)
        ])

    def test_길이를_넘기면_넘긴_길이만큼의_순열을_생성한다(self):
        test_list = [1, 2, 3]

        actual = list(permutations(test_list, 2))

        self.assertEqual(len(actual[0]), 2)
        self.assertEqual(actual, [
            (1, 2), (1, 3),
            (2, 1), (2, 3),
            (3, 1), (3, 2)
        ])
