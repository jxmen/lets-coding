import unittest


class ListAddTest(unittest.TestCase):

    def test_리스트에서_리스트를_더할경우_원소들이_리스트안으로_들어간다(self):
        a_list = []
        b_list = [1, 2, 3]

        a_list += b_list

        self.assertEqual(a_list, [1, 2, 3])
