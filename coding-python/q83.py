def pre4(nums: list[int]) -> list[int]:
    """
    Description:
        Given a non-empty list of integers `nums`, return a new list containing the elements
        from `nums` that appear before the first `4`. You may assume that `nums` contains at
        least one `4`.

    Examples:
        pre4([1, 2, 4, 1]) → [1, 2]
        pre4([3, 1, 4]) → [3, 1]
        pre4([1, 4, 4]) → [1]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q83.py`

    Args:
        nums (list[int]): A non-empty list of integers containing at least one 4.

    Returns:
        list[int]: A list of the elements before the first 4 in `nums`.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for pre4 function
import unittest

class TestPre4(unittest.TestCase):
    def test_pre4(self):
        self.assertEqual(pre4([1, 2, 4, 1]), [1, 2])
        self.assertEqual(pre4([3, 1, 4]), [3, 1])
        self.assertEqual(pre4([1, 4, 4]), [1])
        self.assertEqual(pre4([1, 4, 4, 2]), [1])
        self.assertEqual(pre4([1, 3, 4, 2, 4]), [1, 3])
        self.assertEqual(pre4([4, 4]), [])
        self.assertEqual(pre4([3, 3, 4]), [3, 3])
        self.assertEqual(pre4([1, 2, 1, 4]), [1, 2, 1])
        self.assertEqual(pre4([2, 1, 4, 2]), [2, 1])
        self.assertEqual(pre4([2, 1, 2, 1, 4, 2]), [2, 1, 2, 1])

if __name__ == "__main__":
    unittest.main()
