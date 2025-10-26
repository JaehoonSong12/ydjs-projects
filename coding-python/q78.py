def sameEnds(nums: list[int], length: int) -> bool:
    """
    Description:
        Return True if the group of `length` numbers at the start and end of the list are the same.
        You may assume that 0 <= length <= len(nums).

    Examples:
        sameEnds([5, 6, 45, 99, 13, 5, 6], 1) → False
        sameEnds([5, 6, 45, 99, 13, 5, 6], 2) → True
        sameEnds([5, 6, 45, 99, 13, 5, 6], 3) → False

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q78.py`

    Args:
        nums (list[int]): The list of integers to check.
        length (int): The number of elements to compare at both ends.

    Returns:
        bool: True if the first `length` elements and the last `length` elements are identical.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for sameEnds function
import unittest

class TestSameEnds(unittest.TestCase):
    def test_sameEnds(self):
        self.assertFalse(sameEnds([5, 6, 45, 99, 13, 5, 6], 1))
        self.assertTrue (sameEnds([5, 6, 45, 99, 13, 5, 6], 2))
        self.assertFalse(sameEnds([5, 6, 45, 99, 13, 5, 6], 3))
        self.assertTrue (sameEnds([1, 2, 5, 2, 1], 1))
        self.assertFalse(sameEnds([1, 2, 5, 2, 1], 2))
        self.assertTrue (sameEnds([1, 2, 5, 2, 1], 0))
        self.assertTrue (sameEnds([1, 2, 5, 2, 1], 5))
        self.assertTrue (sameEnds([1, 1, 1], 0))
        self.assertTrue (sameEnds([1, 1, 1], 1))
        self.assertTrue (sameEnds([1, 1, 1], 2))
        self.assertTrue (sameEnds([1, 1, 1], 3))
        self.assertTrue (sameEnds([1], 1))
        self.assertTrue (sameEnds([], 0))
        self.assertFalse(sameEnds([4, 2, 4, 5], 1))

if __name__ == "__main__":
    unittest.main()
