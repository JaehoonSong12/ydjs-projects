def maxMirror(nums: list[int]) -> int:
    """
    Description:
        We'll say that a "mirror" section in an array is a group of contiguous elements
        such that somewhere in the array, the same group appears in reverse order.
        Return the size of the largest mirror section found in the given array.

    Examples:
        maxMirror([1, 2, 3, 8, 9, 3, 2, 1]) → 3
        maxMirror([1, 2, 1, 4]) → 3
        maxMirror([7, 1, 2, 9, 7, 2, 1]) → 2

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q94.py`

    Args:
        nums (list[int]): The list of integers to examine.

    Returns:
        int: The length of the largest mirror section.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for maxMirror function
import unittest

class TestMaxMirror(unittest.TestCase):
    def test_maxMirror(self):
        self.assertEqual(maxMirror([1, 2, 3, 8, 9, 3, 2, 1]), 3)
        self.assertEqual(maxMirror([1, 2, 1, 4]), 3)
        self.assertEqual(maxMirror([7, 1, 2, 9, 7, 2, 1]), 2)
        self.assertEqual(maxMirror([21, 22, 9, 8, 7, 6, 23, 24, 6, 7, 8, 9, 25, 7, 8, 9]), 4)
        self.assertEqual(maxMirror([1, 2, 1, 20, 21, 1, 2, 1, 2, 23, 24, 2, 1, 2, 1, 25]), 4)
        self.assertEqual(maxMirror([1, 2, 3, 2, 1]), 5)
        self.assertEqual(maxMirror([1, 2, 3, 3, 8]), 2)
        self.assertEqual(maxMirror([1, 2, 7, 8, 1, 7, 2]), 2)
        self.assertEqual(maxMirror([1, 1, 1]), 3)
        self.assertEqual(maxMirror([1]), 1)
        self.assertEqual(maxMirror([]), 0)
        self.assertEqual(maxMirror([9, 1, 1, 4, 2, 1, 1, 1]), 3)
        self.assertEqual(maxMirror([5, 9, 9, 4, 5, 4, 9, 9, 2]), 7)
        self.assertEqual(maxMirror([5, 9, 9, 6, 5, 4, 9, 9, 2]), 2)
        self.assertEqual(maxMirror([5, 9, 1, 6, 5, 4, 1, 9, 5]), 3)

if __name__ == "__main__":
    unittest.main()
