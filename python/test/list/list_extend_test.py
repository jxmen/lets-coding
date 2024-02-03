import unittest

class ListExtendTest(unittest.TestCase):

    def test_extend_함수는_리스트의_원소를_서로_합친다(self):
        a_list = [1, 2, 3]

        a_list.extend([4, 5])

        self.assertEqual(a_list, [1, 2, 3, 4, 5])
